package io.zipcoder.Cards;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by W550952 on 30/08/2017.
 */
public class Hand {
    List<Card> hand;
    int numberOfCards;

    public Hand(){
        hand = new ArrayList<Card>();
        numberOfCards = 0;
    }

    public int getNumberOfCards(){
        return numberOfCards;
    }

    public boolean addCard(Card card){
        if (card != null && !containsCard(card)){
            hand.add(card);
            numberOfCards++;
            return true;
        } else if (card == null){
            System.out.println("That card is null");
            return false;
        } else {
            System.out.println("That card is already in the hand");
            return false;
        }
    }

    private boolean containsCard(Card input){
        for (Card card : hand){
            if (card.getSuit().equals(input.getSuit()) && card.getValue().equals(input.getValue())){
                return true;
            }
        }
        return false;
    }
}
