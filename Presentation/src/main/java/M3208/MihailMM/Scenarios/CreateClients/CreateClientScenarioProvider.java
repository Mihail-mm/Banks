package M3208.MihailMM.Scenarios.CreateClients;

import M3208.MihailMM.CurrentStates.CurrentBank.CurrentBank;
import M3208.MihailMM.CurrentStates.CurrentClient.CurrentClient;
import M3208.MihailMM.IScenario;
import M3208.MihailMM.IScenarioProvider;

public class CreateClientScenarioProvider implements IScenarioProvider {
    private CurrentBank _currentBank;
    private CurrentClient _currentClient;
    public CreateClientScenarioProvider(CurrentBank currentBank, CurrentClient currentClient) {
        _currentBank = currentBank;
        _currentClient = currentClient;
    }
    @Override
    public IScenario TryGetScenario() {
        IScenario scenario = null;
        if (_currentBank.getBank() != null && _currentClient.get_client() == null) {
            scenario = new CreateClientScenario(_currentClient);
        }
        return scenario;
    }
}
