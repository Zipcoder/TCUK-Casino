/**
 * 
 */
package io.zipcoder.Games;
import io.zipcoder.Bettable;
import io.zipcoder.Player;
import io.zipcoder.UserInterface;
import io.zipcoder.Handlers.DiceHandler;

/**
 * @author F628559
 *
 */
public class HighLowDiceGame extends DiceGame implements Bettable {

	private int numberOfDiceToPlayWith;
	private DiceHandler diceHandler;
	
	public HighLowDiceGame(){
		
	}
	
	@Override
	public void playGame() {
		
		boolean continueGame = true;
		
		System.out.println("Welcome to the higher and lower dice game!");
		
		while(continueGame){
		
			// select number of dice to play with
			System.out.println("How many dice do you want to play with? Please type a number:");
			int diceToPlayWith = UserInterface.getUserInput(); // BUG: may crash if not handed an int
			this.diceHandler = new DiceHandler();
			
			// randomly throw dice at the player
			//int diceRoll1 = diceHandler.set
			
			
			// let the player bet if they want to: 
			// bet if the next roll will be higher or lower 
			
			// if the next roll is what they bet (if they bet high and it is high) , return money to the player
			// this should be scaled?? 
			
		
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
