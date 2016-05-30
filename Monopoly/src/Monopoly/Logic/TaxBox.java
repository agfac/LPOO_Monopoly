package Monopoly.Logic;

import java.util.*;

/**
 * 
 */
public class TaxBox extends BoardBox {
	private int taxValue;
	
    /**
     * Default constructor
     */
    public TaxBox(int pos, String name,int taxValue) {
    	this.pos = pos;
    	this.name = name;
    	this.taxValue = taxValue;
    }

    /**
     * @param post 
     * @param name 
     * @param taxValue
     */
    public void TaxBox(int post, String name, int taxValue) {
        // TODO implement here
    }

    /**
     * @param player 
     * @return
     */
    public void behaviorWithPlayer(Player player) {
        // TODO implement here
    }

}