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
	 * Constructor
	 * 
	 * @param id
	 *            symbol
	 * @param name
	 *            symbol
	 * @param piece
	 *            symbol
	 */
	public PlayerSymbol(int id, String name, BufferedImage piece) {
		this.id = id;
		this.name = name;
		this.piece = piece;
	}

	/**
	 * Get symbol image
	 * 
	 * @return symbol image
	 */
	public BufferedImage getPiece() {
		return piece;
	}

	/**
	 * Get id of player symbol
	 * 
	 * @return id of player symbol
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Set symbol ID
	 * 
	 * @param value
	 *            id to be set
	 */
	public void setId(int value) {
		this.id = value;
	}

	/**
	 * Get symbol name
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

}