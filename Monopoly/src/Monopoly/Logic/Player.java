package Monopoly.Logic;

import java.util.HashMap;
import java.util.Vector;

/**
 * Class Player
 */
public class Player {
	
    private BoardBox pos;
    private int id;
    private String name;
    private int balance;
    private PlayerSymbol symbol;
    private boolean inJail;
    private Vector<Card> cardsOwned;
    private Vector<Property> propertiesOwned;
    private int nrOfRolls;
    private int nrOfRollsInJail;
    private HashMap <GroupProperty,Integer> propertyGroup;
    
	/**
     * Default constructor of Player
     */
    public Player() {	
    }
    
    /**
     * Constructor of Player
     * @param name of player
     * @param piece symbol that identify the player
     * @param balance value that player have in their wallet
     */
    public Player(String name, PlayerSymbol piece, int balance, BoardBox pos) {
        this.name = name;
        this.symbol = piece;
        this.balance = balance;
        this.inJail = false;
        this.cardsOwned = new Vector<Card>();
        this.propertiesOwned = new Vector<Property>();
        this.pos = pos;
        this.nrOfRolls = 0;
    }

    /**
     * @return ID of the player
     */
    public int getId() {
        return this.id;
    }

    /**
     * @return name of the player
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name of the player
     */
    public void setName(String name) {
    	this.name = name;
    }

    /**
     * @return balance of the player
     */
    public int getBalance() {
        return balance;
    }

    /**
     * @param balance of the player
     */
    public void setBalance(int balance) {
    	this.balance = balance;
    }

    /**
     * @return Symbol of the player
     */
    public PlayerSymbol getSymbol() {
        return symbol;
    }

    /**
     * @param symbol of the player
     */
    public void setSymbol(PlayerSymbol symbol) {
    	this.symbol = symbol;
    }

    /**
     * @return Position of the player
     */
    public BoardBox getPos() {
        return pos;
    }

    /**
     * @param pos Position of the player
     */
    public void setPos(BoardBox pos) {
        this.pos = pos;
    }

    /**
     * @return True if player is in Jail, false if player not in jail
     */
    public boolean getInJail() {
        return inJail;
    }

    /**
     * @param inJail True if player goes to the jail
     */
    public void setInJail(boolean inJail) {
    	this.inJail = inJail;
    }

    /**
     * @return Cards owned
     */
    public Vector<Card> getCardsOwned() {
        return cardsOwned;
    }

    /**
     * @param card to be added to the player owned cards
     */
    public void addCardsOwned(Card card) {
    	cardsOwned.addElement(card);
    }
    
    /**
     * @param card to be removed from player owned cards
     */
    public void removeCardsOwned(Card card) {
    	cardsOwned.remove(card);
    }

    /**
     * @param property that will be purchased from Player
     */
    public void buyProperty(Property property) {
    	if ( balance >= property.getAmount() ){
    		balance -= property.getAmount();
    		propertiesOwned.add(property);
    		property.setSold(true);
    		property.setOwner(this);
    		
    	}
    }

    /**
     * @param property to be sold
     * @param amount to be sold
     */
    public void sellProperty(Property property, int amount) {
    	if ( propertiesOwned.contains(property) ){
    		balance += amount;
    		propertiesOwned.remove(property);
    		property.setSold(false);
    		property.setOwner(null);
    	}
    }

    /**
     * @param property to be mortgage
     */
    public void mortgageProperty(Property property) {
    	if ( propertiesOwned.contains(property) ){
    		balance += property.getMortgageValue();
    		property.setMortgage(true);
    	}
    }

    /**
     * @param property to be "unmortgage"
     */
    public void unMortgageProperty(Property property) {
    	if ( propertiesOwned.contains(property) ){
    		balance -= property.getMortgageValueBack();
    		property.setMortgage(false);
    	}
    }
    
    /**
     * Get all properties owned
     * @return properties owned
     */
    public Vector<Property> getPropertiesOwned(){
    	return propertiesOwned;
    }
    
    /**
     * Number of roll dices tries by user
     * @return Number of roll dices tries by user.
     */
    public int getNrOfRolls() {
		return nrOfRolls;
	}
    
    /**
     * Update the number of tries that user use to roll the dice
     * @param numberTries Number of tries.
     */
	public void setNrOfRolls(Boolean bool) {
		if(bool){
			this.nrOfRolls++;
		}else
			this.nrOfRolls = 0;
	}

	/**
     * Get number of roll dices tries by user in jail
     * @return Number of roll dices tries by user.
     */
	public int getNrOfRollsInJail() {
		return nrOfRollsInJail;
	}

	/**
     * Update the number of tries that user use to roll the dice in jail
     * @param numberTries Number of tries.
     */
	public void setNrOfRollsInJail(Boolean bool) {
		if(!bool){
			this.nrOfRollsInJail++;
		}else
			this.nrOfRollsInJail = 0;
	}
	
	
	
}