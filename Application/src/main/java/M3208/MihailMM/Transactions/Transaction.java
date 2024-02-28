package M3208.MihailMM.Transactions;

import M3208.MihailMM.BankAccounts.IBankAccount;
import M3208.MihailMM.Transactions.TransactionStatus.TransactionStatus;

import java.util.UUID;

public class Transaction implements ITransaction{
    private UUID _id;
    private Double _transferAmount;
    private IBankAccount _translationSource;
    private IBankAccount _toTranslation;
    private TransactionStatus _status;

    public Transaction(Double transferAmount, IBankAccount translationSource, IBankAccount toTranslation) {
        _transferAmount = transferAmount;
        _translationSource = translationSource;
        _toTranslation = toTranslation;
    }


    @Override
    public UUID GetId() {
        return _id;
    }

    @Override
    public Double GetTransferAmount() {
        return _transferAmount;
    }

    @Override
    public IBankAccount GetTranslationSource() {
        return _translationSource;
    }

    @Override
    public IBankAccount GetToTranslation() {
        return _toTranslation;
    }

    @Override
    public TransactionStatus GetStatus() {
        return _status;
    }

    @Override
    public void SetStatus(TransactionStatus status) {
        _status = status;
    }

    @Override
    public void Execute() {
        _translationSource.DoTransaction(this);
        _toTranslation.DoTransaction(this);
    }

    @Override
    public void Undo() {
        _translationSource.CancelTransaction(this);
        _toTranslation.CancelTransaction(this);

    }
}
