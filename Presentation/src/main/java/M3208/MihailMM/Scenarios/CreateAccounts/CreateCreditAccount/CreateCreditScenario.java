package M3208.MihailMM.Scenarios.CreateAccounts.CreateCreditAccount;

import M3208.MihailMM.CurrentStates.CurrentBank.CurrentBank;
import M3208.MihailMM.CurrentStates.CurrentClient.CurrentClient;
import M3208.MihailMM.IScenario;

import java.util.Scanner;

public class CreateCreditScenario implements IScenario {
    private CurrentBank _currentBank;
    private CurrentClient _currentUser;

    public CreateCreditScenario(CurrentBank currentBank, CurrentClient currentUser) {
        _currentBank = currentBank;
        _currentUser = currentUser;
    }

    @Override
    public String GetName() {
        return "Create Credit Account";
    }

    @Override
    public void Run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write start balance: ");
        Float startBalance = scanner.nextFloat();
        _currentBank.getBank().CreateCreditAccount(_currentUser.get_client(), startBalance);
    }
}
