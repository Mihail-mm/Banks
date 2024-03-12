package M3208.MihailMM.Scenarios.TransferMoney;

import M3208.MihailMM.BankAccounts.IBankAccount;
import M3208.MihailMM.CurrentStates.CurrentBank.CurrentBank;
import M3208.MihailMM.CurrentStates.CurrentClient.CurrentClient;
import M3208.MihailMM.IScenario;
import M3208.MihailMM.ResultTypes.TransferMoneyResults.TransferMoneySuccess;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class TransferMoneyScenario implements IScenario {
    private CurrentClient _currentClient;
    private CurrentBank _currentBank;

    public TransferMoneyScenario(CurrentClient currentClient, CurrentBank currentBank) {
        _currentClient = currentClient;
        _currentBank = currentBank;
    }

    @Override
    public String GetName() {
        return "Transfer money";
    }

    @Override
    public void Run() {
        Scanner scanner = new Scanner(System.in);
        List<IBankAccount> accounts = _currentClient.get_client().get_accounts();
        System.out.println("Choose account to transfer money from:");
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(i + ". " + accounts.get(i).GetId() + " " + accounts.get(i).GetAccountType() + " " + accounts.get(i).GetBalance() + " " + accounts.get(i).GetId());
        }
        int choice = scanner.nextInt();
        IBankAccount account = accounts.get(choice);

        System.out.println("Enter the amount of money to transfer:");
        float money = scanner.nextFloat();
        System.out.println("Enter the account id to transfer money to:");
        List<IBankAccount> bankAccounts = _currentBank.getBank().GetBankAccounts();
        for(int i = 0; i < bankAccounts.size(); i++) {
            System.out.println(i + ". " + bankAccounts.get(i).GetId() + " " + bankAccounts.get(i).GetAccountType() + " " + bankAccounts.get(i).GetBalance() + " " + bankAccounts.get(i).GetId());
        }
        choice = scanner.nextInt();
        IBankAccount toAccount = bankAccounts.get(choice);
        var result = account.TransferMoney(toAccount, money);
    }
}
