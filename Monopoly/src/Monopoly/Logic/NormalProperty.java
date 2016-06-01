package Monopoly.Logic;

import java.util.*;

/**
 * 
 */
public class NormalProperty extends Property {

	private int nrHouses;
	private int nrHotels;
	
	private int[] rentValuesWithHouses;
	
	private int[] rentValues;
	
	private int rentValuesWithHotel;
	private int hotelCost;
	private int houseCost;
    
    /**
     * Default constructor
     */
    public NormalProperty() {
    	
    }

    /**
     * 
     */
    public NormalProperty(int pos, String name, int idGroup, int amount, int mortgageValue, int rentValue, int[] rentValuesWithHouses, int rentValuesWithHotel) {
        super(name, pos, idGroup, amount, mortgageValue);
      
        this.rentValues = new int[2];
        this.rentValues[0] = rentValue;
        this.rentValues[1] = (int) (rentValue*1.2);
        /*
        if (idGroup!=1 && idGroup!=9){
        	rentValues = new int[3];
        	this.rentValues[2] = (int) (rentValue*1.2);
        }
        else
        	rentValues = new int[2];
        
        this.rentValues[0] = rentValue;
        this.rentValues[1] = (int) (rentValue*1.1);
      */
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
     * @return
     */
    public boolean canBuildHouse() {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public boolean canBuildHotel() {
        // TODO implement here
        return false;
    }

    /**
     * @param n 
     * @return
     */
    public void buildHouse(int n) {
        // TODO implement here
    }

    /**
     * @param n 
     * @return
     */
    public void buildHotel(int n) {
        // TODO implement here
    }

    /**
     * @param n 
     * @return
     */
    public void sellHouse(int n) {
        // TODO implement here
    }

    /**
     * @param n 
     * @return
     */
    public void sellHotel(int n) {
        // TODO implement here
    }

    /**
     * @return
     */
    public int getNrHouses() {
        // TODO implement here
        return 0;
    }

    /**
     * @return
     */
    public int getNrHotels() {
        // TODO implement here
        return 0;
    }

    /**
     * @return
     */
    public int getHouseCost() {
        // TODO implement here
        return 0;
    }

    /**
     * @param value 
     * @return
     */
    public void setHouseCost(int value) {
        // TODO implement here
    }

    /**
     * @return
     */
    public int getHotelCost() {
        // TODO implement here
        return 0;
    }

    /**
     * @param value 
     * @return
     */
    public void setHotelCost(int value) {
        // TODO implement here
    }

    /**
     * @return
     */
    public int getRentValues() {
        // TODO implement here
        return 0;
    }

    /**
     * @return
     */
    public int getRentValuesWithHouses() {
        // TODO implement here
        return 0;
    }

    /**
     * @return
     */
    public int getRentValuesWithHotel() {
        // TODO implement here
        return 0;
    }
    
    public int getValueToPay(){
    	if(owner.haveAllPropertiesGroup(this)){
    		if(nrHouses != 0 || nrHotels != 0){
    			switch (nrHouses){
    			case 1:
    				return rentValuesWithHouses[0];
    			case 2:
    				return rentValuesWithHouses[1];
    			case 3:
    				return rentValuesWithHouses[2];
    			case 4:
    				return rentValuesWithHouses[3];
    			}
    			
    			if( nrHotels == 1)
    				return rentValuesWithHotel;
    			
    		}else
    			return rentValues[1];
    	}
    	return rentValues[0];
    }

}