package M3208.MihailMM.Scenarios.ChooseUsers;

import M3208.MihailMM.Clients.Client;
import M3208.MihailMM.CurrentStates.CurrentBank.CurrentBank;
import M3208.MihailMM.CurrentStates.CurrentClient.CurrentClient;
import M3208.MihailMM.IScenario;
import M3208.MihailMM.Repositories.ClientRepository;

import java.util.List;
import java.util.Scanner;

public class ChooseUserScenario implements IScenario {
    private CurrentBank _currentBank;
    private CurrentClient _currentClient;
    private ClientRepository _clientRepository;

    public ChooseUserScenario(CurrentBank currentBank, CurrentClient currentClient, ClientRepository clientRepository) {
        _currentBank = currentBank;
        _currentClient = currentClient;
        _clientRepository = clientRepository;
    }

    @Override
    public String GetName() {
        return "Choose Client";
    }

    @Override
    public void Run() {
        Scanner scanner = new Scanner(System.in);
        List<Client> clients = _clientRepository.getAllClients();
        System.out.println("Choose client: ");
        for (int i = 0; i < clients.size(); ++i) {
            System.out.println(i + ". " + clients.get(i).get_name());
        }
        int clientIndex = scanner.nextInt();
        _currentClient.set_client(clients.get(clientIndex));
    }
}
