package io.zipcoder.CardsTests;

import org.junit.Assert;
import org.junit.Test;

import io.zipcoder.Cards.Deck;


/**
 * Created by W550952 on 30/08/2017.
 */
public class DeckTest {
	
	@Test
	public void creatDeskTest(){
		//: Given
		Deck deck = new Deck();
		int expected = 52;
		
		//: When
		int actual = deck.getDeck().size();
		
		//: Then
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void drawTest(){
		//: Given
		Deck deck = new Deck();
		int expected = 51;
		
		//: When
		deck.draw();
		int actual = deck.getNoOfCards();
		
		//: Then
		Assert.assertEquals(expected, actual);
	}
}
