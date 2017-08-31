package io.zipcoder;

import java.util.ArrayList;

import io.zipcoder.Cards.Card;
import io.zipcoder.Cards.Deck;
import io.zipcoder.Cards.Hand;

public class CardDealer {
	
	private Deck deck;
	private Hand dealerHand;

	public CardDealer(){
		this.deck = new Deck();
		this.dealerHand = new Hand();
	}

	public Deck getDeck() {
		return deck;
	}
	
	public Hand getHand() {
		return dealerHand;
	}
	
	// Deal Card To Player
	public Card deal(){
		return deck.draw();
	}
	
	// Deal Cards To Player
	public ArrayList<Card> deal(int noOfCardsDeal){
		ArrayList<Card> cardArray = new ArrayList<Card>();
		for(int i = 0; i < noOfCardsDeal; i++){
			cardArray.add(deck.draw());
		}
		return cardArray;
	}
	
	public void dealToDealer(){
		this.dealerHand.getHand().add(deck.draw());
	}
	
	public void dealToDealer(int noOfCardsDeal){
		for(int i = 0; i < noOfCardsDeal; i++){
			this.dealerHand.getHand().add(deck.draw());
		}
	}

}
