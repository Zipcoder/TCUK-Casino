package io.zipcoder;

import java.util.Random;

public class Dice {

	private static final int MIN_NUMBER = 1;
	private static final int MAX_NUMBER = 6;
	private static final Random randGen = new Random();

	/**
	 * Returns an integer value between 1 and the number of dice to roll
	 * multiplied by 6.
	 * 
	 * @param numberOfDiceToRoll
	 * @return sum value of dice rolls
	 */
	public static int rollDice(int numberOfDiceToRoll) {
		int result = 0;
		for (int i = 0; i < numberOfDiceToRoll; i++) {
			result += randGen.nextInt(MAX_NUMBER) + 1;
		}
		return result;
	}

}
