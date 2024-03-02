package M3208.MihailMM.Scenarios.ShowAccounts;

import M3208.MihailMM.CurrentStates.CurrentBank.CurrentBank;
import M3208.MihailMM.CurrentStates.CurrentClient.CurrentClient;
import M3208.MihailMM.IScenario;
import M3208.MihailMM.IScenarioProvider;

public class ShowAccountsScenarioProvider implements IScenarioProvider {
    private CurrentBank _currentBank;
    private CurrentClient _currentClient;

    public ShowAccountsScenarioProvider(CurrentBank currentBank, CurrentClient currentClient) {
        _currentBank = currentBank;
        _currentClient = currentClient;
    }

    @Override
    public IScenario TryGetScenario() {
        IScenario scenario = null;
        if (_currentBank.getBank() != null && _currentClient.get_client() != null) {
            scenario = new ShowAccountsScenario(_currentBank, _currentClient);
        }
        return scenario;
    }
}
