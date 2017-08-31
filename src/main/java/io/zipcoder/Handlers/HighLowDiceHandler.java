package io.zipcoder.Handlers;

import io.zipcoder.Bettable;
import io.zipcoder.Dice;
import io.zipcoder.Player;

public class HighLowDiceHandler extends DiceHandler implements Bettable {

	private int diceValue;
	private int numberOfDice;
	private Player player;

	//constructor
	public HighLowDiceHandler(Player player) {
		this.player = player;
	}
	
	//get player
	public Player getPlayer() {
		return player;
	}
	
	public void setDiceValue(int numberOfDice) {
		this.numberOfDice = numberOfDice;
		this.diceValue = Dice.rollDice(numberOfDice);
	}

	public int getDiceValue() {
		// get value from rollDice function
		return diceValue;
	}

	public int getNumberOfDice() {
		return numberOfDice;
	}
	
	//wrapper methods - create a method that only allows us to give/take an amount 
	public Player giveMoney(Player player, double amountToAdd) {
	
		// TODO Auto-generated method stub
		return null;
		
	}

	public Player takeMoney(Player player, double amountToRemove) {
		// TODO Auto-generated method stub
		//check available funds first is > 0 - getBalance method on player
		//
		return null;
	}
	
	
}
