package Monopoly.Logic;

import java.awt.image.BufferedImage;
import java.util.*;

/**
 * NormalProperty class
 */
public class NormalProperty extends Property {

	private int nrHouses;
	private int nrHotels;

	private int[] rentValuesWithHouses;

	private int[] rentValues;

	private int rentValuesWithHotel;
	private int hotelCost;
	private int houseCost;

	//TODO apagar
	public void setHouseN(int n){
		nrHouses = n;
	}
	
	public void setHotelN(int n){
		nrHotels = n;
	}
	

	//---------------------------------
	
	/**
	 * NormalProperty Constructor
	 * @param pos Position on board
	 * @param name Name of the property
	 * @param idGroup Id Group
	 * @param amount Cost property
	 * @param mortgageValue Mortgage cost
	 * @param rentValue Rent Cost
	 * @param rentValuesWithHouses Rent Cost with houses 
	 * @param rentValuesWithHotel Rent Cost with hotels
	 */
	public NormalProperty(int pos, String name, int idGroup, int amount, int mortgageValue, int rentValue,
			int[] rentValuesWithHouses, int rentValuesWithHotel, BufferedImage icon, BufferedImage image) {
		super(name, pos, idGroup, amount, mortgageValue, icon, image);

		this.rentValues = new int[2];
		this.rentValues[0] = rentValue;
		this.rentValues[1] = (int) (rentValue * 1.2);

		this.rentValuesWithHouses = rentValuesWithHouses;
		this.rentValuesWithHotel = rentValuesWithHotel;
		
		switch (idGroup) {
		case 1:
			this.houseCost = 50;
			this.hotelCost = 50;
			break;
		case 3:
			this.houseCost = 50;
			this.hotelCost = 50;
			break;
		case 4:
			this.houseCost = 100;
			this.hotelCost = 100;
			break;
		case 5:
			this.houseCost = 100;
			this.hotelCost = 100;
			break;
		case 6:
			this.houseCost = 150;
			this.hotelCost = 150;
			break;
		case 7:
			this.houseCost = 150;
			this.hotelCost = 150;
			break;
		case 8:
			this.houseCost = 200;
			this.hotelCost = 200;
			break;
		case 9:
			this.houseCost = 200;
			this.hotelCost = 200;
			break;
		}
		this.nrHouses = 0;
		this.nrHotels = 0;
	}
	
	/**
	 * Method to check if can build houses or not
	 * @return true if number of houses is less than 4, false if not
	 */
	public boolean canBuildHouse(){
		return (nrHouses < 4)? true : false;
	}

	/**
	 * Method to check if can build hotel or not
	 * @return true if number of houses is 4, false if not
	 */
	public boolean canBuildHotel() {
		return (nrHouses == 4)? true : false;
	}

	/**
	 * Method to add houses
	 * @param n number of houses to be added
	 */
	public void buildHouse(int n) {
		nrHouses += n;
	}

	/**
	 * Method to update the build hotel
	 */
	public void buildHotel() {
		nrHotels = 1;
	}

	/**
	 * Sell houses
	 * @param n number of houses to be sold
	 * @return
	 */
	public void sellHouse(int n) {
		nrHouses -= n;
	}

	/**
	 * Sell hotel
	 * @return
	 */
	public void sellHotel() {
		nrHotels = 0;
	}

	/**
	 * Get number of houses
	 * @return number of houses
	 */
	public int getNrHouses() {
		return nrHouses;
	}

	/**
	 * Get number of hotels
	 * @return number of hotels
	 */
	public int getNrHotels() {
		return nrHotels;
	}

	/**
	 * Get house cost
	 * @return house cost
	 */
	public int getHouseCost() {
		return houseCost;
	}

	/**
	 * Get hotel cost
	 * @return hotel cost
	 */
	public int getHotelCost() {
		return hotelCost;
	}
	
	/**
	 * Get the value to pay on this property
	 * @return value to pay
	 */
	public int getValueToPay() {
		if (owner.haveAllPropertiesGroup(this)) {
			if (nrHouses != 0 || nrHotels != 0) {
				switch (nrHouses) {
				case 1:
					return rentValuesWithHouses[0];
				case 2:
					return rentValuesWithHouses[1];
				case 3:
					return rentValuesWithHouses[2];
				case 4:
					return rentValuesWithHouses[3];
				}

				if (nrHotels == 1)
					return rentValuesWithHotel;

			} else
				return rentValues[1];
		}
		return rentValues[0];
	}

}