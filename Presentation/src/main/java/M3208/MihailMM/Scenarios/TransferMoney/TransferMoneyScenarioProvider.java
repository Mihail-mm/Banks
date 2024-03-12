package M3208.MihailMM.Scenarios.TransferMoney;

import M3208.MihailMM.CurrentStates.CurrentBank.CurrentBank;
import M3208.MihailMM.CurrentStates.CurrentClient.CurrentClient;
import M3208.MihailMM.IScenario;
import M3208.MihailMM.IScenarioProvider;

public class TransferMoneyScenarioProvider implements IScenarioProvider {
    private CurrentClient _currentClient;
    private CurrentBank _currentBank;

    public TransferMoneyScenarioProvider(CurrentBank currentBank, CurrentClient currentClient) {
        _currentClient = currentClient;
        _currentBank = currentBank;
    }


    @Override
    public IScenario TryGetScenario() {
        if(_currentClient.get_client() != null && _currentBank.getBank() != null) {
            return new TransferMoneyScenario(_currentClient, _currentBank);
        }
        return null;
    }
}
