package Monopoly.Logic;

import java.util.*;

/**
 * 
 */
public class Property extends BoardBox {

    protected int idGroup;
    protected int amount;
    protected boolean sold;
    protected Player owner;
    protected boolean mortgage;
    protected int mortgageValue;
    protected int mortgageValueBack;

    /**
     * Default constructor
     */
    public Property(){
    	
    }
    
    public Property(String name, int pos, int idGroup, int amount, int mortgageValue) {
    	this.name = name;
    	this.pos = pos;
    	this.idGroup = idGroup;
    	this.amount = amount;
    	this.sold = false;
    	this.owner = null;
    	this.mortgage = false;
    	this.mortgageValue = mortgageValue;
    	this.mortgageValueBack = (int) (this.mortgageValue*0.8);
    	
    }

    /**
     * @return
     */
    public boolean getSold() {
        return this.sold;
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