package M3208.MihailMM.BankAccounts;

import M3208.MihailMM.Clients.Client;
import M3208.MihailMM.Observer.ISubscriber;
import M3208.MihailMM.ResultTypes.CancelTransactionResults.CancelTransactionResult;
import M3208.MihailMM.ResultTypes.TransferMoneyResults.TransferMoneyResult;
import M3208.MihailMM.ResultTypes.WithdrawMoneyResults.WithdrawResult;
import M3208.MihailMM.Transactions.ITransaction;

import java.util.UUID;

public interface IBankAccount extends ISubscriber {
    UUID GetId();
    Client GetClient();
    Float GetBalance();
    WithdrawResult WithdrawMoney(Float money);
    void Replenishment(Float money);
    WithdrawResult WithdrawMoney(Float money, IBankAccount toTranslation);
    void Replenishment(Float money, IBankAccount fromTranslation);
    TransferMoneyResult TransferMoney(IBankAccount toTranslation, Float money);
    CancelTransactionResult CancelTransaction(UUID idTransaction);
    void DoTransaction(ITransaction transaction);
}
