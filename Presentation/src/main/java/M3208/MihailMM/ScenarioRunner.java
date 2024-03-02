package M3208.MihailMM;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


public class ScenarioRunner implements Runnable {
    private List<IScenarioProvider> providers;

    public ScenarioRunner(List<IScenarioProvider> providers) {
        this.providers = providers;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        List<IScenario> scenarios = getScenarios().stream().filter(Objects::nonNull).toList();
        System.out.println("Choose your scenario: ");
        for(int i = 0; i < scenarios.size(); i++) {
            System.out.println(i + ". " + scenarios.get(i).GetName());
        }
        scenarios.get(scanner.nextInt()).Run();
    }

    private List<IScenario> getScenarios() {
        List<IScenario> result = new ArrayList<>();
        for (IScenarioProvider provider : providers) {
            IScenario scenario = provider.TryGetScenario();
            if (scenario != null) {
                result.add(scenario);
            }
        }
        return result;
    }
}