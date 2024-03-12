package M3208.MihailMM.ResultTypes.WithdrawMoneyResults;

public class WithdrawMoneyDepositLimit implements WithdrawResult{
    @Override
    public String Message() {
        return "Not withdrawal time!";
    }
}
