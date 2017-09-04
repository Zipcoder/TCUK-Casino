package io.zipcoder;

import org.junit.Assert;
import org.junit.Test;

public class DiceTest {

	@Test
	public void testRollDice() {

		// :Given

		int expectedMaxBound = 6;
		int expectedMinBound = 1;
		int numberOfDice = 1;

		// :When
		for (int toRun = 0; toRun < 1000; toRun++) {
			int tempNum = Dice.rollDice(1);
			if (!(1 <= tempNum && tempNum <= 6)) {
				// :Then -- only triggered if number is not between 1 to 6
				// inclusive.
				Assert.fail("testRollDice - number of dice = 1; number returned not within range of 1 to 6");
			}
		}

		// :When
		for ( int toRun = 0; toRun < 10000; toRun++) {
			int tempNum = Dice.rollDice(5);
			if (!(1 <= tempNum && tempNum <= 6*5)) {
				// :Then -- only triggered if number is not between 1 to 30
				// inclusive.
				Assert.fail("testRollDice - number of dice = 1; number returned not within range of 1 to 6");
			}
		}
	}

}
