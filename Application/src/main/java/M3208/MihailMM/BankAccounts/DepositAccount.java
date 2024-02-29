package M3208.MihailMM.BankAccounts;

import M3208.MihailMM.Banks.IBank;
import M3208.MihailMM.Clients.Client;
import M3208.MihailMM.Transactions.ITransaction;

import java.time.LocalDate;
import java.util.List;

public class DepositAccount implements IBankAccount{
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

    }

    @Override
    public void Replenishment(Double money) {

    }

    @Override
    public void WithdrawMoney(Double money, IBankAccount toTranslation) {

    }

    @Override
    public void Replenishment(Double money, IBankAccount fromTranslation) {

    }

    @Override
    public void TransferMoney(IBankAccount toTranslation, Double money) {

    }

    @Override
    public void CancelTransaction(ITransaction toCancel) {

    }

    @Override
    public void DoTransaction(ITransaction transaction) {

    }

    @Override
    public void Update(LocalDate time) {

    }
}
