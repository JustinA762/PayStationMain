package edu.temple.cis.paystation;

public class ProgressiveRateStrategy extends PayStationImpl {
    @Override
    public int calculateTime(int insertedSoFar)
    {
        int time = 0;

        // Less than an hour
        if (insertedSoFar < 150)
        {
            time = insertedSoFar * 2 / 5;
        }
        // Between 1st hour and 2nd hour
        else if (150 <= insertedSoFar && insertedSoFar < 350)
        {
            time = (insertedSoFar - 150) * 3 / 10 + 60;
        }
        // Greater than 2 hours
        else
        {
            time = (insertedSoFar - 350) / 5 + 120;
        }
        return time;
    }

}
