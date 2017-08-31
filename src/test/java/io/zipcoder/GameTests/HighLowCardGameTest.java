package io.zipcoder.GameTests;

import io.zipcoder.Cards.Card;
import io.zipcoder.Cards.Suit;
import io.zipcoder.Cards.Value;
import io.zipcoder.Games.HighLowCardGame;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by W550952 on 31/08/2017.
 */
public class HighLowCardGameTest {
    @Test
    public void compareCardsTestHigher(){
        Card card1 = new Card(Suit.Clubs, Value.EIGHT);
        Card card2 = new Card(Suit.Spades, Value.ACE);

        int expected = 1;

        int actual = HighLowCardGame.compareCards(card1, card2);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void compareCardsTestLower(){
        Card card1 = new Card(Suit.Spades, Value.ACE);
        Card card2 = new Card(Suit.Clubs, Value.EIGHT);

        int expected = -1;

        int actual = HighLowCardGame.compareCards(card1, card2);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void compareCardsTestEqual(){
        Card card1 = new Card(Suit.Spades, Value.ACE);
        Card card2 = new Card(Suit.Spades, Value.ACE);

        int expected = 0;

        int actual = HighLowCardGame.compareCards(card1, card2);

        Assert.assertEquals(expected, actual);
    }
}
