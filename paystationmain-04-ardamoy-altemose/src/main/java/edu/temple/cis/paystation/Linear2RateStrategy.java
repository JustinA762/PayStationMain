package edu.temple.cis.paystation;

public class Linear2RateStrategy extends PayStationImpl{
    @Override
    public int calculateTime(int amount)
    {
        return (amount / 5);
    }
}
