package io.zipcoder.Handlers;
import io.zipcoder.Dice;

public class HighLowDiceHandler extends DiceHandler {
	
	private int diceValue;
	private int numberOfDice;
	
	
	
	public void setDiceValue(int numberOfDice) {
		
		this.numberOfDice = numberOfDice;
		this.diceValue = Dice.rollDice(numberOfDice);
		
	}

	public int getDiceValue() {
		//get value from rollDice function 
		return diceValue;
		
	}
	
	public int getNumberOfDice() {
		
		return numberOfDice;
		
	}

}
