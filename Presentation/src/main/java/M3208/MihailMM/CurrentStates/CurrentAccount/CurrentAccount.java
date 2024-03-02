package M3208.MihailMM.CurrentStates.CurrentAccount;

import M3208.MihailMM.BankAccounts.IBankAccount;

public class CurrentAccount {
    private IBankAccount _account;

    public IBankAccount get_account() {
        return _account;
    }

    public void set_account(IBankAccount _account) {
        this._account = _account;
    }
}
