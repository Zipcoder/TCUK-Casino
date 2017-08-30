package io.zipcoder;

public interface Bettable {

	/**
	 * give the specified amount of money to the inputed player
	 * 
	 * @param player
	 *            to add money to
	 * @param amountToAdd
	 *            the amount of money to add to the player
	 * @return player with more money
	 */
	public Player giveMoney(Player player, double amountToAdd);

	/**
	 * take the specified amount of money from the inputed player
	 * 
	 * @param player
	 *            to add money to
	 * @param amountToAdd
	 *            the amount of money to add to the player
	 * @return player with less money
	 */
	public Player takeMoney(Player player, double amountToRemove);

}
