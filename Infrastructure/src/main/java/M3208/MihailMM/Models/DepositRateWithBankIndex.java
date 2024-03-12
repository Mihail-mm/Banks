package M3208.MihailMM.Models;

public class DepositRateWithBankIndex {
    private DepositRate _depositRate;
    private Integer _bankIndex;

    public DepositRateWithBankIndex(DepositRate depositRate, Integer bankIndex) {
        _depositRate = depositRate;
        _bankIndex = bankIndex;
    }

    public DepositRate getDepositRate() {
        return _depositRate;
    }

    public Integer getBankIndex() {
        return _bankIndex;
    }
}
