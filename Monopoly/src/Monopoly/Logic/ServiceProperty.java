package Monopoly.Logic;

import java.util.*;

/**
 * 
 */
public class ServiceProperty extends Property {

    /**
     * Default constructor
     */
    public ServiceProperty(int pos, String name, int amount) {
    	this.pos = pos;
    	this.name = name;
    	this.amount = amount;
    }

    /**
     * 
     */
    public int[] rentValues;

    /**
     * @param idGroup 
     * @param name 
     * @param pos 
     * @param amount 
     * @param mortgageValue 
     * @param mortgageValueBack 
     * @param rentValues
     */
    public void ServiceProperty(int idGroup, String name, int pos, int amount, int mortgageValue, int mortgageValueBack, int[] rentValues) {
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