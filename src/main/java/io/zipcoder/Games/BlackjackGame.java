package io.zipcoder.Games;

import java.util.ArrayList;
import java.util.HashMap;

import io.zipcoder.Bettable;
import io.zipcoder.CardDealer;
import io.zipcoder.Player;
import io.zipcoder.Cards.Card;
import io.zipcoder.Cards.Hand;
import io.zipcoder.Cards.Value;
import io.zipcoder.Handlers.BlackjackHandler;

public class BlackjackGame extends CardGame {
	
	private HashMap<Value, Integer> valueMap;
//	private BlackjackHandler blackjackHandler;
	private static CardDealer cardDealer;
	
	public BlackjackGame(){
		super();
		this.valueMap = new HashMap<Value, Integer>();
		valueMap.put(Value.TWO, 2);
		valueMap.put(Value.THREE, 3);
		valueMap.put(Value.FOUR, 4);
		valueMap.put(Value.FIVE, 5);
		valueMap.put(Value.SIX, 6);
		valueMap.put(Value.SEVEN, 7);
		valueMap.put(Value.EIGHT, 8);
		valueMap.put(Value.NINE, 9);
		valueMap.put(Value.TEN, 10);
		valueMap.put(Value.JACK, 10);
		valueMap.put(Value.QUEEN, 10);
		valueMap.put(Value.KING, 10);
		valueMap.put(Value.ACE, 11);
//		this.blackjackHandler = new BlackjackHandler();
		this.cardDealer = new CardDealer();	
	}
	
	public static int computeHandValue(Hand hand){
		int sumValue = 0;
		for(int i = 0; i < hand.getNumberOfCards(); i++){
			int cardValue = valueMap.get(hand.getHand().get(i).getValue());
			sumValue += cardValue;
		}
		return sumValue;
	}

	@Override
	public int compareCards(Hand hand, Hand otherHand) {
		if(computeHandValue(hand) > computeHandValue(otherHand)){
			return 1;
		}else if(computeHandValue(hand) < computeHandValue(otherHand)){
			return -1;
		}else{
			return 0;
		}
	
	}
	
	public static void playGame(){
		BlackjackHandler blackjackHandler = new BlackjackHandler();
		
		// Deal Two Cards To The Dealer
		cardDealer.dealToDealer(2);
		
		// Dealer Deal Two Cards To The Player Hand
		blackjackHandler.getHand().addCard(cardDealer.deal());
		blackjackHandler.getHand().addCard(cardDealer.deal());
		
		// Compute Dealer Hand Value
		int dealerHandValue = computeHandValue(cardDealer.getHand());
		
		// Compute Player Hand Value
		int playerHandValue = computeHandValue(blackjackHandler.getHand());
		
		if(playerHandValue == 21 && dealerHandValue == 21){
			System.out.println("This is a tie.");
			return;
		}
		
		if(playerHandValue == 21 && dealerHandValue != 21){
			System.out.println("BlackJack! You Win!!!");
			return;
		}
	}
	
	

}
