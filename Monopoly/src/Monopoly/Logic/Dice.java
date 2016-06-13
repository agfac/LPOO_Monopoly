package Monopoly.Logic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import Monopoly.Gui.ImageLoader;

/**
 * Dice class
 */
public class Dice extends ImageLoader {
	private int value;
	private static BufferedImage diceFace1;
	private static BufferedImage diceFace2;
	private static BufferedImage diceFace3;
	private static BufferedImage diceFace4;
	private static BufferedImage diceFace5;
	private static BufferedImage diceFace6;

	/**
	 * Default constructor of dice
	 */
	public Dice() {

		try {
			diceFace1 = ImageIO.read(new File("resources/images/dice/face1.png"));
			diceFace2 = ImageIO.read(new File("resources/images/dice/face2.png"));
			diceFace3 = ImageIO.read(new File("resources/images/dice/face3.png"));
			diceFace4 = ImageIO.read(new File("resources/images/dice/face4.png"));
			diceFace5 = ImageIO.read(new File("resources/images/dice/face5.png"));
			diceFace6 = ImageIO.read(new File("resources/images/dice/face6.png"));
		} catch (

		IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		System.out.println("sou o dado e o meu valor é ---------> " + value);
	}

	/**
	 * Get image of actual dice value
	 * @return Image of dice value
	 */
	public BufferedImage getImage() {
		BufferedImage aux;
		switch (value) {
		case 1:
			aux = diceFace1;
			break;
		case 2:
			aux = diceFace2;
			break;
		case 3:
			aux = diceFace3;
			break;
		case 4:
			aux = diceFace4;
			break;
		case 5:
			aux = diceFace5;
			break;
		case 6:
			aux = diceFace6;
			break;
		default:
			aux = null;
		}
		return aux;
	}

}