package Monopoly.Logic;

import java.util.Random;

/**
 * Dice class 
 */
public class Dice {
	private int value;
	
    /**
     * Default constructor of dice
     */
    public Dice() {
    }

    /**
     * @return value of dice generated
     */
    public int getValue() {
        return this.value;
    }

    /**
     * @return value generated randomly
     */
    public void rollDice() {
        Random gerador = new Random();
        this.value = gerador.nextInt(6)+1;
    }

}