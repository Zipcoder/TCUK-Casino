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
    static HighLowCardHandler handler;
    static HashMap<Value, Integer> values;
    static CardDealer dealer;

    static {
        values = new HashMap<Value, Integer>();
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

    public static void playGame(Player player){
        /*
        Ask for bet
        Create 2 cards
        reveal 1 card
        ask "higher or lower"
        reveal second card
        call win or lose method in handler
        show remaining balance
         */

        handler = new HighLowCardHandler(player);
        dealer = new CardDealer();

        System.out.println("Welcome to the Hi-Lo Card Game! How much would you like to bet?");
        handler.makeStake(UserInterface.getUserInputDouble());
        Card cardRevealed = dealer.deal();
        Card cardHidden = dealer.deal();

        System.out.println("The first card is: " + cardRevealed + ".\nPlease guess Higher or Lower.");
        String input = UserInterface.getUserInputString();
        while (!input.equalsIgnoreCase("Higher") && !input.equalsIgnoreCase("Lower")){
            System.out.println("That is not a valid input. Please type Higher or Lower");
            input = UserInterface.getUserInputString();
        }

        System.out.println("The second card is: " + cardHidden + ".");

        int comparison = compareCards(cardRevealed, cardHidden);

        if (input.equalsIgnoreCase("Higher")){
            if (comparison == 1){
                handler.cardHigher();
                System.out.println("You win!");
            } else if (comparison == -1){
                handler.cardLower();
                System.out.println("You lose!");
            } else {
                System.out.println("The values were the same, you tied.");
            }
        } else if (input.equalsIgnoreCase("Lower")){
            if (comparison == -1){
                handler.cardHigher();
                System.out.println("You win!");
            } else if (comparison == 1){
                handler.cardLower();
                System.out.println("You lose!");
            } else {
                System.out.println("The values were the same, you tied.");
            }
        }

        System.out.println("Your balance is: " + handler.getPlayer().getBalance());
    }

    public static int compareCards(Card card, Card otherCard) {
        // this checks if the first card is SMALLER than the second - that the second is higher
       if (values.get(card.getValue()) < values.get(otherCard.getValue())){
           return 1;
        }
       // this checks if the first card is LARGER than the second - that the first is higher
        else if (values.get(card.getValue()) > values.get(otherCard.getValue())){
            return -1;
        } else {
            return 0;
        }
    }
}
