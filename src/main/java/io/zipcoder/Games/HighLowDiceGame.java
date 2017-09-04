/**
 * 
 */
package io.zipcoder.Games;

import io.zipcoder.Bettable;
import io.zipcoder.Casino;
import io.zipcoder.Player;
import io.zipcoder.UserInterface;
import io.zipcoder.Handlers.HighLowDiceHandler;

/**
 * @author F628559
 *
 */
public class HighLowDiceGame extends DiceGame {

	private Player player;

	public HighLowDiceGame(Player player) {
		this.player = player;
	}

//	 TEST METHOD
//	public static void main(String[] arg) {
//		Player p = new Player("bob", 1000);
//		HighLowDiceGame.playGameStatic(p);
//	}

	@Override
	public void playGame(Player player) {
		this.player = player;
		HighLowDiceGame.playGameStatic(player);
	}

	/*
	 * *************************************************************************
	 * This class was written as a static class as the method does not require
	 * state, hence why all internal methods are static
	 * *************************************************************************
	 */

	public static void playGameStatic(Player player) {

		boolean continueGame = true;
		double bettingPool = 0;
		HighLowDiceHandler diceHandler = new HighLowDiceHandler(player);
		int numberOfDiceToPlayWith;
		int currentDiceRoll = 0;

		System.out.println("Welcome to the higher and lower dice game!");

		while (continueGame) {

			// select number of dice to play with
			System.out.println("How many dice do you want to play with? Please type a number:");
			numberOfDiceToPlayWith = UserInterface.getUserInput();
			while (numberOfDiceToPlayWith < 1 || numberOfDiceToPlayWith > 10){
				System.out.println("That is not a valid number, please enter a number between 1 and 10");
				numberOfDiceToPlayWith = UserInterface.getUserInput();
			}

			// randomly throw dice at the player
			diceHandler.setDiceValue(numberOfDiceToPlayWith);
			currentDiceRoll = diceHandler.getDiceValue();

			// Tell player what the number is then let the player bet if they
			// want to: bet if the next roll will be higher or lower
			System.out.println("The dice roll added up to: " + currentDiceRoll
					+ " do you want to bet whether the next dice roll will be higher or lower?");

			// check and get the user input
			boolean receivedInvalidInput = true;
			double bettableAmount;
			String userInput = null;

			while (receivedInvalidInput) {

				userInput = UserInterface.getUserInputString();

				if (checkIfInputHas(userInput, "higher") || checkIfInputHas(userInput, "lower")) {
					bettableAmount = (double) getBetAmount(diceHandler);
					if (diceHandler.takeMoney(bettableAmount)) {
						bettingPool += bettableAmount;
						receivedInvalidInput = false;
					}
				} else {
					System.out.println(
							"Sorry, I didn't understand that, can you say if the next dice will be higher or lower?");

				}

			}

			// if the next roll is what they bet (if they bet high and it is
			// high) , return money to the player
			// this should be scaled?
			// diceHandler get last roll, then re-roll.

			diceHandler.setDiceValue(numberOfDiceToPlayWith);
			int newDiceRoll = diceHandler.getDiceValue();

			System.out.println("The new dice value is: " + newDiceRoll);

			// perform check
			// if passes, give money to the player -- have system.out.println
			// informative information

			// winning logic
			if (winLogic(currentDiceRoll, userInput, newDiceRoll)) {

				// add money to player
				diceHandler.giveMoney(bettingPool * 2);
				System.out.println("You Win!");

				// losing logic
			} else if (loseLogic(currentDiceRoll, userInput, newDiceRoll)) {

				// don't give money to player.
				System.out.println("Bad luck!");

				// tie logic
			} else {
				diceHandler.giveMoney(bettingPool);
				System.out.println("Wow, that's cheeky");
			}

			System.out.println("Your new balance is: " + player.getBalance());

			bettingPool = 0;
			System.out.println("\nDo you want to continue the game?");
			boolean continueCondition = true;
			while (continueCondition) {
				userInput = UserInterface.getUserInputString();
				if ((checkIfInputHas(userInput, "n")) || (checkIfInputHas(userInput, "no"))) {
					System.out.println("See you next time at higher or lower dice game!");
					continueCondition = false;
					continueGame = false;
				} else if ((checkIfInputHas(userInput, "y")) || (checkIfInputHas(userInput, "yes"))) {
					System.out.println("Let's go again!");
					continueCondition = false;
				} else {
					System.out.println("Sorry, could you repeat that? (yes/no)");
				}
			}
		}

		return;

	}

	/**
	 * check the user has won the bet
	 * 
	 * @param currentDiceRoll
	 * @param userInput
	 * @param newDiceRoll
	 * @return true if the user has won
	 */
	public static boolean loseLogic(int currentDiceRoll, String userInput, int newDiceRoll) {
		return (newDiceRoll < currentDiceRoll && checkIfInputHas(userInput, "higher"))
				|| (newDiceRoll > currentDiceRoll && checkIfInputHas(userInput, "lower"));
	}

	/**
	 * check the user has lost the bet
	 * 
	 * @param currentDiceRoll
	 * @param userInput
	 * @param newDiceRoll
	 * @return true if user has lost
	 */
	public static boolean winLogic(int currentDiceRoll, String userInput, int newDiceRoll) {
		return (newDiceRoll > currentDiceRoll && checkIfInputHas(userInput, "higher"))
				|| (newDiceRoll < currentDiceRoll && checkIfInputHas(userInput, "lower"));
	}

	/**
	 * get a bet and return the int
	 * 
	 * @return
	 */
	public static double getBetAmount(HighLowDiceHandler diceHandler) {

		boolean condition = true;
		double betAmount = 0;

		while (condition) {
			System.out.println("How much money do you want to bet?");
			betAmount = UserInterface.getUserInputDouble();

			condition = checkBetAmount(diceHandler, betAmount);
		}

		return (double) betAmount;
	}

	/**
	 * 
	 * 
	 * @param betAmount
	 * @return
	 */
	public static boolean checkBetAmount(HighLowDiceHandler diceHandler, double betAmount) {
		boolean condition;
		if (!diceHandler.getPlayer().checkBet(betAmount)) {
			System.out.println("You cannot bet that! Please enter a valid bet:");
			condition = true;
		} else {
			condition = false;
		}
		return condition;
	}

	/**
	 * Quick and easy method to check that an inputed string contains a sub
	 * string. Returns true if it does, false if it doesn't
	 * 
	 * @param inputString
	 * @param whatToCheckItContains
	 * @return boolean
	 */
	public static boolean checkIfInputHas(String inputString, String whatToCheckItContains) {
		return inputString.toLowerCase().indexOf(whatToCheckItContains) != -1;
	}

}
