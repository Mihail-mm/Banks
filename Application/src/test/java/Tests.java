import M3208.MihailMM.BankAccounts.IBankAccount;
import M3208.MihailMM.Banks.CentralBank;
import M3208.MihailMM.Banks.IBank;
import M3208.MihailMM.Clients.Client;
import M3208.MihailMM.Models.Address;
import M3208.MihailMM.Models.DepositRate;
import M3208.MihailMM.Models.Passport;
import M3208.MihailMM.ResultTypes.TransferMoneyResults.TransferMoneyDoubtfulResult;
import M3208.MihailMM.ResultTypes.TransferMoneyResults.TransferMoneyResult;
import M3208.MihailMM.ResultTypes.WithdrawMoneyResults.WithdrawMoneyNotVerified;
import M3208.MihailMM.ResultTypes.WithdrawMoneyResults.WithdrawResult;
import M3208.MihailMM.TimeMachine.TimeMachine;
import M3208.MihailMM.Transactions.ITransaction;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {
    private final CentralBank centralBank = CentralBank.getInstance();
    private final TimeMachine timeMachine = new TimeMachine(centralBank);
    @Test
    public void TransferMoneyFromMeToMyFriend() {
        List<DepositRate> depositRates = new ArrayList<>();
        depositRates.add(new DepositRate(0f, 50000f, 3f));
        depositRates.add(new DepositRate(50000.01f, 100000f, 6f));
        depositRates.add(new DepositRate(100000.01f, Float.MAX_VALUE, 9f));

        IBank sber = centralBank.CreateBank(
                "SberBank",
                3f,
                depositRates,
                1000f,
                1000f,
                1000f
        );

        Client.ClientBuilder clientBuilder = new Client.ClientBuilder();

        clientBuilder.AddName("Mihail");
        clientBuilder.AddSurname("Manzhelevskiy");
        clientBuilder.AddPassport(new Passport(1234, 56789));
        clientBuilder.AddAddress(new Address("Holmogorskaya", 4));
        Client I = clientBuilder.Build();

        clientBuilder.AddName("Makar");
        clientBuilder.AddSurname("Druzhinnikov");
        clientBuilder.AddPassport(new Passport(4321, 98765));
        Client MyFriend = clientBuilder.Build();

        IBankAccount IDebit = sber.CreateDebitAccount(I, 1000f);
        IBankAccount MyFriendDebit = sber.CreateDebitAccount(MyFriend, 1000f);

        IDebit.TransferMoney(MyFriendDebit, 200f);

        assertEquals(800, IDebit.GetBalance());
        assertEquals(1200, MyFriendDebit.GetBalance());
    }

    @Test
    public void TransferMoneyFromMeToMyFriendWithoutVerified() {
        List<DepositRate> depositRates = new ArrayList<>();
        depositRates.add(new DepositRate(0f, 50000f, 3f));
        depositRates.add(new DepositRate(50000.01f, 100000f, 6f));
        depositRates.add(new DepositRate(100000.01f, Float.MAX_VALUE, 9f));

        IBank sber = centralBank.CreateBank(
                "SberBank",
                3f,
                depositRates,
                1000f,
                1000f,
                1000f
        );

        Client.ClientBuilder clientBuilder = new Client.ClientBuilder();

        clientBuilder.AddName("Mihail");
        clientBuilder.AddSurname("Manzhelevskiy");
        Client I = clientBuilder.Build();

        clientBuilder.AddName("Makar");
        clientBuilder.AddSurname("Druzhinnikov");
        Client MyFriend = clientBuilder.Build();

        IBankAccount IDebit = sber.CreateDebitAccount(I, 1e6f);
        IBankAccount MyFriendDebit = sber.CreateDebitAccount(MyFriend, 1e6f);

        TransferMoneyResult result = IDebit.TransferMoney(MyFriendDebit, 1e5f);

        assertEquals(TransferMoneyDoubtfulResult.class, result.getClass());
    }

    @Test
    public void WithdrawMoneyFromNotVerifiedAccount() {
        List<DepositRate> depositRates = new ArrayList<>();
        depositRates.add(new DepositRate(0f, 50000f, 3f));
        depositRates.add(new DepositRate(50000.01f, 100000f, 6f));
        depositRates.add(new DepositRate(100000.01f, Float.MAX_VALUE, 9f));

        IBank sber = centralBank.CreateBank(
                "SberBank",
                3f,
                depositRates,
                1000f,
                1000f,
                1000f
        );

        Client.ClientBuilder clientBuilder = new Client.ClientBuilder();

        clientBuilder.AddName("Mihail");
        clientBuilder.AddSurname("Manzhelevskiy");
        Client I = clientBuilder.Build();

        IBankAccount MyDebitAccount = sber.CreateDebitAccount(I, 1e6f);
        IBankAccount MyCreditAccount = sber.CreateCreditAccount(I, 1e6f);
        IBankAccount MyDepositAccount = sber.CreateDepositAccount(I, 1e6f, LocalDate.of(2024, 2, 29));

        WithdrawResult withdrawResult1 = MyDebitAccount.WithdrawMoney(1e5f);
        WithdrawResult withdrawResult2 = MyCreditAccount.WithdrawMoney(1e5f);
        WithdrawResult withdrawResult3 = MyDepositAccount.WithdrawMoney(1e5f);

        assertEquals(WithdrawMoneyNotVerified.class, withdrawResult1.getClass());
        assertEquals(WithdrawMoneyNotVerified.class, withdrawResult2.getClass());
        assertEquals(WithdrawMoneyNotVerified.class, withdrawResult3.getClass());
    }

    @Test
    public void CancelOperation() {
        List<DepositRate> depositRates = new ArrayList<>();
        depositRates.add(new DepositRate(0f, 50000f, 3f));
        depositRates.add(new DepositRate(50000.01f, 100000f, 6f));
        depositRates.add(new DepositRate(100000.01f, Float.MAX_VALUE, 9f));

        IBank sber = centralBank.CreateBank(
                "SberBank",
                3f,
                depositRates,
                1000f,
                1000f,
                1000f
        );

        Client.ClientBuilder clientBuilder = new Client.ClientBuilder();

        clientBuilder.AddName("Mihail");
        clientBuilder.AddSurname("Manzhelevskiy");
        clientBuilder.AddPassport(new Passport(1234, 56789));
        clientBuilder.AddAddress(new Address("Holmogorskaya", 4));
        Client I = clientBuilder.Build();

        clientBuilder.AddName("Makar");
        clientBuilder.AddSurname("Druzhinnikov");
        clientBuilder.AddPassport(new Passport(4321, 98765));
        Client MyFriend = clientBuilder.Build();

        IBankAccount IDebit = sber.CreateDebitAccount(I, 1000f);
        IBankAccount MyFriendDebit = sber.CreateDebitAccount(MyFriend, 1000f);

        IBankAccount MyCredit = sber.CreateCreditAccount(I, 1000f);
        IBankAccount MyFriendCredit = sber.CreateCreditAccount(MyFriend, 1000f);

        IBankAccount MyDeposit = sber.CreateDepositAccount(I, 1000f, LocalDate.of(2024, 2, 29));
        IBankAccount MyFriendDeposit = sber.CreateDepositAccount(MyFriend, 1000f, LocalDate.of(2024, 2, 29));

        IDebit.TransferMoney(MyFriendDebit, 200f);
        MyCredit.TransferMoney(MyFriendCredit, 200f);
        MyDeposit.TransferMoney(MyFriendDeposit, 200f);

        IDebit.GetHistory().get(0).Undo();
        MyCredit.GetHistory().get(0).Undo();
        MyDeposit.GetHistory().get(0).Undo();

        assertEquals(1000, MyFriendDebit.GetBalance());
        assertEquals(1000, IDebit.GetBalance());
        assertEquals(1000, MyCredit.GetBalance());
        assertEquals(1000, MyFriendCredit.GetBalance());
        assertEquals(1000, MyDeposit.GetBalance());
        assertEquals(1000, MyFriendDeposit.GetBalance());
    }
}
