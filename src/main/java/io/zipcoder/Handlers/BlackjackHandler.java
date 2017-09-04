package io.zipcoder.Handlers;

import io.zipcoder.Bettable;
import io.zipcoder.Player;
import io.zipcoder.UserInterface;

public class BlackjackHandler extends CardHandler {
    private double stake;
    //private Player player;

    public BlackjackHandler(Player player){
    	super(player);
        //this.player = player;
        stake = 0;
    }

    public Player getPlayer(){
        return player;
    }

    public void hitSuccess(){
        player.increaseBalance(stake);
    }

    public void hitFail(){
        player.reduceBalance(stake);
    }

    public boolean makeStake(double amount){
        if (amount > 0){
            stake = amount;
            return true;
        } else {
            UserInterface.sendUpwardsToUser("Amount cannot be less than 0");
            return false;
        }

    }
}
