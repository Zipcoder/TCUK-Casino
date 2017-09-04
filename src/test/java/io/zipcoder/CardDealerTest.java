package io.zipcoder;

import org.junit.Assert;
import org.junit.Test;

public class CardDealerTest {
	
	@Test
	public void dealTest(){
		//: Given
		CardDealer cardDealer = new CardDealer();
		int expected = 2;
		
		//: When
		int actual = cardDealer.deal(2).size();
		
		//: Then
		Assert.assertEquals(expected, actual);
	}

}
