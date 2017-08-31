package io.zipcoder.Handlers;

import io.zipcoder.Bettable;
import io.zipcoder.Player;

public class HighLowCardHandler extends CardHandler {
	private double pot;
	private double stake;
	private Player player;

	public HighLowCardHandler(Player player){
		this.player = player;
		pot = 0;
		stake = 0;
	}

	public Player getPlayer(){
		return player;
	}

	public void cardHigher(){
		player.increaseBalance(pot);
	}

	public void cardLower(){
		player.reduceBalance(stake);
	}

	public boolean makeStake(double amount){
		if (amount > 0){
			stake = amount;
			pot += amount;
			return true;
		}
		System.out.println("Amount cannot be less than 0");
		return false;
	}
}
