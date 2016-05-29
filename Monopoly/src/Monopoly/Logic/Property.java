package Monopoly.Logic;

import java.util.*;

/**
 * 
 */
public class Property extends BoardBox {

    /**
     * 
     */
    private int idGroup;

    /**
     * 
     */
    private int amount;

    /**
     * 
     */
    private boolean sold;

    /**
     * 
     */
    private Player owner;

    /**
     * 
     */
    private boolean mortgage;

    /**
     * 
     */
    private int mortgageValue;

    /**
     * 
     */
    private int mortgageValueBack;


    /**
     * Default constructor
     */
    public Property() {
    }

    /**
     * @return
     */
    public boolean getSold() {
        return false;
    }

    /**
     * @param sold  
     * @return
     */
    public void setSold(boolean sold ) {
        this.sold = sold;
    }

    /**
     * @return
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @return
     */
    public boolean getMortgage() {
        return mortgage;
    }

    /**
     * @param mortgage 
     * @return
     */
    public void setMortgage(boolean mortgage) {
        this.mortgage = mortgage;
    }

    /**
     * @return
     */
    public int getIdGroup() {
        return idGroup;
    }

    /**
     * @return
     */
    public int getMortgageValue() {
        return mortgageValue;
    }
    
    /**
     * @return
     */
    public int getMortgageValueBack() {
        return mortgageValueBack;
    }
    /**
     * @param player 
     * @return
     */
    public void behaviorWithPlayer(Player player) {
    	owner = player;
    }

    /**
     * @return
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * @param player
     */
    public void setOwner(Player player) {
    	owner = player;
    }

    /**
     * 
     */
    public class AssociationClass1 {

        /**
         * Default constructor
         */
        public AssociationClass1() {
        }

    }

}