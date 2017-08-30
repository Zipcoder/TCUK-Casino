package io.zipcoder.CardsTests;

import io.zipcoder.Cards.Card;
import io.zipcoder.Cards.Hand;
import io.zipcoder.Cards.Suit;
import io.zipcoder.Cards.Value;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by W550952 on 30/08/2017.
 */
public class HandTest {
    Hand hand;

    @Before
    public void setUpTests(){
        hand = new Hand();
        hand.addCard(new Card(Suit.Spades, Value.ACE));
        hand.addCard(new Card(Suit.Hearts, Value.FIVE));
    }

    @Test
    public void addCardDupeTest(){
        boolean condition = hand.addCard(new Card(Suit.Spades, Value.ACE));

        Assert.assertFalse(condition);
    }

    @Test
    public void addCardTest(){
        boolean condition = hand.addCard(new Card(Suit.Clubs, Value.KING));

        Assert.assertTrue(condition);
    }

    @Test
    public void clearHandTest(){
        int expected = 0;

        hand.clearHand();

        int actual = hand.getNumberOfCards();

        Assert.assertEquals(expected, actual);
    }
}
