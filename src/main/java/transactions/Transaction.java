package transactions;

import io.zipcoder.Player;

public class Transaction {

	private Player player;
	private double betAmount;
	private String game;
	private double amountRemaining;

	/**
	 * @param player
	 * @param betAmount
	 * @param game
	 * @param amountRemaining
	 */
	public Transaction(Player player, double betAmount, String game, double amountRemaining) {
		super();
		this.player = player;
		this.betAmount = betAmount;
		this.game = game;
		this.amountRemaining = amountRemaining;
	}

	/**
	 * generates a string representation of the object
	 * @return string
	 */
	public String toString(){
		return "Transaction [Player : "+player+"; betAmount: "+betAmount+"; game :"+game+"; amount remaining: "+amountRemaining+"]";
	}

	/**
	 * @return the game
	 */
	public String getGame() {
		return game;
	}
	
	public Player getPlayer(){
		return player;
	}
	
	

}
