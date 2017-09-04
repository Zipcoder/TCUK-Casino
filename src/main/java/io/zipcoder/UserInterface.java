package io.zipcoder;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by W550952 on 30/08/2017.
 */
public class UserInterface {

	private static Scanner sc = new Scanner(System.in);
	private static String originalUserInput;
	private static Player player;
	private static boolean useSystemOutFunciton = true;

	private static final Pattern FLOAT_PATTERN = Pattern.compile("[+-]?[0-9,]*\\.?[0-9]+([eE][+-]?[0-9]*)?");
	private static final String CURRENCY_SYMBOL = "£";

	/*
	 * *************************************************************************
	 * PUBLIC INTERFACE METHODS
	 * *************************************************************************
	 */

	/**
	 * Show the inputed userReadableString to the user and then receive an input
	 * from the user as a string
	 * 
	 * @param userReadableString
	 * @return string input from the user
	 */
	public static String getUserInputString(String userReadableString) {
		commonInit(userReadableString);
		String output = getStringLogic();
		return commonReturn(output);
	}

	/**
	 * DEPRECATED get a String input from the user <br>
	 * Use instead: getUserInputString(userReadableString); where the inputed
	 * string is passed to the user prior to them inputting a double
	 * 
	 * @return String
	 * @deprecated
	 * @see getUserInputString(userReadableString)
	 */
	public static String getUserInputString() {
		// may be called by legacy functions -- do a legacy init.
		commonLegacyInit();
		String output = getStringLogic();
		return commonReturn(output);
	}

	/**
	 * Show the inputed userReadableString to the user and then receive an input
	 * from the user as an integer
	 * 
	 * @param userReadableString
	 * @return int user input
	 */
	public static int getUserInput(String userReadableString) {
		commonInit(userReadableString);
		return commonReturn(getIntLogic());
	}

	/**
	 * DEPRECATED get an int from the user <br>
	 * Use instead: getUserInput(userReadableString); where the inputed string
	 * is passed to the user prior to them inputting an int.
	 * 
	 * @return int
	 * @deprecated
	 * @see getUserInput(userReadableString)
	 */
	public static int getUserInput() {
		commonLegacyInit();
		return commonReturn(getIntLogic());
	}

	/**
	 * Show the inputed userReadableString to the user and then receive an input
	 * from the user as a Double (can be auto-unboxed to double). This method
	 * will still function even if the user enters a string, or a string with a
	 * double in it.
	 * 
	 * If this value is printed, there is no guarantee it will be printed to 2DP
	 * if the last digit is a 0.
	 * 
	 * @param userReadableString
	 * @return Double user input
	 * @see getUserInputDoubleTo2DP(userReadableString)
	 */
	public static Double getUserInputDouble(String userReadableString) {
		commonInit(userReadableString);
		return commonReturn(getDoubleLogic());
	}

	/**
	 * Show the inputed userReadableString to the user and then receive an input
	 * from the user as a Double (can be auto-unboxed to double), if the
	 * received double has more than 2 decimal places the number is silently
	 * rounded to 2 decimal places
	 * 
	 * @param userReadableString
	 * @return Double that the user inputed to 2 decimal places.
	 * @see getUserInputDoule(userReadableString)
	 */
	public static Double getUserInputDoubleTo2DP(String userReadableString) {
		return UserInterface.getUserInputDoubleTo2DP(userReadableString, false);
	}

	/**
	 * Show the inputed userReadableString to the user and then receive an input
	 * from the user as a Double (can be auto-unboxed to double), if the
	 * received double has more than 2 decimal places it is rounded to 2 dp. If
	 * the printWarningMessage flag is set to true, the user is informed of the
	 * rounding.
	 * 
	 * If this value is printed, there is no guarantee it will be printed to 2DP
	 * if the last digit is a 0.
	 * 
	 * @param userReadableString
	 * @param printWarningMessage
	 * @return Double that the user inputed to 2 decimal places.
	 * @see getUserInputDoule(userReadableString)
	 */
	public static Double getUserInputDoubleTo2DP(String userReadableString, boolean printWarningMessage) {
		return UserInterface.getUserInputDoubleTo2DP(userReadableString, printWarningMessage, false);
	}

	/**
	 * Show the inputed userReadableString to the user and then receive an input
	 * from the user as a Double (can be auto-unboxed to double), if the
	 * received double has more than 2 decimal places it is rounded to 2 dp. If
	 * the printWarningMessage flag is set to true, the user is informed of the
	 * rounding. if the printCurrencyFlag is set to true then the global
	 * currency symbol is printed before the number.
	 * 
	 * If this value is printed, there is no guarantee it will be printed to 2DP
	 * if the last digit is a 0.
	 * 
	 * @param userReadableString
	 * @param printWarningMessage
	 * @param printCurrenyFlag
	 * @return Double that the user inputed to 2 decimal places.
	 * @see getUserInputDoule(userReadableString)
	 */
	public static Double getUserInputDoubleTo2DP(String userReadableString, boolean printWarningMessage,
			boolean printCurrencyFlag) {

		commonInit(userReadableString);
		Double gotDouble = getDoubleLogic();

		gotDouble = Math.round(gotDouble * 100.0) / 100.0;
		if (printWarningMessage) {
			sendUpwardsToUser(String.format(
					("We will assume you meant " + (printCurrencyFlag ? CURRENCY_SYMBOL : "") + "%.2f"), gotDouble));
		}
		return commonReturn(gotDouble);

	}

	/**
	 * DEPRECATED get a double from the user <br>
	 * Use instead: getUserInputDouble(userReadableString); where the inputed
	 * string is passed to the user prior to them inputting a double
	 * 
	 * @return Double
	 * @deprecated
	 * @see getUserInputDouble(userReadableString)
	 */
	public static Double getUserInputDouble() {
		commonLegacyInit();
		return commonReturn(getDoubleLogic());
	}

	/**
	 * @return the string "Welcome to the Casino!"
	 */
	public String welcome() {
		return "Welcome to the Casino!\nFor help whilst playing a game, just ask for \"help\"!";
	}

	/**
	 * checks if "whatToCheckItContains" is present in the "inputString"
	 * 
	 * @param inputString
	 * @param whatToCheckItContains
	 * @return boolean true if the input string is contained in the
	 *         whatToCheckItContains string
	 */
	public static boolean checkIfInputHas(String inputString, String whatToCheckItContains) {
		return inputString.toLowerCase().indexOf(whatToCheckItContains) != -1;
	}

	/**
	 * Push the inputed string up to the user. Currently only sends to
	 * system.out.println
	 * 
	 * @param userReadableString
	 */
	public static void sendUpwardsToUser(String userReadableString) {
		if (useSystemOutFunciton) {
			System.out.println(userReadableString);
		} else {
			throw new RuntimeException("Feature to transfer information up to user "
					+ "whilst not calling System.out has not yet been implemented");
		}
	}

	/**
	 * checks to see if the input string contains a double and then retrieves
	 * the double from the string input, if no number was found then return
	 * null. <br>
	 * <br>
	 * E.g.: <br>
	 * input = " this is a test input 12.14" <br>
	 * returns: (Double) 12.14.
	 * 
	 * 
	 * @param input
	 *            string which may contain a number
	 * @return Double or null
	 */
	public static Double getNumberOutOfString(String input) {
		try {
			return Double.valueOf(stripOtherCharsFromExpectedNumericString(input));
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * assign a player for the userInterface to perform help actions on.
	 * 
	 * @param player
	 */
	public static void assignPlayer(Player player) {
		UserInterface.player = player;
	}

	/**
	 * removes any reference to any player in the UserInterface.
	 */
	public static void clearPlayer() {
		player = null;
	}

	/**
	 * checks if there is a player assigned into the UserInterface
	 * 
	 * @return true if not null.
	 */
	public static boolean isPlayerAssigned() {
		return player != null;
	}

	/*
	 * *************************************************************************
	 * PRIVATE INTERNAL METHODS
	 * *************************************************************************
	 */

	private static void help(String inputString) {
		String message = "\n--- help menu ---\n";
		message += "Please select a help feature:\n" + " - check my balance and get transaction history\n"
				+ " - check the game rules\n" + " - leave the help interface";

		sendUpwardsToUser(message);
		String userInput = getStringLogic();

		if (checkIfInputHas(userInput, "rules") || checkIfInputHas(userInput, "game rules")) {
			rulesHelp(userInput);
		} else if (checkIfInputHas(userInput, "balance")) {
			balanceHelp(userInput);
		} else {
			return;
		}

	}

	private static void balanceHelp(String inputString) {
		if (!isPlayerAssigned()) {
			sendUpwardsToUser("This feature is unavailble without an active user in the casino.");
			return;
		}

		String message = "\n--- balance help menu ---\n";
		message += "Please select a feature:\n" + " - check my balance\n" + " - check my transaction history\n"
				+ " - deposit money into my accont";

		sendUpwardsToUser(message);
		String userInput = getStringLogic();

		if (checkIfInputHas(userInput, "balance")) {
			sendUpwardsToUser(getPlayerName() + ", your balance is: " + player.getBalanceAsString());
		} else if (checkIfInputHas(userInput, "transaction")) {
			if (player.getLog().getLog().size() == 0) {
				sendUpwardsToUser("no transactions have been made");
			} else {
				sendUpwardsToUser(player.getLog().printTransactionLog(player.getLog().getLog().size()));
			}
		} else if (checkIfInputHas(userInput, "deposit")) {
			player.increaseBalance(getUserInputDoubleTo2DP("How much do you want to deposit?", true, true));
		}

	}

	private static void rulesHelp(String inputString) {
		sendUpwardsToUser("Pleas ask an attendant for the current game rules :) ");
	}

	/**
	 * check if help is initiated
	 * 
	 * @param userInputString
	 * @return string that the user inputed after help completion
	 */
	private static String invokeHelp(String userInputString) {

		if (checkIfInputHas(userInputString, "rules") || checkIfInputHas(userInputString, "game rules")) {
			rulesHelp(userInputString);
		} else if (checkIfInputHas(userInputString, "balance")) {
			balanceHelp(userInputString);
		} else {
			help(userInputString);
		}

		sendUpwardsToUser("--- exiting help ---\n" + originalUserInput);
		return getStringLogic();
	}

	/**
	 * strips an input string of all other non-numeric characters and converts
	 * it to a double. This method can throw errors if the pattern is unmatched.
	 * 
	 * @param input
	 *            - input String
	 * @return double
	 * @throws NumberFormatException
	 * @throws IllegalStateException
	 */
	protected static double stripOtherCharsFromExpectedNumericString(String input)
			throws NumberFormatException, IllegalStateException {
		// Assign in the string to match, attempt to find and then
		Matcher matching = FLOAT_PATTERN.matcher(input);
		matching.find();
		String extractedDouble = matching.group();
		Double returner = Double.parseDouble(extractedDouble);
		return returner;
	}

	/**
	 * Logs the question asked to the user so it can be thrown back when exiting
	 * help.
	 * 
	 * @param currentUserInput
	 *            the currentUserInput to set
	 */
	protected static void setOriginalUserInput(String currentUserInput) {
		UserInterface.originalUserInput = currentUserInput;
	}

	/**
	 * common return feature that tidys up after any UserInterface method is
	 * called
	 * 
	 * @param T
	 *            toReturn
	 * @return T toReturn
	 */
	protected static <T> T commonReturn(T toReturn) {
		setOriginalUserInput(null);
		return toReturn;
	}

	/**
	 * Logs a default statement if UserInterface is called with a legacy
	 * feature.
	 */
	protected static void commonLegacyInit() {
		if (UserInterface.originalUserInput == null)
			UserInterface.originalUserInput = "\nPlease scroll up to answer the originally asked question prior to invoking help!";

	}

	/**
	 * common initialisation method for non-legacy code
	 * 
	 * @param userReadableString
	 */
	private static void commonInit(String userReadableString) {
		setOriginalUserInput(userReadableString);
		if (useSystemOutFunciton)
			sendUpwardsToUser(userReadableString);
	}

	/**
	 * call common logic for legacy and non-legacy methods
	 * 
	 * @return
	 */
	private static String getStringLogic() {
		String userInputString = sc.nextLine();
		if (UserInterface.checkIfInputHas(userInputString, "help")) {
			userInputString = invokeHelp(userInputString);
		}
		return userInputString;
	}

	/**
	 * invoke the double logic method, get a double from a user which is safely
	 * 
	 * @return
	 */
	private static double getDoubleLogic() {
		boolean condition = true;
		double output = 0;
		while (condition) {
			String doubleString = getStringLogic();
			try {
				output = stripOtherCharsFromExpectedNumericString(doubleString);
				condition = false;
			} catch (Exception e) {
				try {
					output = Double.parseDouble(doubleString);
					condition = false;
				} catch (Exception anotherE) {
					sendUpwardsToUser("Sorry, could you input a double?");
				}

			}
		}
		return output;
	}

	/**
	 * invoke the get int logic method, which invokes get double logic.
	 * 
	 * @return
	 */
	private static int getIntLogic() {
		return Integer.parseInt("" + Math.round(getDoubleLogic()));
	}

	/**
	 * get the current name of the player, or return an empty string.
	 * 
	 * @return
	 */
	private static String getPlayerName() {
		if (isPlayerAssigned()) {
			return player.getName();
		} else {
			return "";
		}
	}

}
