package io.zipcoder.Games;

import io.zipcoder.Player;

/**
 * Created by W550952 on 01/09/2017.
 */
public class MainForDebug {
    public static void main(String[] args){
        Player player = new Player("Lily", 500);
        HighLowDiceGame.playGameStatic(player);
    }
}
