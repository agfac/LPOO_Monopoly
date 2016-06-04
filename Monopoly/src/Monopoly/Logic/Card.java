package Monopoly.Logic;

import java.util.*;

/**
 * Card Class
 */
public class Card {
	
	private String deckType;
	private int nrCard;
    

    /**
     * Card constructor
     * @param deckType description of the card
     * @param nrCards number of card
     */
    public Card(String deckType, int nrCard) {
        this.deckType = deckType;
        this.nrCard = nrCard;
    }

    /**
     * Get the card number
     * @return card number
     */
	public int getNrCard() {
		return nrCard;
	}
    
}