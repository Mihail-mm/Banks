package M3208.MihailMM.Scenarios.ChooseUsers;

import M3208.MihailMM.CurrentStates.CurrentBank.CurrentBank;
import M3208.MihailMM.CurrentStates.CurrentClient.CurrentClient;
import M3208.MihailMM.IScenario;
import M3208.MihailMM.IScenarioProvider;
import M3208.MihailMM.Repositories.ClientRepository;

public class ChooseUserScenarioProvider implements IScenarioProvider {
    private CurrentBank _currentBank;
    private CurrentClient _currentClient;
    private ClientRepository _clientRepository;

    public ChooseUserScenarioProvider(CurrentBank currentBank, CurrentClient currentClient, ClientRepository clientRepository) {
        _currentBank = currentBank;
        _currentClient = currentClient;
        _clientRepository = clientRepository;
    }

    @Override
    public IScenario TryGetScenario() {
        if (_currentBank.getBank() != null && _currentClient.get_client() == null) {
            return new ChooseUserScenario(_currentBank, _currentClient, _clientRepository);
        }
        return null;
    }
}
