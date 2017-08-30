package io.zipcoder.Games;

import java.util.Scanner;

/**
 * Created by W550952 on 30/08/2017.
 */
public class UserInterface {
    public static String getUserInputString(){
        Scanner sc = new Scanner(System.in);
        String output = sc.nextLine();
        sc.close();
        return output;
    }

    public static int getUserInput(){
        Scanner sc = new Scanner(System.in);
        int output = sc.nextInt();
        sc.close();
        return output;
    }

    public String welcome(){
        return "Welcome to the Casino!";
    }
}
