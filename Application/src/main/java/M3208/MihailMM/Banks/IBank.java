package M3208.MihailMM.Banks;

import M3208.MihailMM.BankAccounts.IBankAccount;
import M3208.MihailMM.Clients.Client;
import M3208.MihailMM.Models.DepositRate;
import M3208.MihailMM.Observer.IPublisher;
import M3208.MihailMM.Observer.ISubscriber;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IBank extends ISubscriber {
    String GetName();
    UUID GetId();
    Float GetDebitInterest();
    List<DepositRate> GetDepositRates();
    Float GetCreditCommission();
    Float GetMaxWithdraw();
    Float GetMaxRemittance();
    void AddBankAccount(IBankAccount bankAccount);
    IBankAccount CreateDebitAccount(Client client, Float startBalance);
    IBankAccount CreateCreditAccount(Client client, Float startBalance);
    IBankAccount CreateDepositAccount(Client client, Float startBalance, LocalDate finalDate);
    List<IBankAccount> GetBankAccounts();

}
