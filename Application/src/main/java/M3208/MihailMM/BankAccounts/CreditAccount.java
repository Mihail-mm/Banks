package M3208.MihailMM.BankAccounts;

import M3208.MihailMM.Clients.Client;
import M3208.MihailMM.Transactions.ITransaction;

import java.time.LocalDate;

public class CreditAccount implements IBankAccount{
    @Override
    public Client GetClient() {
        return null;
    }

    @Override
    public Double GetBalance() {
        return null;
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
