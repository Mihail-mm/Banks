package M3208.MihailMM.Banks;

import M3208.MihailMM.BankAccounts.IBankAccount;
import M3208.MihailMM.Clients.Client;
import M3208.MihailMM.Models.DepositRate;

import java.util.List;

public class BankBuilder {
    private String _name;
    private Float _debitInterest;
    private List<DepositRate> _depositRates;
    private Float _creditCommission;
    private Float _maxWithdraw;
    private Float _maxRemittance;

    public void AddDepositRate(DepositRate depositRate) {
        _depositRates.add(depositRate);
    }

    public void WithName(String name) {
        _name = name;
    }

    public void WithDebitInterest(Float debitInterest) {
        _debitInterest = debitInterest;
    }

    public void WithCreditCommission(Float creditCommission) {
        _creditCommission = creditCommission;
    }

    public void WithMaxWithdraw(Float maxWithdraw) {
        _maxWithdraw = maxWithdraw;
    }

    public void WithMaxRemittance(Float maxRemittance) {
        _maxRemittance = maxRemittance;
    }

    public IBank Build() {
        return new Bank(_name, _debitInterest, _depositRates, _creditCommission, _maxWithdraw, _maxRemittance);
    }
}
