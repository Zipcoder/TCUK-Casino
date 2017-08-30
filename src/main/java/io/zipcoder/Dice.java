package io.zipcoder;

import java.util.Random;

public class Dice {

	private static final int MIN_NUMBER = 1;
	private static final int MAX_NUMBER = 6;
	private static final Random randGen = new Random();
	
	public static int rollDice(int numberOfDiceToRoll){
		int result = 0;
		for (int i = 0; i<numberOfDiceToRoll; i++){
			result += randGen.nextInt(MAX_NUMBER) + 1;
		}
		return result;
	}
	
}
