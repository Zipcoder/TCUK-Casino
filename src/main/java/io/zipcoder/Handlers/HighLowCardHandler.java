package io.zipcoder.Handlers;

import io.zipcoder.Player;

public class HighLowCardHandler extends CardHandler {
	private double stake;

	public HighLowCardHandler(Player player){
		super(player);
		stake = 0;
	}

	public Player getPlayer(){
		return player;
	}

	public void cardHigher(){
		player.increaseBalance(stake);
	}

	public void cardLower(){
		player.reduceBalance(stake);
	}

	public boolean makeStake(double amount){
		if (amount > 0){
			stake = amount;
			return true;
		}
		System.out.println("Amount cannot be less than 0");
		return false;
	}
}
