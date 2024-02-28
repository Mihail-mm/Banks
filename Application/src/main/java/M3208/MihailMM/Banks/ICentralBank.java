package M3208.MihailMM.Banks;

import M3208.MihailMM.Models.DepositRate;
import M3208.MihailMM.Observer.IPublisher;

import java.util.List;
import java.util.UUID;

public interface ICentralBank extends IPublisher {
    List<IBank> GetBanks();
    IBank CreateBank(String name, Double debitInterest, List<DepositRate> depositRates, Double creditInterest, Double creditLimit);
    void AddBank(IBank bank);
    IBank GetBankByName(String bankName);
    IBank GetBankById(UUID id);

}
