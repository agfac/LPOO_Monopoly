package Monopoly.Logic;

import java.awt.image.BufferedImage;
import java.util.*;

/**
 * Community Class
 */
public class CommunityBox extends BoardBox {

	/**
	 * Default constructor
	 */
	public CommunityBox(int pos, BufferedImage image) {
		this.pos = pos;
		this.name = "Community Box";
		this.image = image;
	}

}