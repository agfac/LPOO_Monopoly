package Monopoly.Logic;

import java.util.*;

/**
 * ServiceProperty Class
 */
public class ServiceProperty extends Property {
	private int[] rentValue;

    /**
     * Constructor of ServiceProperty
     * @param pos Position
     * @param name Name
     * @param idGroup IDGroup
     * @param amount Value of service property
     * @param mortgageValue Mortgage value
     * @param rentValues Array of rent values
     */
    public ServiceProperty(int pos, String name, int idGroup, int amount, int mortgageValue, int[] rentValues) {
    	super(name, pos, idGroup, amount, mortgageValue);
    	this.rentValue = rentValues;
    }

    /**
     * @return
     */
    public int getValueToPay(int diceValue) {
    	if (owner.getPropertiesNr(((Property) this)) == 2) 
			return (rentValue[1]*diceValue);
    	return (rentValue[0]*diceValue);
    }

}