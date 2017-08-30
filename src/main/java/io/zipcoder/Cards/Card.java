package io.zipcoder.Cards;
/**
 * Created by W550952 on 30/08/2017.
 */

public class Card implements Comparable<Card> {
	
	private Suit suit;
	private Value value;
	
	public Card(Suit suit, Value value) {
		super();
		this.suit = suit;
		this.value = value;
	}

	public Suit getSuit() {
		return suit;
	}

	public Value getValue() {
		return value;
	} 
	
	@Override
	public String toString() {
		return this.value + " of " + this.suit;
	}
	
	public int compareTo(Card card){
		int result = value.compareTo(card.value);
		
		return result;
		
	}
	
}
