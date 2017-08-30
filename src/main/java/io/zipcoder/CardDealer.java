package io.zipcoder;

import java.util.ArrayList;

import io.zipcoder.Cards.Card;
import io.zipcoder.Cards.Deck;

public class CardDealer {
	
	private Deck deck;

	public CardDealer(){
		this.deck = new Deck();
	}

	public Deck getDeck() {
		return deck;
	}
	
	public ArrayList<Card> deal(int noOfCardsDeal){
		ArrayList<Card> cardArray = new ArrayList<Card>();
		for(int i = 0; i < noOfCardsDeal; i++){
			cardArray.add(deck.draw());
		}
		return cardArray;
	}

}
