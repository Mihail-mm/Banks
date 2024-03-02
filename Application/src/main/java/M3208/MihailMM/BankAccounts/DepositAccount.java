package M3208.MihailMM.BankAccounts;

import M3208.MihailMM.Banks.IBank;
import M3208.MihailMM.Clients.Client;
import M3208.MihailMM.Clients.ClientStatus.DoubtfulStatus;
import M3208.MihailMM.Models.DepositRate;
import M3208.MihailMM.ResultTypes.CancelTransactionResults.CancelTransactionAlreadyCancel;
import M3208.MihailMM.ResultTypes.CancelTransactionResults.CancelTransactionNotFound;
import M3208.MihailMM.ResultTypes.CancelTransactionResults.CancelTransactionResult;
import M3208.MihailMM.ResultTypes.CancelTransactionResults.CancelTransactionSuccess;
import M3208.MihailMM.ResultTypes.TransferMoneyResults.*;
import M3208.MihailMM.ResultTypes.WithdrawMoneyResults.*;
import M3208.MihailMM.Transactions.ITransaction;
import M3208.MihailMM.Transactions.Transaction;
import M3208.MihailMM.Transactions.TransactionStatus.CancelStatus;
import M3208.MihailMM.Transactions.TransactionStatus.CompletedStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DepositAccount implements IBankAccount {
    private UUID _id;
    private List<ITransaction> _history;
    private Client _client;
    private Float _balance;
    private IBank _bank;
    private LocalDate _finalDate;

    public DepositAccount(Float balance, Client client, IBank bank, LocalDate finalDate) {
        _id = UUID.randomUUID();
        _history = new ArrayList<>();
        _balance = balance;
        _client = client;
        _bank = bank;
        _finalDate = finalDate;
    }

    @Override
    public UUID GetId() {
        return _id;
    }

    @Override
    public Client GetClient() {
        return _client;
    }

    @Override
    public Float GetBalance() {
        return _balance;
    }

    @Override
    public WithdrawResult WithdrawMoney(Float money) {
        if (_finalDate.isAfter(LocalDate.now())) {
            return new WithdrawMoneyDepositLimit();
        }
        if (_balance < money) {
            return new NotEnoughMoneyResult();
        }
        if (_bank.GetMaxWithdraw() < money && _client.get_status() instanceof DoubtfulStatus) {
            return new WithdrawMoneyNotVerified();
        }
        var newTransaction = new Transaction(money, this, null);
        _history.add(newTransaction);
        _balance -= money;
        newTransaction.SetStatus(new CompletedStatus());
        return new WithdrawSuccessResult();
    }

    @Override
    public void Replenishment(Float money) {
        var newTransaction = new Transaction(money, null, this);
        _history.add(newTransaction);
        _balance += money;
        newTransaction.SetStatus(new CompletedStatus());
    }

    @Override
    public WithdrawResult WithdrawMoney(Float money, IBankAccount toTranslation) {
        if (_finalDate.isAfter(LocalDate.now())) {
            return new WithdrawMoneyDepositLimit();
        }
        if (_balance < money) {
            return new NotEnoughMoneyResult();
        }
        if (_bank.GetMaxWithdraw() < money && _client.get_status() instanceof DoubtfulStatus) {
            return new WithdrawMoneyNotVerified();
        }

        var newTransaction = new Transaction(money, this, toTranslation);
        _history.add(newTransaction);
        newTransaction.Execute();
        newTransaction.SetStatus(new CompletedStatus());
        return new WithdrawSuccessResult();
    }

    @Override
    public void Replenishment(Float money, IBankAccount fromTranslation) {
        var newTransaction = new Transaction(money, fromTranslation, this);
        _history.add(newTransaction);
        _balance += money;
        newTransaction.SetStatus(new CompletedStatus());
    }

    @Override
    public TransferMoneyResult TransferMoney(IBankAccount toTranslation, Float money) {
        if (money > _bank.GetMaxRemittance() && _client.get_status() instanceof DoubtfulStatus) {
            return new TransferMoneyDoubtfulResult();
        }
        if (_balance < money) {
            return new TransferMoneyNotEnough();
        }

        if (_finalDate.isAfter(LocalDate.now())) {
            return new TransferMoneyDepositLimit();
        }

        var newTransaction = new Transaction(money, this, toTranslation);
        _history.add(newTransaction);
        newTransaction.Execute();
        newTransaction.SetStatus(new CompletedStatus());
        return new TransferMoneySuccess();
    }

    @Override
    public CancelTransactionResult CancelTransaction(UUID idTransaction) {
        ITransaction cancelTransaction = _history.stream().filter(transaction -> transaction.GetId() == idTransaction).findFirst().orElse(null);
        if (cancelTransaction == null) {
            return new CancelTransactionNotFound();
        }
        if (cancelTransaction.GetStatus() instanceof CancelStatus) {
            return new CancelTransactionAlreadyCancel();
        }
        Replenishment(cancelTransaction.GetTransferAmount());
        cancelTransaction.SetStatus(new CancelStatus());
        return new CancelTransactionSuccess();
    }

    @Override
    public void DoTransaction(ITransaction transaction) {
        _history.add(transaction);
        if (transaction.GetToTranslation() == this) {
            _balance += transaction.GetTransferAmount();
        }
        if (transaction.GetTranslationSource() == this) {
            _balance -= transaction.GetTransferAmount();
        }

        transaction.SetStatus(new CompletedStatus());
    }

    @Override
    public void Update(LocalDate time) {
        if (time.isBefore(_finalDate)) {
            for (var rate : _bank.GetDepositRates()) {
                if (rate.getLowerLimit() <= _balance && _balance <= rate.getUpperLimit()) {
                    _balance += _balance*rate.getInterest();
                }
            }
        }
    }
}
