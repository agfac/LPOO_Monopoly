package Monopoly.Logic;

import java.awt.image.BufferedImage;
import java.util.*;

/**
 * ChanceBox class
 */
public class ChanceBox extends BoardBox {

	/**
	 * Default constructor
	 */
	public ChanceBox(int pos, BufferedImage image) {
		this.pos = pos;
		this.name = "Chance Box";
		this.image = image;
	}

}