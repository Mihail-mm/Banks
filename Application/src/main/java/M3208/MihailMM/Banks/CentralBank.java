package M3208.MihailMM.Banks;

import M3208.MihailMM.Models.DepositRate;
import M3208.MihailMM.Observer.ISubscriber;

import java.time.LocalDate;
import java.util.*;

public class CentralBank implements ICentralBank {
    private static Optional<CentralBank> instance;
    private final List<IBank> _banks;
    private final List<ISubscriber> _subscribers;

    private CentralBank() {
        _subscribers = new ArrayList<>();
        _banks = new ArrayList<>();
    }

    public static CentralBank getInstance() {
        if (instance.isEmpty()) {
            instance = Optional.of(new CentralBank());
        }
        return instance.get();
    }

    @Override
    public List<IBank> GetBanks() {
        return Collections.unmodifiableList(_banks);
    }

    @Override
    public IBank CreateBank(String name, Double debitInterest, List<DepositRate> depositRates, Double creditInterest, Double creditLimit) {
        return new Bank(name, debitInterest, depositRates, creditInterest, creditLimit);
    }

    @Override
    public void AddBank(IBank bank) {
        _banks.add(bank);
        _subscribers.add(bank);
    }

    @Override
    public IBank GetBankByName(String bankName) {
        if (bankName == null) {
            throw new IllegalArgumentException();
        }

        for (IBank bank : _banks) {
            if (bank.GetName().equals(bankName)) {
                return bank;
            }
        }

        throw new IllegalArgumentException();
    }

    @Override
    public IBank GetBankById(UUID id) {
        if (id == null) {
            throw new NullPointerException();
        }

        for (IBank bankImpl : _banks) {
            if (bankImpl.GetId().equals(id)) {
                return bankImpl;
            }
        }

        throw new IllegalArgumentException();
    }

    @Override
    public void AddSubscriber(ISubscriber subscriber) {
        _subscribers.add(subscriber);
    }

    @Override
    public void RemoveSubscriber(ISubscriber subscriber) {
        _subscribers.remove(subscriber);
    }

    @Override
    public void NotifySubscribers(LocalDate time) {
        for(IBank bank : _banks) {
            bank.Update(time);
        }
    }
}
