package io.zipcoder.Cards;

import java.util.ArrayList;
import java.util.List;

import io.zipcoder.UserInterface;

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
            UserInterface.sendUpwardsToUser("That card is null");
            return false;
        } else {
            UserInterface.sendUpwardsToUser("That card is already in the hand");
            return false;
        }
    }

    public List<Card> getHand(){
        return hand;
    }

    private boolean containsCard(Card input){
        for (Card card : hand){
            if (card.getSuit().equals(input.getSuit()) && card.getValue().equals(input.getValue())){
                return true;
            }
        }
        return false;
    }

    public void clearHand(){
        hand = new ArrayList<Card>();
        numberOfCards = 0;
    }
    
    @Override
    public String toString(){
    	String output = "";
    	for(int i = 0; i < numberOfCards; i++){
    		output += hand.get(i).getSuit() + "-" + hand.get(i).getValue() + "  ";
    	}
    	return output;
    }
}
