package io.zipcoder;

/**
 * Created by W550952 on 30/08/2017.
 */
public class Player implements Bettable {
    private double balance;
    private String name;

    public Player(String name, double balance){
        this.balance = balance;
        this.name = name;
    }

    public double getBalance(){
        return balance;
    }

    public String getName(){
        return name;
    }

    public boolean increaseBalance(double amount){
        if (amount > 0){
            balance += amount;
            return true;
        } else {
            System.out.println("Cannot increase by zero or less");
            return false;
        }
    }

    public boolean reduceBalance(double amount){
        if (amount > 0 && (balance - amount) > 0){
            balance -= amount;
            return true;
        } else if ((balance - amount) < 0){
            System.out.println("Not enough money");
            return false;
        } else {
            System.out.println("Cannot reduce by zero or less");
            return false;
        }
    }
}
