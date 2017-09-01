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

	// Method To Show The Card
	public static void showHand(Hand hand){
		String output = hand.getHand().toString();
		System.out.println(output + " " + computeHandValue(hand) + " points.");
	}
	
	public void showResult(Hand hand){
		System.out.println("Dealer's Cards:");
		showHand(cardDealer.getHand());
		System.out.println("Player's Cards:");
		showHand(hand);
	}
	// Hit A Card
	public static void hit(Hand hand){
		hand.addCard(cardDealer.deal());
	}
	
	public double askForStake(Player player){
		System.out.println("How much would you like to bet?");
		double amount = UserInterface.getUserInputDouble();
				
		// Bet At The Beginning Of The Game
		while (!player.checkBet(amount)){
			System.out.println("You don't have enough money to bet that! Your current balance is " + player.getBalanceAsString());
			System.out.println("How much would you like to bet?");
			amount = UserInterface.getUserInputDouble();
		}
		return amount;
	}
	
	public void dealFirstHand(BlackjackHandler blackjackHandler){
		cardDealer.dealToDealer(2);
		
		// Dealer Deal Two Cards To The Player Hand
		blackjackHandler.getHand().addCard(cardDealer.deal());
		blackjackHandler.getHand().addCard(cardDealer.deal());
		showResult(blackjackHandler.getHand());
	}
	
	public void playGame(Player player){
		BlackjackHandler blackjackHandler = new BlackjackHandler(player);

		// Ask For The Amount Of Stake
		blackjackHandler.makeStake(askForStake(player));
		
		// Deal Two Cards To The Dealer
		dealFirstHand(blackjackHandler);
		
		// BlackJack At The First Two Cards
		int dealerHandValue = computeHandValue(cardDealer.getHand());
		int playerHandValue = computeHandValue(blackjackHandler.getHand());
		if(playerHandValue == 21 && dealerHandValue == 21){
			firstHandTie(player);
			return;
		}
		
		if(playerHandValue == 21 && dealerHandValue != 21){
			firstHandWin(player, blackjackHandler);
			return;
		}
		
		if(playerHandValue != 21 && dealerHandValue == 21){
			firstHandFail(player, blackjackHandler);
			return;
		}
		
		// Player Decision (handValue < 21)
		while(true){
			String userDecision = askUserDecision();
			if(userDecision.equalsIgnoreCase("Y")){
				hit(blackjackHandler.getHand());
				if(computeHandValue(blackjackHandler.getHand()) > 21){
					handValueBiggerThan21(player, blackjackHandler);
					return;
				}
				showResult(blackjackHandler.getHand());
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
				handValueBiggerThan21(player, blackjackHandler);
				return;
			} else if(computeHandValue(cardDealer.getHand()) == computeHandValue(blackjackHandler.getHand()) && computeHandValue(cardDealer.getHand()) >= 17){
				tie(player, blackjackHandler);
				return;
			} else if(computeHandValue(cardDealer.getHand()) < 17 || computeHandValue(cardDealer.getHand()) < computeHandValue(blackjackHandler.getHand())){
				hit(cardDealer.getHand());
				continue;
			} else {
				win(player, blackjackHandler);
				return;
			}
		}
		
	}

	public void firstHandFail(Player player, BlackjackHandler blackjackHandler) {
		System.out.println("Dealer BlackJack! You Lose. Game Over.");
		blackjackHandler.hitFail();;
		System.out.println(player.getName() + ": " + player.getBalance() + ". Try Again! You Will Win Next Time!");
	}

	public void firstHandWin(Player player, BlackjackHandler blackjackHandler) {
		System.out.println("BlackJack! You Win!!!");
		blackjackHandler.hitSuccess();
		System.out.println(player.getName() + ": " + player.getBalance() + ". Well Done! Play More!");
	}

	public void firstHandTie(Player player) {
		System.out.println("This is a tie. Game Over.");
		System.out.println(player.getName() + ": " + player.getBalance() + ". See You Next Time!");
	}

	public String askUserDecision() {
		System.out.println("Do you want to hit? Please answer Y/N");
		String userDecision = UserInterface.getUserInputString();
		return userDecision;
	}

	public void win(Player player, BlackjackHandler blackjackHandler) {
		showResult(blackjackHandler.getHand());
		System.out.println("You Win. Game Over.");
		blackjackHandler.hitSuccess();
		System.out.println(player.getName() + ": " + player.getBalance() + ". Well Done! Play More!");
	}

	public void tie(Player player, BlackjackHandler blackjackHandler) {
		showResult(blackjackHandler.getHand());
		firstHandTie(player);
	}

	public void handValueBiggerThan21(Player player, BlackjackHandler blackjackHandler) {
		showResult(blackjackHandler.getHand());
		System.out.println("You Lose. Game Over.");
		blackjackHandler.hitFail();
		System.out.println(player.getName() + ": " + player.getBalance() + ". Try Again! You Will Win Next Time!");
	}
	
		
}
