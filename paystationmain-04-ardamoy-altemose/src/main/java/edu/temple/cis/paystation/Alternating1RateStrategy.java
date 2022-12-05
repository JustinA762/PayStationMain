package edu.temple.cis.paystation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
public class Alternating1RateStrategy extends PayStationImpl
{
    @Override
    public int calculateTime(int insertedSoFar) {
        return this.calculateTimeForCal(insertedSoFar, Calendar.getInstance());
    }

    public int calculateTimeForCal(int insertedSoFar, Calendar cal)  {
        // Dates

        // Get the current time in minutes
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);

        int currentMinuteOfDay = ((hour * 60) + minute);
        int minutesInDay = 1440;
        // Rate Strategies
        PayStation lps = new Linear1RateStrategy();
        PayStation pps = new ProgressiveRateStrategy();

        // Time Variable
        int time;
        // Weekends are linear1 rate strategy
        if (cal.get(Calendar.DAY_OF_WEEK) == 1 || cal.get(Calendar.DAY_OF_WEEK) == 7)
        {
            time = lps.calculateTime(insertedSoFar);
        }
        // Weekdays are progressive rate strategy
        else
        {
            time = pps.calculateTime(insertedSoFar);
        }
        return time;
    }
}
