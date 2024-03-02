package M3208.MihailMM.Scenarios.CreateAccounts.CreateDepositAccount;

import M3208.MihailMM.CurrentStates.CurrentAccount.CurrentAccount;
import M3208.MihailMM.CurrentStates.CurrentBank.CurrentBank;
import M3208.MihailMM.CurrentStates.CurrentClient.CurrentClient;
import M3208.MihailMM.IScenario;
import M3208.MihailMM.IScenarioProvider;

public class CreateDepositScenarioProvider implements IScenarioProvider {
    private CurrentBank _currentBank;
    private CurrentClient _currentUser;

    public CreateDepositScenarioProvider(CurrentBank currentBank, CurrentClient currentUser) {
        _currentBank = currentBank;
        _currentUser = currentUser;
    }

    @Override
    public IScenario TryGetScenario() {
        if (_currentUser.get_client() == null || _currentBank.getBank() == null) {
            return null;
        }
        return new CreateDepositScenario(_currentBank, _currentUser);
    }
}
