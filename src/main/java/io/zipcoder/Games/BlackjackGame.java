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
		int aceCount = 0;
		for(int i = 0; i < hand.getNumberOfCards(); i++){
			int cardValue = valueMap.get(hand.getHand().get(i).getValue());
			sumValue += cardValue;
			if(cardValue == 11){
				aceCount++;
			}
		}
		for(int x = 0; x < aceCount; x++){
			if(sumValue > 21){
				sumValue = sumValue - 10;
				if(sumValue < 21){
					break;
				}
			}
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
//		if(hand.getHand().size() == 2 && hand.getHand().get(0).equals(Value.ACE)  &&  hand.getHand().get(1).equals(Value.ACE)){
//			System.out.println(output + " " + " 12 points.");
//		}else{
//			System.out.println(output + " " + computeHandValue(hand) + " points.");
//		}
		System.out.println(output + " " + computeHandValue(hand) + " points.");
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
		while (!player.checkBet(amount)){
			System.out.println("You don't have enough money to bet that! Your current balance is " + player.getBalanceAsString());
			System.out.println("How much would you like to bet?");
			amount = UserInterface.getUserInputDouble();
		}

		blackjackHandler.makeStake(amount);
		
		// Deal Two Cards To The Dealer
		cardDealer.dealToDealer(2);
		
		// Dealer Deal Two Cards To The Player Hand
		blackjackHandler.getHand().addCard(cardDealer.deal());
		blackjackHandler.getHand().addCard(cardDealer.deal());
//		if(valueMap.get(cardDealer.getHand().getHand().get(0)) == 11 && valueMap.get(cardDealer.getHand().getHand().get(1)) == 11){
//			String output = cardDealer.getHand().getHand().toString();
//			System.out.println("Dealer's Cards:");
//			System.out.println(output + " " + "12 point.");
//			System.out.println("Player's Cards:");
//			showHand(blackjackHandler.getHand());
//		}else if(valueMap.get(blackjackHandler.getHand().getHand().get(0)) == 11 && valueMap.get(blackjackHandler.getHand().getHand().get(1)) == 11){
//			String output = blackjackHandler.getHand().getHand().toString();
//			System.out.println("Dealer's Cards:");
//			showHand(cardDealer.getHand());
//			System.out.println("Player's Cards:");
//			System.out.println(output + " " + "12 point.");
//		}else{
			System.out.println("Dealer's Cards:");
			showHand(cardDealer.getHand());
			System.out.println("Player's Cards:");
			showHand(blackjackHandler.getHand());
//		}
		
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
		
		if(playerHandValue != 21 && dealerHandValue == 21){
			System.out.println("Dealer BlackJack! You Lose. Game Over.");
			blackjackHandler.hitFail();;
			System.out.println(player.getName() + ": " + player.getBalance() + ". Try Again! You Will Win Next Time!");
			return;
		}
		
		// Player Decision(handValue < 21) --- tow ACE switch value!!!
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
			} else if(computeHandValue(cardDealer.getHand()) == computeHandValue(blackjackHandler.getHand()) && computeHandValue(cardDealer.getHand()) >= 17){
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
