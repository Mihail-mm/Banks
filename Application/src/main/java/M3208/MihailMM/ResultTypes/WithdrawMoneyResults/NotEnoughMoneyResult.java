package M3208.MihailMM.ResultTypes.WithdrawMoneyResults;

public class NotEnoughMoneyResult implements WithdrawResult{
    @Override
    public String Message() {
        return "Not Enough Money";
    }
}
