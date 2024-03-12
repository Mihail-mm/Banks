package M3208.MihailMM.Scenarios.ReplenishmentMoney;

import M3208.MihailMM.BankAccounts.IBankAccount;
import M3208.MihailMM.CurrentStates.CurrentBank.CurrentBank;
import M3208.MihailMM.CurrentStates.CurrentClient.CurrentClient;
import M3208.MihailMM.IScenario;

import java.util.List;
import java.util.Scanner;

public class ReplenishmentScenario implements IScenario {
    private CurrentBank _currentBank;
    private CurrentClient _currentClient;

    public ReplenishmentScenario(CurrentBank currentBank, CurrentClient currentClient) {
        _currentBank = currentBank;
        _currentClient = currentClient;
    }

    @Override
    public String GetName() {
        return "Replenishment";
    }

    @Override
    public void Run() {
        Scanner scanner = new Scanner(System.in);
        List<IBankAccount> bankAccounts = _currentClient.get_client().get_accounts();
        System.out.println("Choose account to replenish: ");
        for (int i = 0; i < bankAccounts.size(); ++i) {
            IBankAccount bankAccount = bankAccounts.get(i);
            System.out.print(i + ". Type : " + bankAccount.GetAccountType() + "; ");
            System.out.print("Id: : " + bankAccount.GetId() + "; ");
            System.out.print("Balance : " + bankAccount.GetBalance() + "\n");
        }
        int choice = scanner.nextInt();
        IBankAccount account = bankAccounts.get(choice);
        System.out.println("Write amount to replenish: ");
        Float amount = scanner.nextFloat();
        account.Replenishment(amount);
    }
}
