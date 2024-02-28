package M3208.MihailMM.Clients;

import M3208.MihailMM.BankAccounts.IBankAccount;
import M3208.MihailMM.Clients.ClientStatus.ClientStatus;
import M3208.MihailMM.Clients.ClientStatus.DoubtfulStatus;
import M3208.MihailMM.Clients.ClientStatus.VerifiedStatus;
import M3208.MihailMM.Exceptions.ClientException;
import M3208.MihailMM.Models.Address;
import M3208.MihailMM.Models.Passport;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Client {
    private final UUID _id;
    private final String _name;
    private final String _surname;
    private Optional<Passport> _passport;
    private Optional<Address> _address;
    private List<IBankAccount> _accounts;
    private ClientStatus _status;

    private Client(String name, String surname, Optional<Passport> passport, Optional<Address> address, ClientStatus status) {
        _id = UUID.randomUUID();
        _name = name;
        _surname = surname;
        _passport = passport;
        _address = address;
        _accounts = new ArrayList<>();
        _status = status;
    }

    public UUID get_id() {
        return _id;
    }

    public String get_name() {
        return _name;
    }

    public String get_surname() {
        return _surname;
    }

    public Optional<Passport> get_passport() {
        return _passport;
    }

    public Optional<Address> get_address() {
        return _address;
    }

    public List<IBankAccount> get_accounts() {
        return _accounts;
    }

    public ClientStatus get_status() {
        return _status;
    }

    public void AddAccount(IBankAccount bankAccount) {
        _accounts.add(bankAccount);
    }

    public void AddPassport(Passport passport) {
        _passport = Optional.ofNullable(passport);
        if (_address.isPresent()) {
            _status = new VerifiedStatus();
        }
    }

    public void AddAddress(Address address) {
        _address = Optional.ofNullable(address);
        if (_passport.isPresent()) {
            _status = new VerifiedStatus();
        }
    }


    public static class ClientBuilder {
        private Optional<String> _name;
        private Optional<String> _surname;
        private Optional<Passport> _passport;
        private Optional<Address> _address;
        private ClientStatus _status;

        public void AddName(String name) {
            _name = name.describeConstable();
        }

        public void AddSurname(String surname) {
            _surname = surname.describeConstable();
        }

        public void AddPassport(Passport passport) {
            _passport = Optional.ofNullable(passport);
        }

        public void AddAddress(Address address) {
            _address = Optional.ofNullable(address);
        }

        public Client Build() {
            if (_name.isEmpty() || _surname.isEmpty()) {
                throw new ClientException("Add name or surname");
            }
            if (_passport.isEmpty() || _address.isEmpty()) {
                _status = new DoubtfulStatus();
            } else {
                _status = new VerifiedStatus();
            }
            return new Client(_name.get(), _surname.get(), _passport, _address, _status);
        }

    }
}
