package Monopoly.Logic;

import java.awt.image.BufferedImage;
import java.util.*;

/**
 * JailBox Class
 */
public class JailBox extends BoardBox {

    /**
     * Default constructor
     */
    public JailBox(BufferedImage image) {
    	this.pos = 10;
    	this.name = "Jail Box";
		this.image = image;
    }

}