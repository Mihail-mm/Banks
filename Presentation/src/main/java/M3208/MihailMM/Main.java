package M3208.MihailMM;

import M3208.MihailMM.CurrentStates.CurrentBank.CurrentBank;
import M3208.MihailMM.CurrentStates.CurrentClient.CurrentClient;
import M3208.MihailMM.Repositories.BankRepository;
import M3208.MihailMM.Repositories.ClientRepository;
import M3208.MihailMM.Scenarios.CancelOperation.CancelOperationScenarioProvider;
import M3208.MihailMM.Scenarios.ChooseBanks.ChooseBankScenarioProvider;
import M3208.MihailMM.Scenarios.ChooseUsers.ChooseUserScenarioProvider;
import M3208.MihailMM.Scenarios.CreateAccounts.CreateCreditAccount.CreateCreditScenarioProvider;
import M3208.MihailMM.Scenarios.CreateAccounts.CreateDebitAccount.CreateDebitScenarioProvider;
import M3208.MihailMM.Scenarios.CreateAccounts.CreateDepositAccount.CreateDepositScenarioProvider;
import M3208.MihailMM.Scenarios.CreateBanks.CreateBankScenarioProvider;
import M3208.MihailMM.Scenarios.CreateClients.CreateClientScenarioProvider;
import M3208.MihailMM.Scenarios.ReplenishmentMoney.ReplenishmentScenarioProvider;
import M3208.MihailMM.Scenarios.ShowAccounts.ShowAccountsScenarioProvider;
import M3208.MihailMM.Scenarios.TransferMoney.TransferMoneyScenarioProvider;
import M3208.MihailMM.Scenarios.WithdrawMoney.WithdrawScenarioProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class
 */

public class Main {
    public static void main(String[] args) {
        CurrentBank currentBank = new CurrentBank();
        CurrentClient currentClient = new CurrentClient();
        BankRepository bankRepository = new BankRepository();
        ClientRepository clientRepository = new ClientRepository();
        List<IScenarioProvider> providers = new ArrayList<>();

        providers.add(new CreateBankScenarioProvider(currentBank, currentClient));
        providers.add(new CreateClientScenarioProvider(currentBank, currentClient));

        providers.add(new CreateDepositScenarioProvider(currentBank, currentClient));
        providers.add(new CreateDebitScenarioProvider(currentBank, currentClient));
        providers.add(new CreateCreditScenarioProvider(currentBank, currentClient));

        providers.add(new ShowAccountsScenarioProvider(currentBank, currentClient));

        providers.add(new WithdrawScenarioProvider(currentBank, currentClient));
        providers.add(new ReplenishmentScenarioProvider(currentBank, currentClient));
        providers.add(new TransferMoneyScenarioProvider(currentBank, currentClient));
        providers.add(new CancelOperationScenarioProvider(currentBank, currentClient));

        providers.add(new ChooseBankScenarioProvider(currentBank, bankRepository));
        providers.add(new ChooseUserScenarioProvider(currentBank, currentClient, clientRepository));


        ScenarioRunner runner = new ScenarioRunner(providers);
        runner.run();
        while (true) {
            runner.run();
        }
    }
}