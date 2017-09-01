package io.zipcoder;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by W550952 on 30/08/2017.
 */
public class UserInterface {

	private static Scanner sc = new Scanner(System.in);
	private static boolean useSystemOutFunciton = true;

	/**
	 * Show the inputed userReadableString to the user and then receive an input
	 * from the user as a string
	 * 
	 * @param userReadableString
	 * @return string input from the user
	 */
	public static String getUserInputString(String userReadableString) {
		if (useSystemOutFunciton)
			sendUpwardsToUser(userReadableString);
		return getUserInputString();
	}

	public static String getUserInputString() {

		String output = sc.nextLine();

		if (UserInterface.checkIfInputHas(output, "help")) {
			help();
		}

		return output;
	}

	/**
	 * Show the inputed userReadableString to the user and then receive an input
	 * from the user as an integer
	 * 
	 * @param userReadableString
	 * @return int user input
	 */
	public static int getUserInput(String userReadableString) {
		if (useSystemOutFunciton)
			sendUpwardsToUser(userReadableString);
		return getUserInput();
	}

	public static int getUserInput() {

		boolean condition = true;
		int output = 0;
		while (condition) {
			String intString = getUserInputString();
			try {
				output = Integer.parseInt(intString);
				condition = false;
			} catch (Exception e) {
				sendUpwardsToUser("Sorry, could you input an integer?");
			}
		}
		return output;
	}

	/**
	 * Show the inputed userReadableString to the user and then receive an input
	 * from the user as a Double (can be auto-unboxed to double)
	 * 
	 * @param userReadableString
	 * @return Double user input
	 */
	public static Double getUserInputDouble(String userReadableString) {
		if (useSystemOutFunciton)
			sendUpwardsToUser(userReadableString);
		return getUserInputDouble();
	}

	public static Double getUserInputDouble() {

		boolean condition = true;
		double output = 0;
		while (condition) {
			String doubleString = getUserInputString();
			try {
				output = Double.parseDouble(doubleString);
				condition = false;
			} catch (Exception e) {
				sendUpwardsToUser("Sorry, could you input a double?");
			}
		}

		return output;
	}

	public String welcome() {
		return "Welcome to the Casino!";
	}

	/**
	 * checks if "whatToCheckItContains" is present in the "inputString"
	 * 
	 * @param inputString
	 * @param whatToCheckItContains
	 * @return boolean true if the input string is contained in the whatToCheckItContains string
	 */
	public static boolean checkIfInputHas(String inputString, String whatToCheckItContains) {
		return inputString.toLowerCase().indexOf(whatToCheckItContains) != -1;
	}

	private static void help() {
		System.out.println("help triggered");
	}

	private static double stripOtherCharsFromExpectedNumericString() {
		return 0.0;
	}

	/**
	 * Push the inputed string up to the user. Currently only sends to system.out.println
	 * 
	 * @param userReadableString
	 */
	public static void sendUpwardsToUser(String userReadableString) {
		if (useSystemOutFunciton) {
			System.out.println(userReadableString);
		} else {
			throw new RuntimeException("Feature yet to be implemented");
		}
	}

}
