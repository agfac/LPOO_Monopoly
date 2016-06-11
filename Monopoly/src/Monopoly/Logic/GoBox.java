package Monopoly.Logic;

import java.awt.image.BufferedImage;
import java.util.*;

/**
 * GoBox Class
 */
public class GoBox extends BoardBox {
	
    /**
     * Default constructor
     */
    public GoBox(BufferedImage image) {
    	this.name = "GO BOX";
    	this.pos = 0;
		this.image = image;
    }
    public GoBox(){
    	this.name = "GO BOX";
    	this.pos = 0;
    }

}