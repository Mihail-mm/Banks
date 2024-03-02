package M3208.MihailMM.Banks;

import M3208.MihailMM.BankAccounts.CreditAccount;
import M3208.MihailMM.BankAccounts.DebitAccount;
import M3208.MihailMM.BankAccounts.DepositAccount;
import M3208.MihailMM.BankAccounts.IBankAccount;
import M3208.MihailMM.Clients.Client;
import M3208.MihailMM.Models.DepositRate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Bank implements IBank{
    private UUID _id;
    private String _name;
    private List<Client> _clients;
    private List<IBankAccount> _accounts;
    private Float _debitInterest;
    private List<DepositRate> _depositRates;
    private Float _creditCommission;
    private Float _maxWithdraw;
    private Float _maxRemittance;

    public Bank(String name, Float debitInterest, List<DepositRate> depositRates, Float creditCommission, Float maxWithdraw, Float maxRemittance) {
        _id = UUID.randomUUID();
        _name = name;
        _debitInterest = debitInterest;
        _depositRates = depositRates;
        _creditCommission = creditCommission;
        _accounts = new ArrayList<>();
        _maxWithdraw = maxWithdraw;
        _maxRemittance = maxRemittance;
    }

    @Override
    public String GetName() {
        return _name;
    }

    @Override
    public UUID GetId() {
        return _id;
    }

    @Override
    public Float GetDebitInterest() {
        return _debitInterest;
    }

    @Override
    public List<DepositRate> GetDepositRates() {
        return _depositRates;
    }

    @Override
    public Float GetCreditCommission() {
        return _creditCommission;
    }

    @Override
    public Float GetMaxWithdraw() {
        return _maxWithdraw;
    }

    @Override
    public Float GetMaxRemittance() {
        return _maxRemittance;
    }


    @Override
    public void AddBankAccount(IBankAccount bankAccount) {
        _accounts.add(bankAccount);
    }

    @Override
    public IBankAccount CreateDebitAccount(Client client, Float startBalance) {
        IBankAccount account = new DebitAccount(startBalance, client, this);
        AddBankAccount(account);
        client.AddAccount(account);
        return account;
    }

    @Override
    public IBankAccount CreateCreditAccount(Client client, Float startBalance) {
        IBankAccount account = new CreditAccount(startBalance, client, this);
        AddBankAccount(account);
        client.AddAccount(account);
        return account;
    }

    @Override
    public IBankAccount CreateDepositAccount(Client client, Float startBalance, LocalDate finalDate) {
        IBankAccount account = new DepositAccount(startBalance, client, this, finalDate);
        AddBankAccount(account);
        client.AddAccount(account);
        return account;
    }

    @Override
    public void Update(LocalDate time) {
        for(IBankAccount subscriber : _accounts) {
            subscriber.Update(time);
        }
    }
}
