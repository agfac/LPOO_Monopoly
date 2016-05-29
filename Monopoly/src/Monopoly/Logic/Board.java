package Monopoly.Logic;

import java.util.*;

/**
 * QfcWAvgW
 */
public class Board {

    /**
     * Default constructor
     */
    public Board() {
    }

    /**
     * 
     */
    public Vector<Player> players;

    /**
     * 
     */
    public Vector<Deck> deck;

    /**
     * 
     */
    public BoardBox[] boxs;


    /**
     * @param players
     */
    public void Board(Vector<Player> players) {
    	this.players = players;
    	deck.add(new Deck());
    	deck.add(new Deck());
    	
    	
    	// ciclo para criar tds as propiendades e guardar no -> boxs
    }

}