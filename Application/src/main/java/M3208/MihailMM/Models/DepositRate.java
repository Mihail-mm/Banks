package M3208.MihailMM.Models;

public class DepositRate {
    private Float UpperLimit;
    private Float LowerLimit;
    private Float Interest;

    public DepositRate(Float lowerLimit, Float upperLimit, Float interest)
    {
        UpperLimit = upperLimit;
        LowerLimit = lowerLimit;
        Interest = interest;
    }


    public Float getUpperLimit() {
        return UpperLimit;
    }

    public Float getInterest() {
        return Interest;
    }

    public Float getLowerLimit() {
        return LowerLimit;
    }
}
