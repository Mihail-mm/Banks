package M3208.MihailMM.ResultTypes.WithdrawMoneyResults;

public class WithdrawMoneyNotVerified implements WithdrawResult{
    @Override
    public String Message() {
        return "Account not verified!";
    }
}
