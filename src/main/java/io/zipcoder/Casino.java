package io.zipcoder;

import io.zipcoder.Games.BlackjackGame;
import io.zipcoder.Games.HighLowCardGame;
import io.zipcoder.Games.HighLowDiceGame;

import java.util.ArrayList;
import java.util.List;

public class Casino {
	private static List<Player> players = new ArrayList<Player>();

	public static void main(String[] args){
		start();
	}

	public static void start(){
		Player player = null;
		System.out.println("Welcome to the casino! Do you have an account? Please type Yes or No.");
		String account = UserInterface.getUserInputString();
		while (!account.equalsIgnoreCase("Yes") && !account.equalsIgnoreCase("No")){
			System.out.println("That is not a valid input. Please say Yes or No.");
			account = UserInterface.getUserInputString();
		}

		if (account.equalsIgnoreCase("Yes")){
			System.out.println("What is your username?");
			String name = UserInterface.getUserInputString();
			player = checkAccount(name);
			if (player == null){
				System.out.println("That account does not exist! Please make a new account.");
				player = createAccount();
			}
		} else if (account.equalsIgnoreCase("No")) {
			player = createAccount();
		}

		String anotherGame = "Yes";
		while (anotherGame.equalsIgnoreCase("Yes")){
			chooseGame(player);
			System.out.println("Do you want to play a different game? Please say Yes or No.");
			anotherGame = UserInterface.getUserInputString();
			while (!anotherGame.equalsIgnoreCase("Yes") && !anotherGame.equalsIgnoreCase("No")){
				System.out.println("That is not a valid input. Please say Yes or No.");
				anotherGame = UserInterface.getUserInputString();
			}
		}
		System.out.println("Thank you for playing! See you next time.");
	}

	private static Player createAccount(){
		System.out.println("Please enter a username:");
		String name = UserInterface.getUserInputString();
		for (Player player1 : players){
			while (player1.getName().equals(name)){
				System.out.println("That username has already been taken. Please enter another:");
				name = UserInterface.getUserInputString();
				break;
			}
		}
		System.out.println("How much money do you want to play with?");
		double balance = UserInterface.getUserInputDouble();
		while (balance < 0){
			System.out.println("That is not a valid balance, can't add less than zero");
			balance = UserInterface.getUserInputDouble();
		}

		return new Player(name, balance);
	}

	private static Player checkAccount(String name){
		for (Player player1 : players){
			if (player1.getName().equals(name)){
				return player1;
			}
		}
		return null;
	}

	private static void chooseGame(Player player){
		System.out.println("What game would you like to play?\nPlease choose from:\n" +
				"1: Blackjack\n" +
				"2: Hi-Lo Card Game\n" +
				"3: Hi-Lo Dice Game");
		int choice = UserInterface.getUserInput();
		while (choice != 1 && choice != 2 && choice != 3){
			System.out.println("That is not a valid input, please try again. The options are:\n" +
					"1: Blackjack\n" +
					"2: Hi-Lo Card Game\n" +
					"3: Hi-Lo Dice Game");

			choice = UserInterface.getUserInput();
		}

		switch (choice){
			case 1:
				BlackjackGame game = new BlackjackGame();
				game.playGame(player);
				break;
			case 2:
				HighLowCardGame.playGame(player);
				break;
			case 3:
				HighLowDiceGame.playGameStatic(player);
		}
	}
}

