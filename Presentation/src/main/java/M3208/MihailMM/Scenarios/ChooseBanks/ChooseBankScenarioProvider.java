package M3208.MihailMM.Scenarios.ChooseBanks;

import M3208.MihailMM.CurrentStates.CurrentBank.CurrentBank;
import M3208.MihailMM.IScenario;
import M3208.MihailMM.IScenarioProvider;
import M3208.MihailMM.Repositories.BankRepository;

public class ChooseBankScenarioProvider implements IScenarioProvider {
    private CurrentBank _currentBank;
    private BankRepository _bankRepository;

    public ChooseBankScenarioProvider(CurrentBank currentBank, BankRepository bankRepository) {
        _currentBank = currentBank;
        _bankRepository = bankRepository;
    }

    @Override
    public IScenario TryGetScenario() {
        if (_currentBank.getBank() == null) {
            return new ChooseBankScenario(_currentBank, _bankRepository);
        }
        return null;
    }
}
