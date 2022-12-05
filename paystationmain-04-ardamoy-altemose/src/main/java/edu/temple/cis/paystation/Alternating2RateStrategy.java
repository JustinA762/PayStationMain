package edu.temple.cis.paystation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;

public class Alternating2RateStrategy extends PayStationImpl
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

        // Time Variable
        int time;
        // If it is sunday, parking is free and get remaining time of free parking
        if (cal.get(Calendar.DAY_OF_WEEK) == 1)
        {
            System.out.println("Today is the weekend, FREE PARKING!");
            time = minutesInDay - currentMinuteOfDay;

        }
        //if it is saturday, parking is free and get remaining time of free parking
        else if (cal.get(Calendar.DAY_OF_WEEK) == 7)
        {
            System.out.println("Today is the weekend, FREE PARKING!");
            time = (minutesInDay * 2) - currentMinuteOfDay;

        }
        // Otherwise, the weekdays are Linear1 rate strategy
        else
        {
            time = lps.calculateTime(insertedSoFar);
        }
        return time;
    }



}
