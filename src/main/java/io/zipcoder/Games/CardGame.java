package io.zipcoder.Games;

import io.zipcoder.Cards.Hand;

/**
 * Created by W550952 on 30/08/2017.
 */
public abstract class CardGame {
    public abstract int compareCards(Hand hand, Hand otherHand);
}
