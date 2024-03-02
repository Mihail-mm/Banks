package M3208.MihailMM.Scenarios.CreateAccounts.CreateDepositAccount;

import M3208.MihailMM.CurrentStates.CurrentAccount.CurrentAccount;
import M3208.MihailMM.CurrentStates.CurrentBank.CurrentBank;
import M3208.MihailMM.CurrentStates.CurrentClient.CurrentClient;
import M3208.MihailMM.IScenario;

import java.time.LocalDate;
import java.util.Scanner;

public class CreateDepositScenario implements IScenario {
    private CurrentBank _currentBank;
    private CurrentClient _currentUser;

    public CreateDepositScenario(CurrentBank currentBank, CurrentClient currentUser) {
        _currentBank = currentBank;
        _currentUser = currentUser;
    }

    @Override
    public String GetName() {
        return "Create Deposit Account";
    }

    @Override
    public void Run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write start balance: ");
        Float startBalance = scanner.nextFloat();
        _currentBank.getBank().CreateDepositAccount(_currentUser.get_client(), startBalance, LocalDate.now());
    }

}
