package M3208.MihailMM.Scenarios.CreateClients;

import M3208.MihailMM.Clients.Client;
import M3208.MihailMM.CurrentStates.CurrentClient.CurrentClient;
import M3208.MihailMM.IScenario;
import M3208.MihailMM.Models.Passport;

import java.util.Scanner;

public class CreateClientScenario implements IScenario {
    private CurrentClient _currentClient;
    public CreateClientScenario(CurrentClient currentClient) {
        _currentClient = currentClient;
    }
    @Override
    public String GetName() {
        return "Create Client";
    }

    @Override
    public void Run() {
        Scanner scanner = new Scanner(System.in);
        Client.ClientBuilder clientBuilder = new Client.ClientBuilder();
        System.out.println("Write client name: ");
        String name = scanner.next();
        clientBuilder.AddName(name);
        System.out.println("Write client surname: ");
        String surname = scanner.next();
        clientBuilder.AddSurname(surname);
        System.out.println("Write client passport series: ");
        Integer passportSeries = Integer.parseInt(scanner.next());
        System.out.println("Write client passport number: ");
        Integer passportNumber = Integer.parseInt(scanner.next());
        clientBuilder.AddPassport(new Passport(passportSeries, passportNumber));
        Client client = clientBuilder.Build();
        _currentClient.set_client(client);
    }
}
