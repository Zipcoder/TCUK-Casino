package io.zipcoder.Handlers;

import io.zipcoder.Dice;
import io.zipcoder.Player;

public class HighLowDiceHandler extends DiceHandler {

	private int diceValue;
	private int numberOfDice;
	private Player player;

	public HighLowDiceHandler(Player player) {
		this.player = player;
	}

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

}
