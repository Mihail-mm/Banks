package M3208.MihailMM.Banks;

import M3208.MihailMM.BankAccounts.IBankAccount;
import M3208.MihailMM.Models.DepositRate;
import M3208.MihailMM.Observer.ISubscriber;

import java.util.List;
import java.util.UUID;

public interface IBank extends ISubscriber {
    String GetName();
    UUID GetId();
    double GetDebitInterest();
    List<DepositRate> GetDepositRates();
    double GetCreditInterest();
    Double GetCreditLimit();
    List<IBankAccount> Accounts();
    List<String> ClientOfAccount();
    void AddBankAccount(IBankAccount bankAccount);
    IBankAccount GetBankAccount(String clientName);
}
