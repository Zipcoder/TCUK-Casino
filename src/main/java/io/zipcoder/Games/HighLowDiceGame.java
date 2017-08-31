/**
 * 
 */
package io.zipcoder.Games;

import io.zipcoder.Bettable;
import io.zipcoder.Player;
import io.zipcoder.UserInterface;
import io.zipcoder.Handlers.HighLowDiceHandler;

/**
 * @author F628559
 *
 */
public class HighLowDiceGame extends DiceGame { // REMOVE
												// implements
												// bettable
												// later

	@Override
	public void playGame() {
		// TODO Auto-generated method stub

	}

	private static int numberOfDiceToPlayWith;
	private static HighLowDiceHandler diceHandler;
	private static int bettingPool = 0;

	// @Override
	public static void playGame(Player player) {

		boolean continueGame = true;
		diceHandler = new HighLowDiceHandler(player);
		int currentDiceRoll = 0;

		System.out.println("Welcome to the higher and lower dice game!");

		while (continueGame) {

			// select number of dice to play with
			System.out.println("How many dice do you want to play with? Please type a number:");
			numberOfDiceToPlayWith = UserInterface.getUserInput();

			// randomly throw dice at the player
			diceHandler.setDiceValue(numberOfDiceToPlayWith);
			currentDiceRoll = diceHandler.getDiceValue();

			// Tell player what the number is then let the player bet if they
			// want to: bet if the next roll will be higher or lower
			System.out.println("The dice roll added up to: " + currentDiceRoll
					+ " do you want to bet whether the next dice roll will be higher or lower?");

			// check and get the user input
			boolean receivedInvalidInput = true;
			int bettableAmount;
			String userInput;

			while (receivedInvalidInput) {
				userInput = UserInterface.getUserInputString();
				if (checkIfInputHas(userInput, "higher") || checkIfInputHas(userInput, "lower")) {

					bettableAmount = getBetAmount();
					bettingPool += diceHandler.takeMoney(bettableAmount);
					receivedInvalidInput = false;
				}
			}

			// if the next roll is what they bet (if they bet high and it is
			// high) , return money to the player
			// this should be scaled?
			// diceHandler get last roll, then re-roll.

			diceHandler.setDiceValue(numberOfDiceToPlayWith);
			int newDiceRoll = diceHandler.getDiceValue();

			// perform check
			// if passes, give money to the player -- have system.out.println
			// informative information
			
			if (newDiceRoll > currentDiceRoll || checkIfInputHas(userInput, "higher")) {
				
			} else if (newDiceRoll < currentDiceRoll || checkIfInputHas(userInput, "higher")) {
				
			} else if (newDiceRoll > currentDiceRoll || checkIfInputHas(userInput, "lower")) {
				
			} else if (newDiceRoll < currentDiceRoll || checkIfInputHas(userInput, "lower")) {
				
			} else if (newDiceRoll == currentDiceRoll) {
				diceHandler.giveMoney(bettingPool);
			}

			System.out.println("\nDo you want to continue the game?");
			// get user input as boolean value
			
		}

		return;

	}

	/**
	 * get a bet and return the int
	 * 
	 * @return
	 */
	public static int getBetAmount() {

		boolean condition = true;
		int betAmount = 0;

		while (condition) {
			System.out.println("How much money do you want to bet?");
			betAmount = UserInterface.getUserInput();

			if (diceHandler.checkBet(betAmount)) {
				System.out.println("You cannot bet that! Please enter a valid bet:");
				condition = true;
			} else {
				condition = false;
			}
		}

		return betAmount;
	}

	public static boolean checkIfInputHas(String inputString, String whatToCheckItContains) {
		return inputString.toLowerCase().indexOf(whatToCheckItContains) != -1;
	}

	@Deprecated
	/**
	 * DO NOT USE THIS
	 */
	public Player giveMoney(Player player, double amountToAdd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Deprecated
	/**
	 * DO NOT USE THIS
	 */
	public Player takeMoney(Player player, double amountToRemove) {
		// TODO Auto-generated method stub
		return null;
	}

}
