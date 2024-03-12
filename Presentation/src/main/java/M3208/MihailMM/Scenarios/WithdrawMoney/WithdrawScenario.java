package M3208.MihailMM.Scenarios.WithdrawMoney;

import M3208.MihailMM.BankAccounts.IBankAccount;
import M3208.MihailMM.CurrentStates.CurrentClient.CurrentClient;
import M3208.MihailMM.IScenario;

import java.util.List;
import java.util.Scanner;

public class WithdrawScenario implements IScenario {
    private CurrentClient _currentUser;

    public WithdrawScenario(CurrentClient currentUser) {
        _currentUser = currentUser;
    }

    @Override
    public String GetName() {
        return "Withdraw Money";
    }

    @Override
    public void Run() {
        Scanner scanner = new Scanner(System.in);
        List<IBankAccount> accounts = _currentUser.get_client().get_accounts();
        System.out.println("Choose account to withdraw from: ");
        for (int i = 0; i < accounts.size(); ++i) {
            IBankAccount bankAccount = accounts.get(i);
            System.out.print(i + ". Type : " + bankAccount.GetAccountType() + "; ");
            System.out.print("Id: : " + bankAccount.GetId() + "; ");
            System.out.print("Balance : " + bankAccount.GetBalance() + "\n");
        }
        int accountIndex = scanner.nextInt();
        IBankAccount account = accounts.get(accountIndex);
        System.out.println("Write amount to withdraw: ");
        Float amount = scanner.nextFloat();
        var result = account.WithdrawMoney(amount);
        System.out.println(result.Message());
    }
}
