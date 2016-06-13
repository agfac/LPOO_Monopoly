package Monopoly.Logic;

import java.awt.image.BufferedImage;
import java.util.*;

/**
 * ServiceProperty Class
 */
public class ServiceProperty extends Property {
	private int[] rentValue;

	/**
	 * Constructor of ServiceProperty
	 * 
	 * @param pos
	 *            Position
	 * @param name
	 *            Name
	 * @param idGroup
	 *            IDGroup
	 * @param amount
	 *            Value of service property
	 * @param mortgageValue
	 *            Mortgage value
	 * @param rentValues
	 *            Array of rent values
	 * @param icon
	 * 			Property header
	 * @param image
	 * 			Full property image
	 */
	public ServiceProperty(int pos, String name, int idGroup, int amount, int mortgageValue, int[] rentValues,
			BufferedImage icon, BufferedImage image) {
		super(name, pos, idGroup, amount, mortgageValue, icon, image);
		this.rentValue = rentValues;
	}

	/**
	 * Method to return value to pay by dice value and number of properties
	 * @param diceValue Value of dice
	 * @return rent value to pay
	 */
	public int getValueToPay(int diceValue) {
		if (owner.getPropertiesNr(((Property) this)) == 2)
			return (rentValue[1] * diceValue);
		return (rentValue[0] * diceValue);
	}

}