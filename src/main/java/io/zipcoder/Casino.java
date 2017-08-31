package io.zipcoder;

import io.zipcoder.Games.BlackjackGame;

public class Casino {
	
	public static void main(String[] args){
		BlackjackGame newGame = new BlackjackGame();
		Player player1 = new Player("Cici", 1000.0);
		newGame.playGame(player1);
	}
}

