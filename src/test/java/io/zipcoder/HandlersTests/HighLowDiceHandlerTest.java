package io.zipcoder.HandlersTests;

import org.junit.Test;

import io.zipcoder.Player;

import org.junit.Assert;

import io.zipcoder.Handlers.HighLowDiceHandler;

public class HighLowDiceHandlerTest {

	@Test
	
	public void checkBetTrueTest() {
		//: Given
		Player player = new Player("Bob", 100);
		HighLowDiceHandler handler = new HighLowDiceHandler(player);
		
		//: When
		boolean actual = handler.checkBet(50.0);
		
		//: Then
		Assert.assertTrue(actual);
	}
	
	@Test
	
	public void checkBetFalseTest() {
		//: Given
		
		//: When
		
		//: Then
	}
	
	@Test
	
	public void checkBetZeroTest() {
		//: Given
		
		//: When
		
		//: Then
	}
	
	@Test
	
	public void checkBetNegativeTest() {
		//: Given
		
		//: When
		
		//: Then
	}
	
	@Test 
	
	public void giveMoneyTest() {
		
	}
	
	@Test
	
	public void takeMoneyTest() {
		
	}

}
