package M3208.MihailMM.BankAccounts;

import M3208.MihailMM.Banks.IBank;
import M3208.MihailMM.Clients.Client;
import M3208.MihailMM.Clients.ClientStatus.DoubtfulStatus;
import M3208.MihailMM.ResultTypes.CancelTransactionResults.CancelTransactionAlreadyCancel;
import M3208.MihailMM.ResultTypes.CancelTransactionResults.CancelTransactionResult;
import M3208.MihailMM.ResultTypes.CancelTransactionResults.CancelTransactionSuccess;
import M3208.MihailMM.ResultTypes.TransferMoneyResults.TransferMoneyDoubtfulResult;
import M3208.MihailMM.ResultTypes.TransferMoneyResults.TransferMoneyNotEnough;
import M3208.MihailMM.ResultTypes.TransferMoneyResults.TransferMoneyResult;
import M3208.MihailMM.ResultTypes.TransferMoneyResults.TransferMoneySuccess;
import M3208.MihailMM.ResultTypes.WithdrawMoneyResults.NotEnoughMoneyResult;
import M3208.MihailMM.ResultTypes.WithdrawMoneyResults.WithdrawMoneyNotVerified;
import M3208.MihailMM.ResultTypes.WithdrawMoneyResults.WithdrawResult;
import M3208.MihailMM.ResultTypes.WithdrawMoneyResults.WithdrawSuccessResult;
import M3208.MihailMM.Transactions.ITransaction;
import M3208.MihailMM.Transactions.Transaction;
import M3208.MihailMM.Transactions.TransactionStatus.CancelStatus;
import M3208.MihailMM.Transactions.TransactionStatus.CompletedStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DebitAccount implements IBankAccount {
    private final Client _client;
    private final IBank _bank;
    private UUID _id;
    private List<ITransaction> _history;
    private Float _accumulatedInterest;
    private Float _balance;

    public DebitAccount(Float balance, Client client, IBank bank) {
        _id = UUID.randomUUID();
        _history = new ArrayList<>();
        _balance = balance;
        _client = client;
        _bank = bank;
    }

    @Override
    public String GetAccountType() {
        return "Debit";
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
    public List<ITransaction> GetHistory() {
        return _history;
    }

    @Override
    public WithdrawResult WithdrawMoney(Float money) {
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
    public WithdrawResult WithdrawMoney(Float money, IBankAccount account) {
        if (_balance < money) {
            return new NotEnoughMoneyResult();
        }

        if (_bank.GetMaxWithdraw() < money && _client.get_status() instanceof DoubtfulStatus) {
            return new WithdrawMoneyNotVerified();
        }
        ITransaction newTransaction = new Transaction(money, this, account);
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
    public void Replenishment(Float money, IBankAccount account) {
        var newTransaction = new Transaction(money, account, this);
        _history.add(newTransaction);
        _balance += money;
        newTransaction.SetStatus(new CompletedStatus());
    }

    @Override
    public TransferMoneyResult TransferMoney(IBankAccount toTranslation, Float money) {
        if (_bank.GetMaxWithdraw() < money && _client.get_status() instanceof DoubtfulStatus) {
            return new TransferMoneyDoubtfulResult();
        }
        if (money > _balance) {
            return new TransferMoneyNotEnough();
        }
        this.WithdrawMoney(money, toTranslation);
        toTranslation.Replenishment(money, this);
        return new TransferMoneySuccess();
    }

    @Override
    public CancelTransactionResult CancelTransaction(ITransaction toCancel) {
        if (toCancel.GetToTranslation() == this) {
            _balance -= toCancel.GetTransferAmount();
        }
        if (toCancel.GetTranslationSource() == this) {
            _balance += toCancel.GetTransferAmount();
        }

        toCancel.SetStatus(new CancelStatus());
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
        if (time.getDayOfMonth() == 1) {
            this.Replenishment(_accumulatedInterest);
            _accumulatedInterest = 0f;
        }

        _accumulatedInterest += _balance * _bank.GetDebitInterest();
    }
}
