package M3208.MihailMM.BankAccounts;

import M3208.MihailMM.Banks.IBank;
import M3208.MihailMM.Clients.Client;
import M3208.MihailMM.Exceptions.BankAccountException;
import M3208.MihailMM.Transactions.ITransaction;
import M3208.MihailMM.Transactions.Transaction;
import M3208.MihailMM.Transactions.TransactionStatus.CancelStatus;
import M3208.MihailMM.Transactions.TransactionStatus.CompletedStatus;

import java.time.LocalDate;
import java.util.List;

public class DebitAccount implements IBankAccount{
    private List<ITransaction> _history;
    private Double _accumulatedInterest;
    private Client _client;
    private Double _balance;
    private double _interest;
    private IBank _bank;
    @Override
    public Client GetClient() {
        return _client;
    }

    @Override
    public Double GetBalance() {
        return _balance;
    }

    @Override
    public void WithdrawMoney(Double money) {
        if (_balance < money)
        {
            throw new BankAccountException("don't have money");
        }

        var newTransaction = new Transaction(money, this, null);
        _history.add(newTransaction);
        _balance -= money;
        newTransaction.SetStatus(new CompletedStatus());
    }

    @Override
    public void Replenishment(Double money) {
        var newTransaction = new Transaction(money, null, this);
        _history.add(newTransaction);
        _balance += money;
        newTransaction.SetStatus(new CompletedStatus());
    }

    @Override
    public void WithdrawMoney(Double money, IBankAccount toTranslation) {
        if (_balance < money)
        {
            throw new BankAccountException("don't have money");
        }

        var newTransaction = new Transaction(money, this, toTranslation);
        _history.add(newTransaction);
        _balance -= money;
        newTransaction.SetStatus(new CompletedStatus());
    }

    @Override
    public void Replenishment(Double money, IBankAccount fromTranslation) {
        var newTransaction = new Transaction(money, fromTranslation, this);
        _history.add(newTransaction);
        _balance += money;
        newTransaction.SetStatus(new CompletedStatus());
    }

    @Override
    public void TransferMoney(IBankAccount toTranslation, Double money) {
        WithdrawMoney(money, toTranslation);
    }

    @Override
    public void CancelTransaction(ITransaction toCancel) {
        if (!_history.contains(toCancel)) {
            throw new BankAccountException("not found transaction");
        }
        if (toCancel.GetStatus() instanceof CancelStatus) {
            throw new BankAccountException("");
        }
        Replenishment(toCancel.GetTransferAmount());
        toCancel.SetStatus(new CancelStatus());
    }

    @Override
    public void DoTransaction(ITransaction transaction) {
        _history.add(transaction);
        if (transaction.GetToTranslation() == this) {
            _balance += transaction.GetTransferAmount();
        }
        if (transaction.GetTranslationSource() == this)
        {
            _balance -= transaction.GetTransferAmount();
        }

        transaction.SetStatus(new CompletedStatus());
    }

    @Override
    public void Update(LocalDate time) {
        if (time.getDayOfMonth() == 1)
        {
            this.Replenishment(_accumulatedInterest);
            _accumulatedInterest = 0d;
        }

        _accumulatedInterest += _balance * _interest;
    }
}
