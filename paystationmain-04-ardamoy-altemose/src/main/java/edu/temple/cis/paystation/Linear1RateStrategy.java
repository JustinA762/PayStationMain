package edu.temple.cis.paystation;

public class Linear1RateStrategy extends PayStationImpl {
    @Override
    public int calculateTime(int amount){ return (amount * 2) / 5;

    }
}
