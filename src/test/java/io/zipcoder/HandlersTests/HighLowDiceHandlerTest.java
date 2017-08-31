package io.zipcoder.HandlersTests;

import org.junit.Test;

import io.zipcoder.Player;

import org.junit.Assert;

import io.zipcoder.Handlers.HighLowDiceHandler;

public class HighLowDiceHandlerTest {

	@Test 
	
	public void giveMoneyTrueTest() {
		//: Given
		Player player = new Player("Bob", 100);
		HighLowDiceHandler handler = new HighLowDiceHandler(player);

		double expected1 = 400.0;
		//: When
		boolean actual = handler.giveMoney(300);
		double actual1 = player.getBalance();
		//: Then
		Assert.assertTrue(actual);
		Assert.assertEquals(expected1, actual1, 0.0);
			
	}
	
	@Test 
	
	public void giveMoneyFalseTest() {
		//: Given
		Player player = new Player("Bob", 100);
		HighLowDiceHandler handler = new HighLowDiceHandler(player);

		//: When
		boolean actual = handler.giveMoney(-300);

		//: Then
		Assert.assertFalse(actual);
		
	}
	
	@Test
	
	public void takeMoneyTrueTest() {
		//: Given
		Player player = new Player("Bob", 100);
		HighLowDiceHandler handler = new HighLowDiceHandler(player);
		
		//: When
		boolean actual = handler.takeMoney(50);
		
		//: Then
		Assert.assertTrue(actual);
	}
	
	@Test
	
	public void takeMoneyFalseTest() {
		//: Given
		Player player = new Player("Bob", 100);
		HighLowDiceHandler handler = new HighLowDiceHandler(player);
		
		//: When
		boolean actual = handler.takeMoney(150);
		
		//: Then
		Assert.assertFalse(actual);
	}
	
	@Test
	
	public void takeMoneyNegativeTest() {
		//: Given
		Player player = new Player("Bob", 100);
		HighLowDiceHandler handler = new HighLowDiceHandler(player);
		
		//: When
		boolean actual = handler.takeMoney(-100);
				
		//: Then
		Assert.assertFalse(actual);
	}
	
}
