package M3208.MihailMM.Models;

public class DepositRate {
    private Double UpperLimit;
    private double Interest;

    public DepositRate(Double upperLimit, double interest)
    {
        UpperLimit = upperLimit;
        Interest = interest;
    }


    public Double getUpperLimit() {
        return UpperLimit;
    }

    public double getInterest() {
        return Interest;
    }
}
