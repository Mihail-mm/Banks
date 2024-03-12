package M3208.MihailMM.Scenarios.ShowAccounts;

import M3208.MihailMM.BankAccounts.IBankAccount;
import M3208.MihailMM.CurrentStates.CurrentBank.CurrentBank;
import M3208.MihailMM.CurrentStates.CurrentClient.CurrentClient;
import M3208.MihailMM.IScenario;

import java.util.List;

public class ShowAccountsScenario implements IScenario {
    private CurrentBank _currentBank;
    private CurrentClient _currentClient;

    public ShowAccountsScenario(CurrentBank currentBank, CurrentClient currentClient) {
        _currentBank = currentBank;
        _currentClient = currentClient;
    }

    @Override
    public String GetName() {
        return "Show Accounts";
    }

    @Override
    public void Run() {
        List<IBankAccount> accounts = _currentClient.get_client().get_accounts();
        for (int i = 0; i < accounts.size(); ++i) {
            IBankAccount bankAccount = accounts.get(i);
            System.out.print("Type : " + bankAccount.getClass() + "\n");
            System.out.print("BankId : " + bankAccount.GetId() + "\n");
            System.out.print("Balance : " + bankAccount.GetBalance() + "\n");
        }
    }
}
