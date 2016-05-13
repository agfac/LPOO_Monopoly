
import java.util.*;

/**
 * 
 */
public class Player {

    /**
     * Default constructor
     */
    public Player() {
    }

    /**
     * 
     */
    public BoardBox pos;

    /**
     * 
     */
    public int id;

    /**
     * 
     */
    public String name;

    /**
     * 
     */
    public int balance;

    /**
     * 
     */
    public PlayerSymbol symbol;

    /**
     * 
     */
    public boolean inJail;

    /**
     * 
     */
    public Vector<Card> cardsOwned;

    /**
     * 
     */
    public Vector<Property> propertiesOwned;




    /**
     * @param name 
     * @param piece 
     * @param balance
     */
    public void Player(String name, PlayerSymbol piece, int balance) {
        // TODO implement here
    }

    /**
     * @return
     */
    public int getId() {
        // TODO implement here
        return 0;
    }

    /**
     * @return
     */
    public String getName() {
        // TODO implement here
        return null;
    }

    /**
     * @param name 
     * @return
     */
    public void setName(String name) {
        // TODO implement here
    }

    /**
     * @return
     */
    public int getBalance() {
        // TODO implement here
        return 0;
    }

    /**
     * @param balance 
     * @return
     */
    public void setBalance(int balance) {
        // TODO implement here
    }

    /**
     * @return
     */
    public PlayerSymbol getSymbol() {
        // TODO implement here
        return null;
    }

    /**
     * @param symbol 
     * @return
     */
    public void setSymbol(PlayerSymbol symbol) {
        // TODO implement here
    }

    /**
     * @return
     */
    public BoardBox getPos() {
        // TODO implement here
        return null;
    }

    /**
     * @param pos 
     * @return
     */
    public void setPos(BoardBox pos) {
        // TODO implement here
    }

    /**
     * @return
     */
    public boolean getInJain() {
        // TODO implement here
        return false;
    }

    /**
     * @param inJail 
     * @return
     */
    public void setInJail(boolean inJail) {
        // TODO implement here
    }

    /**
     * @return
     */
    public Vector<Card> getCardsOwned() {
        // TODO implement here
        return null;
    }

    /**
     * @param card 
     * @return
     */
    public void setCardsOwned(Card card) {
        // TODO implement here
    }

    /**
     * @return
     */
    public void buyProperty() {
        // TODO implement here
    }

    /**
     * @param property 
     * @return
     */
    public void sellProperty(Property property) {
        // TODO implement here
    }

    /**
     * @param property 
     * @return
     */
    public void mortgageProperty(Property property) {
        // TODO implement here
    }

    /**
     * @param property 
     * @return
     */
    public void unMortgageProperty(Property property) {
        // TODO implement here
    }

}