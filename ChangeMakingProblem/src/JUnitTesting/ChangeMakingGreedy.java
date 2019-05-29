package JUnitTesting;
/**
 * Bowen Kruse
 * 5/28/29
 * Program that uses J Unit to test a change
 * making program that uses the greedy algorithm
 * to choose the largest denomination of common
 * American coins to make change of a given amount
 * based on pseudo code found at
 * https://algoskills.com/g_coinchange.php
 */

import org.junit.Assert;
import org.junit.ComparisonFailure;
import org.junit.Rule;
import org.junit.Test;
import org.hamcrest.CoreMatchers;
import org.junit.rules.ExpectedException;
import java.util.Arrays;
import java.util.Collections;

public class ChangeMakingGreedy {
    public static String GreedyCoins(Integer coinvalue[],int n,int amount) throws AssertionError {
        //Sorts available coins to make change from into reverse order
        Arrays.sort(coinvalue, Collections.reverseOrder());
        //String builder that will build the list of of the largest denominations of coins
        StringBuilder sb = new StringBuilder();

        System.out.println("\nCoin set:\n");
        for( int i=0; i< n; i++ )
            while(amount >= coinvalue[i])
                {
                    //while loop is needed since one coin can be used multiple times
                    amount = amount - coinvalue[i];
                    System.out.print(coinvalue[i]+" ");
                    sb.append(coinvalue[i]+" ");
                }
        return sb.toString();
        }

    @Rule
    public ExpectedException ExceptionRule = ExpectedException.none();

    //Unit Test Area
    @Test
    public void testChangeMakerA() {
        Integer[] coinSet1 = {1,10,1,50,5};
        int amount1 = 25;
        GreedyCoins(coinSet1, coinSet1.length, amount1);

    }
    @Test
    public void testChangeMakerB() {
        Integer[] coinSet2 = {1,5,5,10,25};
        int amount2 = 20;
        Assert.assertThat(GreedyCoins(coinSet2, coinSet2.length, amount2), CoreMatchers.is("10 10 "));
    }
    @Test(expected = AssertionError.class)
    public void testChangeMakerC() {
        ExceptionRule.expectMessage("Comparison Failure and Assertion Error");
        Integer[] coinSet3 = { };
        int amount3 = 29;
        Assert.assertEquals(GreedyCoins(coinSet3, coinSet3.length, amount3), "10 5 10 25");
    }
    @Test
    public void testChangeMakerD() {
        int amount4 = 25;
        Integer[] coinSet4 = {Integer.parseInt("1"), Integer.parseInt("5"),5};
        Assert.assertThat(GreedyCoins(coinSet4, coinSet4.length, amount4), CoreMatchers.is("5 5 5 5 5 "));
    }
    @Test
    public void testChangeMakerE() {
        int amount5 = 1200;
        Integer[] coinSet5 = {1,5,10,25};
        Assert.assertEquals(GreedyCoins(coinSet5, coinSet5.length, amount5), "25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 ");
    }

}
