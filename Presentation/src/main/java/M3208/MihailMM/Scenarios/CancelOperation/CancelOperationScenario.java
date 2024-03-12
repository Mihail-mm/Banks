package M3208.MihailMM.Scenarios.CancelOperation;

import M3208.MihailMM.BankAccounts.IBankAccount;
import M3208.MihailMM.CurrentStates.CurrentAccount.CurrentAccount;
import M3208.MihailMM.CurrentStates.CurrentClient.CurrentClient;
import M3208.MihailMM.IScenario;
import M3208.MihailMM.Transactions.ITransaction;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class CancelOperationScenario implements IScenario {
    private CurrentClient _currentAccount;

    public CancelOperationScenario(CurrentClient currentAccount) {
        _currentAccount = currentAccount;
    }

    @Override
    public String GetName() {
        return "Cancel Operation";
    }

    @Override
    public void Run() {
        Scanner scanner = new Scanner(System.in);
        List<IBankAccount> accountList = _currentAccount.get_client().get_accounts();
        System.out.println("Choose account to cancel operation:");
        for (int i = 0; i < accountList.size(); i++) {
            System.out.println(i + ". " + accountList.get(i).GetId() + " " + accountList.get(i).GetAccountType() + " " + accountList.get(i).GetBalance() + " " + accountList.get(i).GetId());
        }
        int choice = scanner.nextInt();
        IBankAccount account = accountList.get(choice);
        System.out.println("Enter the operation id to cancel:");
        List<ITransaction> transactions = account.GetHistory();
        for(int i = 0; i < transactions.size(); i++) {
            System.out.println(i + ". " + transactions.get(i).GetId() + " " + transactions.get(i).GetTransferAmount() + " " + transactions.get(i).GetStatus());
        }
        int operationId = scanner.nextInt();
        ITransaction transaction = transactions.get(operationId);
        transaction.Undo();
    }
}
