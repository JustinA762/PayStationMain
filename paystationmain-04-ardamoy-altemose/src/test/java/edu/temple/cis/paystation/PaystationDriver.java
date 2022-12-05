package edu.temple.cis.paystation;

import java.util.Scanner;
import java.util.Map;

public class PaystationDriver {

    // Main method to run payStation program
    public static void main(String args[]) throws IllegalCoinException
    {
        PayStation Linear1 = new Linear1RateStrategy();
        PayStation rateStrategy = Linear1;
        PayStation Linear2 = new Linear2RateStrategy();
        PayStation Progressive = new ProgressiveRateStrategy();
        PayStation Alternating1 = new Alternating1RateStrategy();
        PayStation Alternating2 = new Alternating2RateStrategy();
        Scanner option = new Scanner(System.in);

        int choice = 0;
        while (!(choice == 7))
        {
            //switch or if else
            //System.out.println("Current rate strategy: "+ rateStrategy);
            System.out.println("________________________");
            System.out.println("1 - Deposit Coins");
            System.out.println("2 - Display");
            System.out.println("3 - Buy Ticket");
            System.out.println("4 - Cancel");
            System.out.println("5 - Empty");
            System.out.println("6 - Change Rate Strategy");
            System.out.println("7 - Quit\n" +
                    "________________________");
            System.out.print("SELECT A CHOICE: ");
            choice = option.nextInt();

            Scanner coins = new Scanner(System.in);
            Scanner rate = new Scanner(System.in);
            switch (choice)
            {
                case 1:
                    // Deposit Coins
                    // One coins at a time!
                    System.out.println("Please enter your amount (5c, 10c, and 25c only!)");
                    System.out.print("Amount: ");
                    int enteredCoin = coins.nextInt();
                    System.out.println("You entered: " + enteredCoin + "c!");
                    rateStrategy.addPayment(enteredCoin, rateStrategy);
                    System.out.println("Time: " + rateStrategy.readDisplay() + " minutes");
                    break;
                case 2:
                    // Display
                    System.out.println("Time: " + rateStrategy.readDisplay() + " minutes");
                    break;
                case 3:
                    // Buy Tickets
                    Receipt receipt;

                    receipt = rateStrategy.buy();
                    System.out.println("You purchased: " + receipt.value() + " minutes");
                    break;
                case 4:
                    // Cancel
                    Map<Integer, Integer> returnCoins = rateStrategy.cancel();
                    System.out.println(returnCoins);
                    break;
                case 5:
                    int money = rateStrategy.empty();
                    System.out.println("Total: " + money + "c");
                    break;
                case 6:
                    // Change Rate Strategy
                    System.out.println("Choose Rate Strategy: ");
                    System.out.println("1 - Alphatown (Linear1)");
                    System.out.println("2 - Betatown (Progressive)");
                    System.out.println("3 - Gammatown (Alternating1)");
                    System.out.println("4 - Deltatown (Linear2)");
                    System.out.println("5 - Omegatown (Alternating2)");
                    System.out.print("SELECT A RATE: ");
                    int chooseRate = rate.nextInt();

                    switch (chooseRate)
                    {
                        case 1:
                            System.out.println("You choose Linear1");
                            rateStrategy = Linear1;
                            break;
                        case 2:
                            System.out.println("You choose Progressive");
                            rateStrategy = Progressive;
                            break;
                        case 3:
                            System.out.println("You choose Alternating1");
                            rateStrategy = Alternating1;
                            break;
                        case 4:
                            System.out.println("You choosing Linear2");
                            rateStrategy = Linear2;
                            break;
                        case 5:
                            System.out.println("You choose Alternating2");
                            rateStrategy = Alternating2;
                            break;
                    }
                    break;
            }
        }
    }
}
