package io.zipcoder.GameTests;

import org.junit.Assert;
import org.junit.Test;

import io.zipcoder.Player;
import io.zipcoder.Games.HighLowDiceGame;
import io.zipcoder.Handlers.HighLowDiceHandler;

public class HighLowDiceGameTest {

	@Test
	public void testLoseLogicTrue() {
		// :Given
		int currentDiceRoll = 5;
		String userInput = "higher";
		int newDiceRoll = 4;
		
		// :When
		boolean result = HighLowDiceGame.loseLogic(currentDiceRoll, userInput, newDiceRoll);
		
		// :Then
		
		Assert.assertTrue(result);
	}
	
	@Test
	public void testLoseLogicFalse() {
		// :Given
		int currentDiceRoll = 5;
		String userInput = "lower";
		int newDiceRoll = 4;
		
		// :When
		boolean result = HighLowDiceGame.loseLogic(currentDiceRoll, userInput, newDiceRoll);
		
		// :Then
		
		Assert.assertFalse(result);
	}
	
	
	@Test
	public void testWinLogicTrue() {
		
		// :Given
		int currentDiceRoll = 5;
		String userInput = "higher";
		int newDiceRoll = 6;
		
		// :When
		boolean result = HighLowDiceGame.winLogic(currentDiceRoll, userInput, newDiceRoll);
				
		
		// :Then		
		Assert.assertTrue(result);
	}
	
	
	@Test
	public void testWinLogicFalse() {
		
		// :Given
		int currentDiceRoll = 5;
		String userInput = "lower";
		int newDiceRoll = 6;
		
		// :When
		boolean result = HighLowDiceGame.winLogic(currentDiceRoll, userInput, newDiceRoll);
				
		
		// :Then		
		Assert.assertFalse(result);
	}

	@Test
	public void testCheckBetAmountTrue() {
		
		// :Given
		Player player = new Player("Bob", 50.40);
		HighLowDiceHandler diceHandler = new  HighLowDiceHandler(player);
		double betAmount = 50;
		
		// :When
		
		boolean result = HighLowDiceGame.checkBetAmount(diceHandler, betAmount);
		
		// :Then
		
		Assert.assertTrue(result);
	}

	
	@Test
	public void testCheckBetAmountFalse() {
		
		// :Given
		Player player = new Player("Bob", 50.40);
		HighLowDiceHandler diceHandler = new  HighLowDiceHandler(player);
		double betAmount = 60;
		
		// :When
		
		boolean result = HighLowDiceGame.checkBetAmount(diceHandler, betAmount);
		
		// :Then
		
		Assert.assertTrue(result);
	}
	
	
	@Test
	public void testCheckIfInputHasTrue() {
		// :Given
		String inputString = "Higher";
		String whatToCheckItContains = "higher";
		
		// :When
		boolean result = HighLowDiceGame.checkIfInputHas(inputString, whatToCheckItContains);
		
		// :Then
		Assert.assertTrue(result);
	}

	
	@Test
	public void testCheckIfInputHasFalse() {
		// :Given
		String inputString = "Lower";
		String whatToCheckItContains = "higher";
		
		// :When
		boolean result = HighLowDiceGame.checkIfInputHas(inputString, whatToCheckItContains);
		
		// :Then
		Assert.assertFalse(result);
	}
	
}







