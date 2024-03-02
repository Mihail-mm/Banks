package M3208.MihailMM.Transactions;

import M3208.MihailMM.BankAccounts.IBankAccount;
import M3208.MihailMM.Transactions.TransactionStatus.TransactionStatus;

import java.util.UUID;

public interface ITransaction {
    UUID GetId();
    Float GetTransferAmount();
    IBankAccount GetTranslationSource();
    IBankAccount GetToTranslation();
    TransactionStatus GetStatus();
    void SetStatus(TransactionStatus status);
    void Execute();
    void Undo();
}
