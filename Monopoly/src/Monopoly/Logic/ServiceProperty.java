package Monopoly.Logic;

import java.util.*;

/**
 * 
 */
public class ServiceProperty extends Property {
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
    public ServiceProperty(int pos, String name, int idGroup, int amount, int mortgageValue, int[] rentValues) {
    	super(name, pos, idGroup, amount, mortgageValue);
    }

    /**
     * @return
     */
    public int getRentValues() {
        return 0;
    }

}