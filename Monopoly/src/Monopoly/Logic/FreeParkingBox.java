package Monopoly.Logic;

import java.awt.image.BufferedImage;

/**
 * FreeParkingBox Class
 */
public class FreeParkingBox extends BoardBox{
	
	/**
     * Default constructor
     */
    public FreeParkingBox(BufferedImage image) {
    	this.pos = 20;
    	this.name = "Free Parking Box";
		this.image = image;
    }

}
