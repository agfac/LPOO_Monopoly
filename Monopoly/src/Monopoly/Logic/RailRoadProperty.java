package Monopoly.Logic;

import java.util.*;

/**
 * 
 */
public class RailRoadProperty extends Property {
	private int[] rentValues;
	
    /**
     * Default constructor
     */
    public RailRoadProperty(int pos, String name) {
    	super(name,pos, 2, 200, 100);
    	this.rentValues = new int[] {25, 50, 100, 200};
    }
    

    /**
     * @param idGroup 
     * @param name 
     * @param pos 
     * @param amount 
     * @param mortgageValue 
     * @param mortgageValueBack 
     * @param rentValues
     */
    public void RailRoadProperty(int idGroup, String name, int pos, int amount, int mortgageValue, int mortgageValueBack, int[] rentValues) {
        // TODO implement here
    }

    /**
     * @return
     */
    public int getRentValues() {
        // TODO implement here
        return 0;
    }

}