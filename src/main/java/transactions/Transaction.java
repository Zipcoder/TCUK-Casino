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
	public Transaction(Player player, double betAmount) {
		super();
		this.player = player;
		this.betAmount = betAmount;
		this.game = player.getCurrentGame();
		this.amountRemaining = player.getBalance();
	}

	/**
	 * generates a string representation of the object
	 * @return string
	 */
	public String toString(){
		return "Transaction [Player : "+player.getName()+"; betAmount: "+betAmount+"; game: "+game+"; amount remaining: "+amountRemaining+"]";
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
