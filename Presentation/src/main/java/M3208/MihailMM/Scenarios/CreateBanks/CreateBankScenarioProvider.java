package M3208.MihailMM.Scenarios.CreateBanks;

import M3208.MihailMM.CurrentStates.CurrentBank.CurrentBank;
import M3208.MihailMM.CurrentStates.CurrentClient.CurrentClient;
import M3208.MihailMM.IScenario;
import M3208.MihailMM.IScenarioProvider;

public class CreateBankScenarioProvider implements IScenarioProvider {
    private CurrentBank _currentBank;
    private CurrentClient _currentClient;

    public CreateBankScenarioProvider(CurrentBank currentBank, CurrentClient currentClient) {
        _currentBank = currentBank;
        _currentClient = currentClient;
    }

    @Override
    public IScenario TryGetScenario() {
        IScenario scenario = null;
        if (_currentBank.getBank() == null && _currentClient.get_client() == null) {
            scenario = new CreateBankScenario(_currentBank);
        }
        return scenario;
    }
}

