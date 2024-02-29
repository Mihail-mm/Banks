package Tests;
import M3208.MihailMM.Banks.CentralBank;
import M3208.MihailMM.Banks.IBank;
import M3208.MihailMM.Models.DepositRate;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Tests {
    public CentralBank centralBank = CentralBank.getInstance();
    @Test
    public void WithdrawMoney(){
        List<DepositRate> depositRates = new ArrayList<>();
        depositRates.add(new DepositRate(100d, 10));
        IBank bank = centralBank.CreateBank("Sber", 10d, depositRates,10d, 10d);

    }
}
