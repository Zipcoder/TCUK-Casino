package io.zipcoder;

import io.zipcoder.Games.BlackjackGame;
import io.zipcoder.Games.HighLowCardGame;
import io.zipcoder.Games.HighLowDiceGame;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Casino {
	private List<Player> players;

	public Casino(ArrayList<Player> players){
		this.players = players;
	}

	public static void main(String[] args){
		Casino casino = new Casino(new ArrayList<Player>());
		casino.start();
	}

	public void start(){
		Player player = null;

		try {
			getPlayers();
		} catch (IOException e){
			players = new ArrayList<Player>();
		}

		String account = getYesOrNo("Welcome to the casino! Do you have an account? Please type Yes or No.");

		if (account.equalsIgnoreCase("Yes")){
			String name = UserInterface.getUserInputString("What is your username?");
			player = checkAccount(name);
			while (player == null){
				int choice = UserInterface.getUserInput("That account does not exist! Would you like to:\n1: Try again?\n2: Create a new account?");
				switch (choice) {
					case 1:
						name = UserInterface.getUserInputString("Please enter your username.");
						player = checkAccount(name);
						break;
					case 2:
						player = createAccount();
						players.add(player);
						UserInterface.assignPlayer(player);
						break;
				}
			}
		} else if (account.equalsIgnoreCase("No")) {
			player = createAccount();
			players.add(player);
			UserInterface.assignPlayer(player);
		}

		String anotherGame = "Yes";
		while (anotherGame.equalsIgnoreCase("Yes")){
			chooseGame(player);
			anotherGame = getYesOrNo("Do you want to play a different game? Please say Yes or No.");
		}
		System.out.println("Thank you for playing! See you next time.");
		try {
			savePlayers();
		} catch (IOException e){
			System.out.println(e);
		}
	}

	private Player createAccount(){
		String name = UserInterface.getUserInputString("Please enter a username:");
		while (name.equals("")){
			name = UserInterface.getUserInputString("Username cannot be empty! Please enter a name.");
		}
		for (Player player1 : players){
			while (player1.getName().equals(name)){
				name = UserInterface.getUserInputString("That username has already been taken. Please enter another:");
				break;
			}
		}
		double balance = UserInterface.getUserInputDouble("How much money do you want to play with?");
		while (balance < 0){
			balance = UserInterface.getUserInputDouble("That is not a valid balance, can't add less than zero");
		}

		return new Player(name, balance);
	}

	private Player checkAccount(String name){
		for (Player player1 : players){
			if (player1.getName().equals(name)){
				return player1;
			}
		}
		return null;
	}

	private void chooseGame(Player player){
		int choice = UserInterface.getUserInput("What game would you like to play?\nPlease choose from:\n" +
				"1: Blackjack\n" +
				"2: Hi-Lo Card Game\n" +
				"3: Hi-Lo Dice Game");
		while (choice != 1 && choice != 2 && choice != 3){
			choice = UserInterface.getUserInput("That is not a valid input, please try again. The options are:\n" +
					"1: Blackjack\n" +
					"2: Hi-Lo Card Game\n" +
					"3: Hi-Lo Dice Game");
		}

		switch (choice){
			case 1:
				BlackjackGame blackjackGame = new BlackjackGame();
				blackjackGame.playGame(player);
				break;
			case 2:
				HighLowCardGame highLowCardGame = new HighLowCardGame();
				highLowCardGame.playGame(player);
				break;
			case 3:
				HighLowDiceGame.playGameStatic(player);
		}
	}

	public void savePlayers() throws IOException {
		File file = new File(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "players.txt");

		try {
			file.createNewFile();
		} catch (Exception e){
			System.out.println(e);
		}

		FileOutputStream out = new FileOutputStream(file);
		StringBuilder sb = new StringBuilder();
		for (Player p : players) {
			sb.append(p.getName() + "," + p.getBalance() + System.lineSeparator());
		}

		String output = sb.toString();
		byte[] contentInBytes = output.getBytes();
		try {
			out.write(contentInBytes);
			out.flush();
			out.close();
		} catch (Exception e){
			System.out.println(e);
		}
	}

	public void getPlayers() throws IOException {
		File file = new File(System.getProperty("user.home") + File.separator + "Documents"  + File.separator + "players.txt");

		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		String currentLine;

		while ((currentLine = bufferedReader.readLine()) != null){
			String[] words = currentLine.split(",");
			players.add(new Player(words[0], Double.parseDouble(words[1])));
		}
	}

	public String getYesOrNo(String question) {
		String answer = UserInterface.getUserInputString(question);
		while (!answer.equalsIgnoreCase("Yes") && !answer.equalsIgnoreCase("No")) {
			answer = UserInterface.getUserInputString("That is not a valid input. Please say Yes or No.");
		}
		return answer;
	}
}