/*
Workshop 1
File: Craps.java
Date: May 24 2019
Author: Tashi Tsering
*/

import java.util.Random;

public class Craps {

    // creates a random number generator for use in the methods rollDice
    private Random randomNumbers = new Random();

    // enumeration with constants that represent the game status
    // set of named constants
    private enum Status {CONTINUE, WON, LOST};

    // constants that represent common rolls of the dice
    private final static int TWO = 2;
    private final static int THREE = 3;
    private final static int SEVEN = 7;
    private final static int ELEVEN = 11;
    private final static int TWELVE = 12;


    // plays the game of craps
    public void play () {
        int myPoint = 0; // point if no win or loss on the first roll
        Status gameStatus; // can contain contnue, WON or LOST

        int sumOfDice = rollDice();     // fist roll of dice

        // determines the game status based on the first roll
        switch (sumOfDice) {
            case SEVEN:         // win with 7 on first roll
            case ELEVEN:      // win with 11 on first roll
                gameStatus = Status.WON;
                break;
            case TWO:    // lose with 2 on first roll
            case THREE:          // lose with 3 on first roll
            case TWELVE:      // lose with 12 on first roll
                gameStatus = Status.LOST;
                break;
            default:            // did not win or lose, keeps point
                gameStatus = Status.CONTINUE;   // game isn't over
                myPoint = sumOfDice;            // remembers the point
                System.out.printf("Point is %d \n", myPoint);
                break;
        } // end switch statement

        // while game is not complete

        while (gameStatus == Status.CONTINUE) { // player hasn't won or lost

            sumOfDice = rollDice();     // roll dice again

            // determines the game status
            if (sumOfDice == myPoint)
                gameStatus = Status.WON;
            else if (sumOfDice == SEVEN) // lose by rolling 7 before point
                gameStatus = Status.LOST;
        } // end while

        // display if player won or lost message
        if (gameStatus == Status.WON)
            System.out.println("Player Wins");
        else
            System.out.println(("Player Loses"));
    }

    public int rollDice() {

        // pick random die values
        int die1 = 1 + randomNumbers.nextInt(6); // roll first dice
        int die2 = 1 + randomNumbers.nextInt(6); // roll second dice

        int sum = die1 + die2; // add both dice

        // display the result of this roll
        System.out.printf("You rolled %d + %d = %d \n", die1, die2, sum);

        return sum; // return sum of dice

    } //  end rollDice method

    public static void main(String args[] ) {
        Craps game = new Craps();
        game.play();
    }
} // end class craps
