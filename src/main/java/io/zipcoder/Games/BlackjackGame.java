package io.zipcoder.Games;

import java.util.ArrayList;
import java.util.HashMap;

import io.zipcoder.Bettable;
import io.zipcoder.CardDealer;
import io.zipcoder.Player;
import io.zipcoder.UserInterface;
import io.zipcoder.Cards.Card;
import io.zipcoder.Cards.Hand;
import io.zipcoder.Cards.Value;
import io.zipcoder.Handlers.BlackjackHandler;

public class BlackjackGame extends CardGame {
	
	private static HashMap<Value, Integer> valueMap;
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

//	@Override
//	public int compareCards(Hand hand, Hand otherHand) {
//		if(computeHandValue(hand) > computeHandValue(otherHand)){
//			return 1;
//		}else if(computeHandValue(hand) < computeHandValue(otherHand)){
//			return -1;
//		}else{
//			return 0;
//		}
//	
//	}
	
	// Method To Show The Card
	public static void showHand(Hand hand){
		String output = hand.getHand().toString();
		System.out.println(output + " " + computeHandValue(hand) + " point.");
	}
	
	// Hit A Card
	public static void hit(Hand hand){
		hand.addCard(cardDealer.deal());
	}
	
	public void playGame(Player player){
		BlackjackHandler blackjackHandler = new BlackjackHandler(player);
		
		// Ask For The Amount Of Stake
		System.out.println("How much would you like to bet?");
		double amount = UserInterface.getUserInputDouble();
		
		// Bet At The Beginning Of The Game
		blackjackHandler.makeStake(amount);
		
		// Deal Two Cards To The Dealer
		cardDealer.dealToDealer(2);
		
		// Dealer Deal Two Cards To The Player Hand
		blackjackHandler.getHand().addCard(cardDealer.deal());
		blackjackHandler.getHand().addCard(cardDealer.deal());
		System.out.println("Dealer's Cards:");
		showHand(cardDealer.getHand());
		System.out.println("Player's Cards:");
		showHand(blackjackHandler.getHand());
		
		// Compute Dealer Hand Value
		int dealerHandValue = computeHandValue(cardDealer.getHand());
		
		// Compute Player Hand Value
		int playerHandValue = computeHandValue(blackjackHandler.getHand());
		
		// BlackJack At The First Two Cards
		if(playerHandValue == 21 && dealerHandValue == 21){
//			System.out.println("Dealer's Cards:");
//			showHand(cardDealer.getHand());
//			System.out.println("Player's Cards:");
//			showHand(blackjackHandler.getHand());
			System.out.println("This is a tie. Game Over.");
			System.out.println(player.getName() + ": " + player.getBalance() + ". See You Next Time!");
			return;
		}
		
		if(playerHandValue == 21 && dealerHandValue != 21){
//			System.out.println("Dealer's Cards:");
//			showHand(cardDealer.getHand());
//			System.out.println("Player's Cards:");
//			showHand(blackjackHandler.getHand());
			System.out.println("BlackJack! You Win!!!");
			blackjackHandler.hitSuccess();
			System.out.println(player.getName() + ": " + player.getBalance() + ". Well Done! Play More!");
			return;
		}
		
		// Player Decision(handValue < 21)
		while(true){
			System.out.println("Do you want to hit? Please answer Y/N");
			String userDecision = UserInterface.getUserInputString();
			if(userDecision.equalsIgnoreCase("Y")){
				hit(blackjackHandler.getHand());
				if(computeHandValue(blackjackHandler.getHand()) > 21){
					System.out.println("Dealer's Cards:");
					showHand(cardDealer.getHand());
					System.out.println("Player's Cards:");
					showHand(blackjackHandler.getHand());
					System.out.println("You Lose. Game Over.");
					blackjackHandler.hitFail();
					System.out.println(player.getName() + ": " + player.getBalance() + ". Try Again! You Will Win Next Time!");
					return;
				}
				System.out.println("Dealer's Cards:");
				showHand(cardDealer.getHand());
				System.out.println("Player's Cards:");
				showHand(blackjackHandler.getHand());
				continue;
			}else if(userDecision.equalsIgnoreCase("N")){
				break;
			}else{
				System.out.println("Please choose from Y and N");
			}
		}
		
		// Dealer's Action
		while(true){
			if(computeHandValue(cardDealer.getHand()) > computeHandValue(blackjackHandler.getHand()) && computeHandValue(cardDealer.getHand()) <= 21){
				System.out.println("Dealer's Cards:");
				showHand(cardDealer.getHand());
				System.out.println("Player's Cards:");
				showHand(blackjackHandler.getHand());
				System.out.println("You Lose. Game Over.");
				blackjackHandler.hitFail();
				System.out.println(player.getName() + ": " + player.getBalance() + ". Try Again! You Will Win Next Time!");
				return;
			} else if(computeHandValue(cardDealer.getHand()) == computeHandValue(blackjackHandler.getHand())){
				System.out.println("Dealer's Cards:");
				showHand(cardDealer.getHand());
				System.out.println("Player's Cards:");
				showHand(blackjackHandler.getHand());
				System.out.println("This is a tie. Game Over.");
				System.out.println(player.getName() + ": " + player.getBalance() + ". See You Next Time!");
				return;
			} else if(computeHandValue(cardDealer.getHand()) < 17 || computeHandValue(cardDealer.getHand()) < computeHandValue(blackjackHandler.getHand())){
				hit(cardDealer.getHand());
				continue;
			} else {
				System.out.println("Dealer's Cards:");
				showHand(cardDealer.getHand());
				System.out.println("Player's Cards:");
				showHand(blackjackHandler.getHand());
				System.out.println("You Win. Game Over.");
				blackjackHandler.hitSuccess();
				System.out.println(player.getName() + ": " + player.getBalance() + ". Well Done! Play More!");
				return;
			}
		}
		
	}
	
	
}
