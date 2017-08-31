package io.zipcoder;

import java.util.Scanner;

/**
 * Created by W550952 on 30/08/2017.
 */
public class UserInterface {
	public static Scanner sc = new Scanner(System.in);
    public static String getUserInputString(){
        //Scanner sc = new Scanner(System.in);
        String output = sc.nextLine();
        //sc.close();
        return output;
    }

    public static int getUserInput(){
        //Scanner sc = new Scanner(System.in);
        int output = sc.nextInt();
        // clear buffer
        sc.nextLine();
        //sc.close();
        return output;
    }
    
    public static Double getUserInputDouble(){
        //Scanner sc = new Scanner(System.in);
        double output = sc.nextDouble();
     // clear buffer
        sc.nextLine();
        //sc.close();
        return output;
    }

    public String welcome(){
        return "Welcome to the Casino!";
    }
}
