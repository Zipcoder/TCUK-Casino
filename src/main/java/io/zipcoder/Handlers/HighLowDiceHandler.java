package io.zipcoder.Handlers;

import io.zipcoder.Dice;
import io.zipcoder.Player;

public class HighLowDiceHandler extends DiceHandler {

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
		// get value from rollDice function w/in Dice class
		return diceValue;
	}

	public int getNumberOfDice() {
		return numberOfDice;
	}
	 
	public boolean giveMoney(double amountToAdd) {
		
		if (amountToAdd >= 0){
			player.increaseBalance(amountToAdd);
			return true;
		}
		return false;	
	}

	public boolean takeMoney(double amountToRemove) {
		
		boolean check = this.player.checkBet(amountToRemove);
		
		if (check == true){
			player.reduceBalance(amountToRemove);
			return true;
		}
		//check available funds first is > 0 - getBalance method on player	
		return false;
	}
	
	
}
