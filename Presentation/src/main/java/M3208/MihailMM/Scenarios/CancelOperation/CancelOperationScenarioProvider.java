package M3208.MihailMM.Scenarios.CancelOperation;

import M3208.MihailMM.CurrentStates.CurrentBank.CurrentBank;
import M3208.MihailMM.CurrentStates.CurrentClient.CurrentClient;
import M3208.MihailMM.IScenario;
import M3208.MihailMM.IScenarioProvider;

public class CancelOperationScenarioProvider implements IScenarioProvider  {
    private CurrentBank _currentBank;
    private CurrentClient _currentClient;

    public CancelOperationScenarioProvider(CurrentBank currentBank, CurrentClient currentClient) {
        _currentBank = currentBank;
        _currentClient = currentClient;
    }

    @Override
    public IScenario TryGetScenario() {
        if (_currentBank.getBank() != null && _currentClient.get_client() != null) {
            return new CancelOperationScenario(_currentClient);
        }
        return null;
    }
}
