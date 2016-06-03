package Monopoly.Logic;

import java.util.*;

/**
 * Card Class
 */
public class Card {
	
    public String deckType;
    public int nrCard;
    
    /**
     * Default constructor
     */
    public Card() {
    }

    /**
     * @param deckType 
     * @param nrCards
     */
    public Card(String deckType, int nrCard) {
        this.deckType = deckType;
        this.nrCard = nrCard;
    }

    /**
     * @param player 
     */
    public void behaviorWithPlayer(Player player) {
        // TODO implement here
    }

}