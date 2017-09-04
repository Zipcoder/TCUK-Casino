package io.zipcoder.HandlersTests;

import io.zipcoder.Handlers.BlackjackHandler;
import io.zipcoder.Handlers.HighLowCardHandler;
import io.zipcoder.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by W550952 on 31/08/2017.
 */
public class HighLowCardHandlerTest {
    HighLowCardHandler handler;

    @Before
    public void setUpTests(){
        Player player = new Player("Harry", 500);
        handler = new HighLowCardHandler(player);
        handler.makeStake(250.0);
    }

    @Test
    public void cardHigherTest(){
        double expected = 750.0;

        handler.cardHigher();

        double actual = handler.getPlayer().getBalance();

        Assert.assertEquals(expected, actual, 0.0);
    }

    @Test
    public void cardLowerTest(){
        double expected = 250.0;

        handler.cardLower();

        double actual = handler.getPlayer().getBalance();

        Assert.assertEquals(expected, actual, 0.0);
    }

    @Test
    public void makeStakeTrue(){
        boolean condition = handler.makeStake(250.0);

        Assert.assertTrue(condition);
    }

    @Test
    public void makeStakeFalseNegative(){
        boolean condition = handler.makeStake(-250.0);

        Assert.assertFalse(condition);
    }

    @Test
    public void makeStakeFalseZero(){
        boolean condition = handler.makeStake(0);

        Assert.assertFalse(condition);
    }
}
