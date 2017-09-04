package io.zipcoder;

import transactions.Transaction;
import transactions.TransactionLog;

/**
 * Created by W550952 on 30/08/2017.
 */
public class Player {
	private double balance;
	private String name;
	private TransactionLog log;
	private String currentGame;

	public Player(String name, double balance) {
		this(name, balance, new TransactionLog());
	}

	public Player(String name, double balance, TransactionLog log) {
		this.balance = balance;
		this.name = name;
		this.log = log;
		this.currentGame = "";
	}

	public double getBalance() {
		return balance;
	}

	public String getName() {
		return name;
	}

	public boolean increaseBalance(double amount) {
		if (amount >= 0) {
			balance += amount;
			// update Transaction log
			this.log.updateTransaction(new Transaction(this, amount));
			return true;
		} else {
			UserInterface.sendUpwardsToUser("Cannot increase by zero or less");
			return false;
		}
	}

	public boolean reduceBalance(double amount) {

		if ((balance - amount) >= 0) {
			if (amount >= 0) {
				balance -= amount;
				// update Transaction log
				this.log.updateTransaction(new Transaction(this, amount));
				return true;
			}
			UserInterface.sendUpwardsToUser("Cannot reduce by zero or less");
			return false;

		} else {
			UserInterface.sendUpwardsToUser("Not enough money");
			return false;
		}
	}

	public boolean checkBet(double bet) {

		// checks that the balance of the player doesn't go negative upon bet
		// returns true if safe to bet i.e. player has enough money
		// check bet isn't a negative number before

		if ((this.getBalance() - bet) >= 0 && bet >= 0) {
			return true;
		}
		return false;

	}

	/**
	 * @param log
	 *            the log to set
	 */
	public void setLog(TransactionLog log) {
		this.log = log;
	}

	/**
	 * @param currentGame
	 *            the currentGame to set
	 */
	public void setCurrentGame(String currentGame) {
		this.currentGame = currentGame;
	}

	/**
	 * @return the currentGame
	 */
	public String getCurrentGame() {
		return currentGame;
	}
	
	public String getBalanceAsString(){
		return String.format("£%.2f", getBalance());
	}

}
