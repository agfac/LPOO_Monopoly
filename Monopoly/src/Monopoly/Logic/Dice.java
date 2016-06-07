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
	 * Get generated dice values
	 * 
	 * @return generated dice values
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * Roll a dice and generate a number
	 * 
	 * @return value generated randomly
	 */
	public void rollDice() {
		Random gerador = new Random();
		this.value = gerador.nextInt(6) + 1;
	}

}