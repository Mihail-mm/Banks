package M3208.MihailMM.Banks;

import M3208.MihailMM.BankAccounts.IBankAccount;
import M3208.MihailMM.Models.DepositRate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Bank implements IBank{
    private UUID _id;
    private String _name;
    private List<IBankAccount> _accounts;
    private Double _debitInterest;
    private List<DepositRate> _depositRates;
    private Double _creditInterest;
    private Double _creditLimit;

    public Bank(String name, Double debitInterest, List<DepositRate> depositRates, Double creditInterest, Double creditLimit) {
        _name = name;
        _debitInterest = debitInterest;
        _depositRates = depositRates;
        _creditInterest = creditInterest;
        _creditLimit = creditLimit;
        _accounts = new ArrayList<>();
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
    public double GetDebitInterest() {
        return _debitInterest;
    }

    @Override
    public List<DepositRate> GetDepositRates() {
        return _depositRates;
    }

    @Override
    public double GetCreditInterest() {
        return _creditInterest;
    }

    @Override
    public Double GetCreditLimit() {
        return _creditLimit;
    }

    @Override
    public List<IBankAccount> Accounts() {
        return _accounts;
    }

    @Override
    public List<String> ClientOfAccount() {
        return null;
    }

    @Override
    public void AddBankAccount(IBankAccount bankAccount) {
        _accounts.add(bankAccount);
    }

    @Override
    public IBankAccount GetBankAccount(String clientName) {
        return _accounts.stream().filter(i -> i.GetClient().get_name().equals(clientName)).findFirst().orElse(null);
    }

    @Override
    public void Update(LocalDate time) {
        for(IBankAccount subscriber : _accounts) {
            subscriber.Update(time);
        }
    }
}
