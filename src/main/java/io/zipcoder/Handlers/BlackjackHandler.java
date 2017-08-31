package io.zipcoder.Handlers;

import io.zipcoder.Bettable;
import io.zipcoder.Player;

public class BlackjackHandler extends CardHandler {
    private double pot;
    private double stake;
    private Player player;

    public BlackjackHandler(Player player){
    	super();
        this.player = player;
        pot = 0;
        stake = 0;
    }

    public Player getPlayer(){
        return player;
    }

    public void hitSuccess(){
        player.increaseBalance(pot);
    }

    public void hitFail(){
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
