package Monopoly.Logic;

import java.util.*;

/**
 * TaxBox Class
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
     * Return the Tax Value
     * @return tax value
     */
	public int getTaxValue() {
		return taxValue;
	}

}