package io.zipcoder.CardsTests;


import org.junit.Assert;
import org.junit.Test;

import io.zipcoder.Cards.Suit;
import io.zipcoder.Cards.Value;
import io.zipcoder.Cards.*;


/**
 * Created by W550952 on 30/08/2017.
 */
public class CardTest {

	// Test toString method

	@Test
	public void toStringTest() {
		// : Given
		Card card = new Card(Suit.Clubs, Value.TWO);
		String expected = "TWO of Clubs";
		// : When
		String actual = card.toString();
		// : Then
		Assert.assertEquals(expected, actual);
		
	}
}
