package M3208.MihailMM.Scenarios.CreateAccounts.CreateCreditAccount;

import M3208.MihailMM.CurrentStates.CurrentBank.CurrentBank;
import M3208.MihailMM.CurrentStates.CurrentClient.CurrentClient;
import M3208.MihailMM.IScenario;
import M3208.MihailMM.IScenarioProvider;

public class CreateCreditScenarioProvider implements IScenarioProvider {
    private CurrentBank _currentBank;
    private CurrentClient _currentUser;

    public CreateCreditScenarioProvider(CurrentBank currentBank, CurrentClient currentUser) {
        _currentBank = currentBank;
        _currentUser = currentUser;
    }

    @Override
    public IScenario TryGetScenario() {
        if (_currentUser.get_client() == null || _currentBank.getBank() == null) {
            return null;
        }
        return new CreateCreditScenario(_currentBank, _currentUser);
    }
}
