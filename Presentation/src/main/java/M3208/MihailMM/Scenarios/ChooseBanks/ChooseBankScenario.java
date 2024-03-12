package M3208.MihailMM.Scenarios.ChooseBanks;

import M3208.MihailMM.Banks.IBank;
import M3208.MihailMM.CurrentStates.CurrentBank.CurrentBank;
import M3208.MihailMM.IScenario;
import M3208.MihailMM.Repositories.BankRepository;

import java.util.List;
import java.util.Scanner;

public class ChooseBankScenario implements IScenario {
    private CurrentBank _currentBank;
    private BankRepository _bankRepository;

    public ChooseBankScenario(CurrentBank currentBank, BankRepository bankRepository) {
        _currentBank = currentBank;
        _bankRepository = bankRepository;
    }

    @Override
    public String GetName() {
        return "Choose bank";
    }

    @Override
    public void Run() {
        Scanner scanner = new Scanner(System.in);
        List<IBank> banks = _bankRepository.getAllBanks();
        System.out.println("Choose bank:");
        for (int i = 0; i < banks.size(); i++) {
            System.out.println(i + 1 + ". " + banks.get(i).GetName());
        }
        int choice = scanner.nextInt();
        _currentBank.setBank(banks.get(choice - 1));
    }
}
