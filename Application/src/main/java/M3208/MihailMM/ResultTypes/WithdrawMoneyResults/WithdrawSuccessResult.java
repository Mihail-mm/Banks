package M3208.MihailMM.ResultTypes.WithdrawMoneyResults;

public class WithdrawSuccessResult implements WithdrawResult{
    @Override
    public String Message() {
        return "Success withdraw money!";
    }
}
