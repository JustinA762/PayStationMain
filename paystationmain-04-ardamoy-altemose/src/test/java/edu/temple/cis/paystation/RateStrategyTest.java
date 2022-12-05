package edu.temple.cis.paystation;
import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

public class RateStrategyTest {
    PayStation ps_Linear1;
    PayStation ps_Progessive;
    PayStation ps_Alternating1;
    PayStation ps_Linear2;
    PayStation ps_Alternating2;
    PayStation ps;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setup() {
        ps_Linear1 = new Linear1RateStrategy();
        ps_Progessive = new ProgressiveRateStrategy();
        ps_Alternating1 = new Alternating1RateStrategy();
        ps_Linear2 = new Linear2RateStrategy();
        ps_Alternating2 = new Alternating2RateStrategy();
        ps = new PayStationImpl();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testLinear1Rate()
    {
        int amount = 80;
        int time = ps_Linear1.calculateTime(amount);
        // System.out.println(time);
        assertEquals("The total time should be 32", 32, time);
    }

    @Test
    public void testProgressiveRateBelow150()
    {
        int amount = 100;
        int time = ps_Progessive.calculateTime(amount);
        // System.out.println(time);
        assertEquals("The total time should be 50", 40, time);
    }
    @Test
    public void testProgressiveRateAbove150Below350()
    {
        int amount = 200;
        int time = ps_Progessive.calculateTime(amount);
        // System.out.println(time);
        assertEquals("The total time should be 75", 75, time);
    }
    @Test
    public void testProgressiveRateAbove350()
    {
        int amount = 400;
        int time = ps_Progessive.calculateTime(amount);
        // System.out.println(time);
        assertEquals("The total time should be 130", 130, time);
    }

    @Test
    public void testAlternating1RateDuringWeekdays()
    {
        Calendar c = Calendar.getInstance();
        c.set(2022, 8, 19, 10, 20);
        int amount = 200;
        System.out.println(c.getTime());
        int time = ps_Alternating1.calculateTimeForCal(amount, c);
        assertEquals("Weekdays should be Progressive", 75, time);
    }

    @Test
    public void testAlternating1RateDuringWeekends()
    {
        Calendar c = Calendar.getInstance();
        c.set(2022, 8, 17, 10, 20);
        int amount = 200;
        System.out.println(c.getTime());
        int time = ps_Alternating1.calculateTimeForCal(amount, c);
        assertEquals("Weekends should be Linear1", 80, time);
    }

    @Test
    public void testLinear2Rate()
    {
        int amount = 50;
        int time = ps_Linear2.calculateTime(amount);
        assertEquals("The total time should be 10", 10, time);
    }

    @Test
    public void testAlternating2RateWeekend()
    {
        Calendar c = Calendar.getInstance();
        c.set(2022, 8, 17, 10, 20);
        int amount = 0;
        System.out.println(c.getTime());
        int time = ps_Alternating2.calculateTimeForCal(amount, c);
            assertEquals("The total time should be the amount of time till 12 am monday", 2260, time);
    }

    @Test
    public void testAlternating2Weekday()
    {
        Calendar c = Calendar.getInstance();
        c.set(2022, 8, 19, 10, 20);
        System.out.println(c.getTime());
        int amount = 80;
        int time = ps_Alternating2.calculateTimeForCal(amount, c);
        assertEquals("The total time should be 32", 32, time);
    }

}
