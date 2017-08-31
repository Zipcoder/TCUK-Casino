package io.zipcoder.Handlers;

import io.zipcoder.CardDealer;
import io.zipcoder.Cards.Hand;

public abstract class CardHandler extends Handler {

	protected Hand hand;
	private CardDealer cardDealer;
	
	
	
	public CardHandler() {
		this.hand = new Hand();
		this.cardDealer = new CardDealer();
	}
	public Hand getHand() {
		return hand;
	}
	public void setHand(Hand hand) {
		this.hand = hand;
	}

}
