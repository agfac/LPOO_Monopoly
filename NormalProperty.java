
import java.util.*;

/**
 * 
 */
public class NormalProperty extends Property {

    /**
     * Default constructor
     */
    public NormalProperty() {
    }

    /**
     * 
     */
    public int nrHouses;

    /**
     * 
     */
    public int nrHotels;

    /**
     * 
     */
    public int[] rentValues;

    /**
     * 
     */
    public int[] rentValuesWithHouses;

    /**
     * 
     */
    public int rentValuesWithHotel;

    /**
     * 
     */
    public int hotelCost;

    /**
     * 
     */
    public int houseCost;

    /**
     * @param idGroup 
     * @param name 
     * @param pos 
     * @param amount 
     * @param mortgageValue 
     * @param mortgageValueBack 
     * @param rentValues 
     * @param rentValuesWithHouses 
     * @param rentValuesWithHotel 
     * @param hotelCost 
     * @param houseCost
     */
    public void NormalProperty(int idGroup, String name, int pos, int amount, int mortgageValue, int mortgageValueBack, int[] rentValues, int[] rentValuesWithHouses, int rentValuesWithHotel, int hotelCost, int houseCost) {
        // TODO implement here
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

}