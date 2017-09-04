package io.zipcoder.Cards;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Stack;

/**
 * Created by W550952 on 30/08/2017.
 */
public class Deck {
	
	private Collection<Card> deck;
	private int noOfCards;
	
	public Deck(){
		this.deck = new Stack<Card>();
		this.noOfCards = 52;
		
		ArrayList<Card> deckArray = new ArrayList<Card>();
		for(Value value : Value.values()){
			for(Suit suit : Suit.values()){
				//Card card = new Card(suit, value);
				deckArray.add(new Card(suit, value));
			}
		}
		Collections.shuffle(deckArray);
		
		for(Card card : deckArray){
			deck.add(card);
		}
	}

	public Collection<Card> getDeck() {
		return deck;
	}

	public int getNoOfCards() {
		return noOfCards;
	}
	
	public Card draw(){
		this.noOfCards -= 1;
		return ((Stack<Card>) this.deck).pop();
	}
	
	
	
}
