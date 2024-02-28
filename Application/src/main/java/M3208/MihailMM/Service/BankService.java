package M3208.MihailMM.Service;

import M3208.MihailMM.BankAccounts.IBankAccount;
import M3208.MihailMM.Banks.Bank;
import M3208.MihailMM.Banks.CentralBank;
import M3208.MihailMM.Banks.IBank;
import M3208.MihailMM.Clients.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BankService {
    private List<Client> _clients;
    private CentralBank MainBank;

    public BankService()
    {
        MainBank = CentralBank.getInstance();
        _clients = new ArrayList<>();
    }



    public List<IBank> Banks() {
        return MainBank.GetBanks();
    }

    public List<String> BanksName()
    {
        return MainBank.GetBanks().stream().map(IBank::GetName).toList();
    }

    public void AddClient(Client client)
    {
        _clients.add(client);
    }

    public void AddBank(IBank bank)
    {
        MainBank.AddBank(bank);
    }

    public void AddBankAccount(IBankAccount account, IBank bank)
    {
        bank.AddBankAccount(account);
    }

    public Client GetClient(String id)
    {
        Client client = _clients.stream().filter(i -> i.get_id() == UUID.fromString(id)).findFirst().orElse(null);
        return client;
    }

    public IBank GetBank(String name)
    {
        IBank bank = MainBank.GetBanks().stream().filter(i -> i.GetName().equals(name)).findFirst().get();
        return bank;
    }
}
