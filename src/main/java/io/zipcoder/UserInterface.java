package io.zipcoder;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by W550952 on 30/08/2017.
 */
public class UserInterface {

	public static Scanner sc = new Scanner(System.in);

	public static String getUserInputString() {

		String output = sc.nextLine();

		if (UserInterface.checkIfInputHas(output, "help")) {
			help();
		}

		return output;
	}
	
	public static double stripOtherCharsFromExpectedNumericString(){
		return 0.0;
	}

	public static int getUserInput() {

		boolean condition = true;
		int output = 0;
		while (condition) {
			String intString = getUserInputString();
			try{
				output = Integer.parseInt(intString);
				condition = false;
			} catch (Exception e) {
				System.out.println("Sorry, could you input an integer?");
			}
		}
		return output;
	}

	public static Double getUserInputDouble() {

		boolean condition = true;
		double output = 0;
		while (condition) {
			String doubleString = getUserInputString();
			try{
				output = Double.parseDouble(doubleString);
				condition = false;
			} catch (Exception e) {
				System.out.println("Sorry, could you input a double?");
			}
		}

		return output;
	}

	public String welcome() {
		return "Welcome to the Casino!";
	}

	public static boolean checkIfInputHas(String inputString, String whatToCheckItContains) {
		return inputString.toLowerCase().indexOf(whatToCheckItContains) != -1;
	}

	private static void help() {
		System.out.println("help triggered");
	}
}
