package M3208.MihailMM.Scenarios.WithdrawMoney;

import M3208.MihailMM.IScenario;
import M3208.MihailMM.IScenarioProvider;

public class WithdrawScenarioProvider implements IScenarioProvider {
    private

    @Override
    public IScenario TryGetScenario() {
        return new WithdrawScenario();
    }
}
