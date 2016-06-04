package Monopoly.Logic;

import java.util.*;

/**
 * RailRoadProperty Class
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
	 * Get the value to pay on this Rail Road
	 * @return value to pay
	 */
	public int getValueToPay() {
		if (owner.getPropertiesNr(((Property) this)) == 2) 
			return rentValues[1];
		if (owner.getPropertiesNr(((Property) this)) == 3) 
			return rentValues[2];
		if (owner.getPropertiesNr(((Property) this)) == 4) 
			return rentValues[3];
		else
			return rentValues[0];
	}

}