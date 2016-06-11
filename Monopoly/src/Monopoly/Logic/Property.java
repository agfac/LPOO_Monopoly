package Monopoly.Logic;

import java.awt.image.BufferedImage;
import java.util.*;

import org.omg.IOP.Codec;

/**
 * Property class
 */
public class Property extends BoardBox {

	protected int idGroup;
	protected int amount;
	protected boolean sold;
	protected Player owner;
	protected boolean mortgage;
	protected int mortgageValue;
	protected int mortgageValueBack;

	protected BufferedImage iconImage;

	/**
	 * Property Constructor
	 * 
	 * @param name
	 *            Name of property
	 * @param pos
	 *            Position on board of property
	 * @param idGroup
	 *            Id Group
	 * @param amount
	 *            Property value
	 * @param mortgageValue
	 *            Mortgage value
	 * @param icon
	 *            Property header
	 * @param image
	 *            Full property image
	 */
	public Property(String name, int pos, int idGroup, int amount, int mortgageValue, BufferedImage icon,
			BufferedImage image) {
		this.name = name;
		this.pos = pos;
		this.idGroup = idGroup;
		this.amount = amount;
		this.sold = false;
		this.owner = null;
		this.mortgage = false;
		this.mortgageValue = mortgageValue;
		this.mortgageValueBack = (int) (this.mortgageValue * 0.8);
		this.iconImage = icon;
		this.image = image;
	}

	/**
	 * Get property header image
	 * 
	 * @return header image
	 */

	public BufferedImage getIconImage() {
		return iconImage;
	}

	/**
	 * Check if property is sold
	 * 
	 * @return <code>true</code> if sold <code>false</code> if not
	 */
	public boolean getSold() {
		return this.sold;
	}

	/**
	 * Update property sold boolean
	 * 
	 * @param sold
	 */
	public void setSold(boolean sold) {
		this.sold = sold;
	}

	/**
	 * Get property amount value
	 * 
	 * @return amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * Check if property is mortgage
	 * 
	 * @return <code>true</code> if mortgage <code>false</code> if not
	 */
	public boolean getMortgage() {
		return mortgage;
	}

	/**
	 * Update property mortgage boolean 
	 * @param mortgage
	 */
	public void setMortgage(boolean mortgage) {
		this.mortgage = mortgage;
	}

	/**
	 * Get group ID
	 * 
	 * @return ID
	 */
	public int getIdGroup() {
		return idGroup;
	}

	/**
	 * Get property mortgage value
	 * 
	 * @return mortgage
	 */
	public int getMortgageValue() {
		return mortgageValue;
	}

	/**
	 * Get property mortgage back value
	 * 
	 * @return mortgage back
	 */
	public int getMortgageValueBack() {
		return mortgageValueBack;
	}

	/**
	 * Get property owner
	 * @return property owner
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * Update property owner
	 * @param player
	 */
	public void setOwner(Player player) {
		owner = player;
	}
}