package Monopoly.Logic;

import java.awt.image.BufferedImage;
import java.util.*;

/**
 * BoardBox Class
 */
public class BoardBox {

    protected String name;
    protected int pos ;
    
    protected BufferedImage image;

    /**
     * Default constructor of BoardBox
     */
    public BoardBox() {
    }
    
    /**
     * BoardBox Constructor
     * @param name on Board
     * @param pos on Board
     */
    public BoardBox(String name, int pos) {
    	this.name = name;
    	this.pos = pos;
    }
    
    /**
     * Get boardbox name
     * @return boardbox name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get boardbox position
     * @return boardbox position
     */
    public int getPos() {
        return this.pos;
    }

	/**
	 * Get boardbox image
	 * 
	 * @return property image
	 */
	public BufferedImage getImage() {
		return image;
	}
}