package M3208.MihailMM;

import M3208.MihailMM.Banks.CentralBank;
import M3208.MihailMM.CurrentStates.CurrentAccount.CurrentAccount;
import M3208.MihailMM.CurrentStates.CurrentBank.CurrentBank;
import M3208.MihailMM.CurrentStates.CurrentClient.CurrentClient;
import M3208.MihailMM.Scenarios.CreateAccounts.CreateCreditAccount.CreateCreditScenarioProvider;
import M3208.MihailMM.Scenarios.CreateAccounts.CreateDebitAccount.CreateDebitScenarioProvider;
import M3208.MihailMM.Scenarios.CreateAccounts.CreateDepositAccount.CreateDepositScenarioProvider;
import M3208.MihailMM.Scenarios.CreateBanks.CreateBankScenarioProvider;
import M3208.MihailMM.Scenarios.CreateClients.CreateClientScenarioProvider;
import M3208.MihailMM.Scenarios.ShowAccounts.ShowAccountsScenarioProvider;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CurrentBank currentBank = new CurrentBank();
        CurrentClient currentClient = new CurrentClient();
        List<IScenarioProvider> providers = new ArrayList<>();

        providers.add(new CreateBankScenarioProvider(currentBank, currentClient));
        providers.add(new CreateClientScenarioProvider(currentBank, currentClient));

        providers.add(new CreateDepositScenarioProvider(currentBank, currentClient));
        providers.add(new CreateDebitScenarioProvider(currentBank, currentClient));
        providers.add(new CreateCreditScenarioProvider(currentBank, currentClient));

        providers.add(new ShowAccountsScenarioProvider(currentBank, currentClient));


        ScenarioRunner runner = new ScenarioRunner(providers);
        runner.run();
        while (true) {
            runner.run();
        }
    }
}