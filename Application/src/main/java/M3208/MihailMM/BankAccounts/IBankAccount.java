package M3208.MihailMM.BankAccounts;

import M3208.MihailMM.Clients.Client;
import M3208.MihailMM.Observer.ISubscriber;
import M3208.MihailMM.Transactions.ITransaction;

public interface IBankAccount extends ISubscriber {
    Client GetClient();
    Double GetBalance();
    void WithdrawMoney(Double money);
    void Replenishment(Double money);
    void WithdrawMoney(Double money, IBankAccount toTranslation);
    void Replenishment(Double money, IBankAccount fromTranslation);
    void TransferMoney(IBankAccount toTranslation, Double money);
    void CancelTransaction(ITransaction toCancel);
    void DoTransaction(ITransaction transaction);
}
