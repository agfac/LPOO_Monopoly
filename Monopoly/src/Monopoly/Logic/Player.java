package Monopoly.Logic;

import java.util.*;

/**
 * 
 */
public class Player {

    /**
     * 
     */
    private BoardBox pos;

    /**
     * 
     */
    private int id;

    /**
     * 
     */
    private String name;
    
    /**
     * 
     */
    private int balance;

    /**
     * 
     */
    private PlayerSymbol symbol;

    /**
     * 
     */
    private boolean inJail;

    /**
     * 
     */
    private Vector<Card> cardsOwned;

    /**
     * 
     */
    private Vector<Property> propertiesOwned;

    /**
     * Default constructor
     */
    public Player() {
    }
    
    /**
     * @param name 
     * @param piece 
     * @param balance
     */
    public void Player(String name, PlayerSymbol piece, int balance) {
    	//pos ira receber a posição inicial
        this.name = name;
        this.symbol = piece;
        this.balance = balance;
        this.inJail = false;
        this.cardsOwned = new Vector<Card>();
        this.propertiesOwned = new Vector<Property>();
    }

    /**
     * @return
     */
    public int getId() {
        return 0;
    }

    /**
     * @return
     */
    public String getName() {
        return null;
    }

    /**
     * @param name 
     * @return
     */
    public void setName(String name) {
    	this.name = name;
    }

    /**
     * @return
     */
    public int getBalance() {
        return balance;
    }

    /**
     * @param balance 
     * @return
     */
    public void setBalance(int balance) {
    	this.balance = balance;
    }

    /**
     * @return
     */
    public PlayerSymbol getSymbol() {
        return symbol;
    }

    /**
     * @param symbol 
     * @return
     */
    public void setSymbol(PlayerSymbol symbol) {
    	this.symbol = symbol;
    }

    /**
     * @return
     */
    public BoardBox getPos() {
        return pos;
    }

    /**
     * @param pos 
     * @return
     */
    public void setPos(BoardBox pos) {
        this.pos = pos;
    }

    /**
     * @return
     */
    public boolean getInJain() {
        return inJail;
    }

    /**
     * @param inJail 
     * @return
     */
    public void setInJail(boolean inJail) {
    	this.inJail = inJail;
    }

    /**
     * @return
     */
    public Vector<Card> getCardsOwned() {
        return cardsOwned;
    }

    /**
     * @param card 
     * @return
     */
    public void addCardsOwned(Card card) {
    	cardsOwned.addElement(card);
    		
    }
    
    /**
     * @param card 
     * @return
     */
    public void removeCardsOwned(Card card) {
    	cardsOwned.remove(card);
    }

    /**
     * @return
     */
    public void buyProperty(Property property) {
    	if ( balance >= property.getAmount() ){
    		balance -= property.getAmount();
    		propertiesOwned.add(property);
    	}
    }

    /**
     * @param property 
     * @return
     */
    public void sellProperty(Property property, int amount) {
    	if ( propertiesOwned.contains(property) ){
    		balance += amount;
    		propertiesOwned.remove(property);
    	}
    }

    /**
     * @param property 
     * @return
     */
    public void mortgageProperty(Property property) {
    	if ( propertiesOwned.contains(property) ){
    		balance += property.getMortgageValue();
    		
    		property.setMortgage(true);
    		
//    		int index = propertiesOwned.indexOf(property);
//    		propertiesOwned.get(index).setMortgage(true);
    	}
    }

    /**
     * @param property 
     * @return
     */
    public void unMortgageProperty(Property property) {
    	if ( propertiesOwned.contains(property) ){
    		balance -= property.getMortgageValueBack();
    		property.setMortgage(false);
    	}
    }

}