package io.zipcoder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by W550952 on 30/08/2017.
 */
public class PlayerTest {
    Player player;

    @Before
    public void setUpTests(){
        player = new Player("Harry", 500);
    }

    @Test
    public void reduceBalancePositiveTest(){
        boolean condition = player.reduceBalance(250);

        Assert.assertTrue(condition);
    }

    @Test
    public void increaseBalancePositiveTest(){
        boolean condition = player.increaseBalance(250);

        Assert.assertTrue(condition);
    }

    @Test
    public void reduceBalanceNegativeTest(){
        boolean condition = player.reduceBalance(-250);

        Assert.assertFalse(condition);
    }

    @Test
    public void increaseBalanceNegativeTest(){
        boolean condition = player.increaseBalance(-250);

        Assert.assertFalse(condition);
    }

    @Test
    public void reduceBalanceZeroTest(){
        boolean condition = player.reduceBalance(0);

        Assert.assertFalse(condition);
    }

    @Test
    public void increaseBalanceZeroTest(){
        boolean condition = player.increaseBalance(0);

        Assert.assertFalse(condition);
    }
}
