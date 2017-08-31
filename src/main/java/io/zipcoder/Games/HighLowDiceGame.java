/**
 *
 */
package io.zipcoder.Games;
import io.zipcoder.Bettable;
import io.zipcoder.Player;
import io.zipcoder.Handlers.Handler;

/**
 * @author F628559
 *
 */
public class HighLowDiceGame extends DiceGame implements Bettable {

	private int numberOfDiceToPlayWith;
	private Handler handler;

	public HighLowDiceGame(){

	}

	@Override
	public void playGame() {

		boolean continueGame = true;

		System.out.println("Welcome to the higher and lower dice game!");

		while(continueGame){

			// select number of dice to play with

			// randomly throw dice at the player

			// let the player bet if they want to:
			// bet if the next roll will be higher or lower



			System.out.println("\nDo you want to continue the game?");
			// get user input as boolean value
		}

		return;

	}

	public Player giveMoney(Player player, double amountToAdd) {
		// TODO Auto-generated method stub
		return null;
	}

	public Player takeMoney(Player player, double amountToRemove) {
		// TODO Auto-generated method stub
		return null;
	}

}
