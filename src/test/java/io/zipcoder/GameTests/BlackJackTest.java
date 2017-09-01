package io.zipcoder.GameTests;

import org.junit.Assert;
import org.junit.Test;

import io.zipcoder.Cards.Card;
import io.zipcoder.Cards.Hand;
import io.zipcoder.Cards.Suit;
import io.zipcoder.Cards.Value;
import io.zipcoder.Games.BlackjackGame;

public class BlackJackTest {
	
	@Test
	public void computeValueTwoAceFirstTest(){
		//: Given
		Hand hand = new Hand();
		BlackjackGame newGame = new BlackjackGame();
		Card card1 = new Card(Suit.Spades, Value.ACE);
		Card card2 = new Card(Suit.Diamonds, Value.ACE);
		hand.addCard(card1);
		hand.addCard(card2);
		int expected = 12;
		
		//: When
		int actual = BlackjackGame.computeHandValue(hand);
		
		//: Then
		Assert.assertEquals(expected, actual);				
	}
	
	@Test
	public void computeValueOneAceFirstTest(){
		//: Given
		Hand hand = new Hand();
		BlackjackGame newGame = new BlackjackGame();
		Card card1 = new Card(Suit.Spades, Value.ACE);
		Card card2 = new Card(Suit.Diamonds, Value.EIGHT);
		hand.addCard(card1);
		hand.addCard(card2);
		int expected = 19;
		
		//: When
		int actual = BlackjackGame.computeHandValue(hand);
		
		//: Then
		Assert.assertEquals(expected, actual);				
	}
	
	@Test
	public void computeValueOneAceLaterTest(){
		//: Given
		Hand hand = new Hand();
		BlackjackGame newGame = new BlackjackGame();
		Card card1 = new Card(Suit.Spades, Value.ACE);
		Card card2 = new Card(Suit.Diamonds, Value.EIGHT);
		Card card3 = new Card(Suit.Hearts, Value.ACE);
		hand.addCard(card1);
		hand.addCard(card2);
		hand.addCard(card3);
		int expected = 20;
		
		//: When
		int actual = BlackjackGame.computeHandValue(hand);
		
		//: Then
		Assert.assertEquals(expected, actual);				
	}
	
	@Test
	public void computeValueMoreAceLaterTest(){
		//: Given
		Hand hand = new Hand();
		BlackjackGame newGame = new BlackjackGame();
		Card card1 = new Card(Suit.Spades, Value.ACE);
		Card card2 = new Card(Suit.Diamonds, Value.EIGHT);
		Card card3 = new Card(Suit.Hearts, Value.ACE);
		Card card4 = new Card(Suit.Hearts, Value.NINE);
		hand.addCard(card1);
		hand.addCard(card2);
		hand.addCard(card3);
		hand.addCard(card4);
		int expected = 19;
		
		//: When
		int actual = BlackjackGame.computeHandValue(hand);
		
		//: Then
		Assert.assertEquals(expected, actual);				
	}
	
	@Test
	public void hitTest(){
		//: Given
		Hand hand = new Hand();
		BlackjackGame newGame = new BlackjackGame();
		Card card1 = new Card(Suit.Spades, Value.EIGHT);
		Card card2 = new Card(Suit.Diamonds, Value.FIVE);
		hand.addCard(card1);
		hand.addCard(card2);
		int expected = 3;
		
		//: When
		BlackjackGame.hit(hand);
		int actual = hand.getNumberOfCards();
		
		//: Then
		Assert.assertEquals(expected, actual);				
	}

}
