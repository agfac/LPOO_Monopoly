package Monopoly.Logic;

import java.awt.image.BufferedImage;
import java.util.*;

/**
 * Card Class
 */
public class Card {

	private String deckType;
	private int nrCard;
	private BufferedImage image;

	/**
	 * Card constructor
	 * 
	 * @param deckType
	 *            description of the card
	 * @param nrCards
	 *            number of card
	 * @param image
	 *            of the card (Chance or Community)
	 */
	public Card(String deckType, int nrCard, BufferedImage image) {
		this.deckType = deckType;
		this.nrCard = nrCard;
		this.image = image;
	}

	/**
	 * Get card image
	 * 
	 * @return card image
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * Get the card number
	 * 
	 * @return card number
	 */
	public int getNrCard() {
		return nrCard;
	}

}