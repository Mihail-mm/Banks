package M3208.MihailMM.Scenarios.CreateBanks;

import M3208.MihailMM.Banks.CentralBank;
import M3208.MihailMM.Banks.IBank;
import M3208.MihailMM.CurrentStates.CurrentBank.CurrentBank;
import M3208.MihailMM.IScenario;
import M3208.MihailMM.Models.DepositRate;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateBankScenario implements IScenario {
    private CurrentBank _currentBank;

    public CreateBankScenario(CurrentBank currentBank) {
        _currentBank = currentBank;
    }

    @Override
    public String GetName() {
        return "Create Bank";
    }

    @Override
    public void Run() {
        CentralBank centralBank = CentralBank.getInstance();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write the name of the bank: ");
        String name = scanner.next();
        System.out.println("Write Debit Interest : ");
        Float debitInterest = scanner.nextFloat();
        System.out.println("Write Credit Commission : ");
        Float creditCommission = scanner.nextFloat();
        System.out.println("Write max withdraw: ");
        Float maxWithdraw = scanner.nextFloat();
        System.out.println("Write max remittance: ");
        Float maxRemittance = scanner.nextFloat();
        System.out.println("Write count deposit rates: ");
        int countDepositRates = scanner.nextInt();
        List<DepositRate> depositRates = new ArrayList<>();
        for (int i = 0; i < countDepositRates; ++i) {
            System.out.println("Write Lower Limit: ");
            Float lowerLimit = scanner.nextFloat();
            System.out.println("Write Upper Limit: ");
            Float upperLimit = scanner.nextFloat();
            System.out.println("Write Interest: ");
            Float interest = scanner.nextFloat();
            depositRates.add(new DepositRate(lowerLimit, upperLimit, interest));
        }
        IBank bank = centralBank.CreateBank(
                name,
                debitInterest,
                depositRates,
                creditCommission,
                maxWithdraw,
                maxRemittance
        );
        _currentBank.setBank(bank);
    }
}
