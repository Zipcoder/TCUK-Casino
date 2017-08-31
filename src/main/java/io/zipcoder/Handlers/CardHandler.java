package io.zipcoder.Handlers;

import io.zipcoder.CardDealer;
import io.zipcoder.Cards.Hand;

public abstract class CardHandler extends Handler {

	protected Hand hand;
	private CardDealer cardDealer;

	public Hand getHand() {
		return hand;
	}
	public void setHand(Hand hand) {
		this.hand = hand;
	}




}
