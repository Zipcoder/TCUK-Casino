package io.zipcoder.Games;

import io.zipcoder.CardDealer;
import io.zipcoder.Cards.Card;
import io.zipcoder.Cards.Hand;
import io.zipcoder.Cards.Value;
import io.zipcoder.Handlers.Handler;
import io.zipcoder.Handlers.HighLowCardHandler;
import io.zipcoder.Player;
import io.zipcoder.UserInterface;

import java.util.HashMap;

public class HighLowCardGame {
    private HighLowCardHandler handler;
    private static HashMap<Value, Integer> values;
    private CardDealer dealer;
    private Player player;

    static {
        values = new HashMap<>();
        values.put(Value.ACE, 1);
        values.put(Value.TWO, 2);
        values.put(Value.THREE, 3);
        values.put(Value.FOUR, 4);
        values.put(Value.FIVE, 5);
        values.put(Value.SIX, 6);
        values.put(Value.SEVEN, 7);
        values.put(Value.EIGHT, 8);
        values.put(Value.NINE, 9);
        values.put(Value.TEN, 10);
        values.put(Value.JACK, 11);
        values.put(Value.QUEEN, 12);
        values.put(Value.KING, 13);
    }

    public void playGame(Player player) {
        this.player = player;
        handler = new HighLowCardHandler(player);
        dealer = new CardDealer();
        String again;

        System.out.println("Welcome to the Hi-Lo Card Game!");
        do {
            double amount = getBet();
            handler.makeStake(amount);
            Card cardRevealed = dealer.deal();
            Card cardHidden = dealer.deal();

            System.out.println("The first card is: " + cardRevealed + ".");
            String guess = getGuess();

            System.out.println("The second card is: " + cardHidden + ".");

            int comparison = getComparison(guess, cardRevealed, cardHidden);

            if (comparison == 1) {
                handler.cardHigher();
                System.out.println("You win!");
            } else if (comparison == -1) {
                handler.cardLower();
                System.out.println("You lose!");
            } else {
                System.out.println("The values were the same, you tied.");
            }
            UserInterface.sendUpwardsToUser("Your balance is: " + handler.getPlayer().getBalance());
            again =  playAgain();
        } while (again.equalsIgnoreCase("Yes"));

        System.out.println("Thank you for playing Higher or Lower Card Game!");
    }

    public int compareCardsHigher(Card card, Card otherCard) {
        // this checks if the first card is SMALLER than the second - that the second is higher
       if (values.get(card.getValue()) < values.get(otherCard.getValue())){
           return 1;
        }
       // this checks if the first card is LARGER than the second - that the second is lower
        else if (values.get(card.getValue()) > values.get(otherCard.getValue())){
            return -1;
        } else {
            return 0;
        }
    }

    public int compareCardsLower(Card card, Card otherCard) {
        // this checks if the first card is SMALLER than the second - that the second is higher
        if (values.get(card.getValue()) < values.get(otherCard.getValue())){
            return -1;
        }
        // this checks if the first card is LARGER than the second - that the second is lower
        else if (values.get(card.getValue()) > values.get(otherCard.getValue())){
            return 1;
        } else {
            return 0;
        }
    }

    public double getBet(){
        double amount = UserInterface.getUserInputDouble("How much would you like to bet?");
        while (amount < 0){
            amount = UserInterface.getUserInputDouble("You cannot bet a negative number, please enter a valid bet. How much would you like to bet?");
        }
        while (!player.checkBet(amount)){
            amount = UserInterface.getUserInputDouble("You don't have enough money to bet that! Your current balance is " + player.getBalanceAsString() + "How much would you like to bet?");
        }
        return amount;
    }

    public String getGuess(){
        String guess = UserInterface.getUserInputString("Please guess Higher or Lower.");
        while (!guess.equalsIgnoreCase("Higher") && !guess.equalsIgnoreCase("Lower")) {
            guess = UserInterface.getUserInputString("That is not a valid input. Please type Higher or Lower");
        }
        return guess;
    }

    public int getComparison(String input, Card cardRevealed, Card cardHidden){
        int comparison = 0;
        if (input.equalsIgnoreCase("Higher")){
            comparison = compareCardsHigher(cardRevealed, cardHidden);
        } else if (input.equalsIgnoreCase("Lower")){
            comparison = compareCardsLower(cardRevealed, cardHidden);
        }
        return comparison;
    }

    public String playAgain(){
        String again = UserInterface.getUserInputString("Do you want to play again? Please type Yes or No.");
        while (!again.equalsIgnoreCase("Yes") && !again.equalsIgnoreCase("No")){
            again = UserInterface.getUserInputString("That is not a valid input. Please say Yes or No.");
        }
        return again;
    }
}
