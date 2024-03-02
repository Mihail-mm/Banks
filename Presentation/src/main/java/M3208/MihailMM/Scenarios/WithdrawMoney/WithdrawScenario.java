package M3208.MihailMM.Scenarios.WithdrawMoney;

import M3208.MihailMM.CurrentStates.CurrentClient.CurrentClient;
import M3208.MihailMM.IScenario;

public class WithdrawScenario implements IScenario {
    private CurrentClient _currentUser;

    public WithdrawScenario(CurrentClient currentUser) {
        _currentUser = currentUser;
    }

    @Override
    public String GetName() {
        return "Withdraw Money";
    }

    @Override
    public void Run() {

    }
}
