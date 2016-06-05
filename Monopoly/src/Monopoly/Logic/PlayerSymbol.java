package Monopoly.Logic;

import java.awt.image.BufferedImage;
import java.util.*;

/**
 * Class PlayerSymbol
 */
public class PlayerSymbol {
	
	private int id;
    private String name;
    private BufferedImage piece;

    /**
     * Default constructor
     */

    public PlayerSymbol(int id, String name, BufferedImage piece ) {
    	this.id = id;
    	this.name = name;
    	this.piece = piece;
    }
   
	public BufferedImage getPiece() {
		return piece;
	}

	/**
     * @return id of player symbol
     */
    public int getId() {
        return this.id;
    }

    /**
     * @param value id to be set
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param value name to be set
     */
    public void setName(String value) {
        this.name = value;
    }

}