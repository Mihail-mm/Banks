package M3208.MihailMM.BankAccounts;

import M3208.MihailMM.Clients.Client;
import M3208.MihailMM.Observer.ISubscriber;
import M3208.MihailMM.ResultTypes.CancelTransactionResults.CancelTransactionResult;
import M3208.MihailMM.ResultTypes.TransferMoneyResults.TransferMoneyResult;
import M3208.MihailMM.ResultTypes.WithdrawMoneyResults.WithdrawResult;
import M3208.MihailMM.Transactions.ITransaction;

import java.util.List;
import java.util.UUID;

public interface IBankAccount extends ISubscriber {
    String GetAccountType();
    UUID GetId();
    Client GetClient();
    Float GetBalance();
    List<ITransaction> GetHistory();
    WithdrawResult WithdrawMoney(Float money);
    WithdrawResult WithdrawMoney(Float money, IBankAccount account);
    void Replenishment(Float money);
    void Replenishment(Float money, IBankAccount account);
    TransferMoneyResult TransferMoney(IBankAccount toTranslation, Float money);
    CancelTransactionResult CancelTransaction(ITransaction toCancel);
    void DoTransaction(ITransaction transaction);
}
