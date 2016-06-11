package Monopoly.Logic;

import java.awt.image.BufferedImage;
import java.util.*;

/**
 * TaxBox Class
 */
public class TaxBox extends BoardBox {
	private int taxValue;
	
    /**
     * Default constructor
     */
    public TaxBox(int pos, String name,int taxValue, BufferedImage image) {
    	this.pos = pos;
    	this.name = name;
    	this.taxValue = taxValue;
		this.image = image;
    }
    
    /**
     * Return the Tax Value
     * @return tax value
     */
	public int getTaxValue() {
		return taxValue;
	}

}