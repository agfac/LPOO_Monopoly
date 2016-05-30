package Monopoly.Logic;

import java.util.*;

/**
 * BoardBox Class
 */
public class BoardBox {

    protected String name;
    protected int pos ;
    protected Vector<Player> playersIn;

    /**
     * Default constructor of BoardBox
     */
    public BoardBox() {
    }
    
    public BoardBox(String name, int pos) {
    	this.name = name;
    	this.pos = pos;
    }
    
    /**
     * @return Name of BoardBox
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return Position of the BoardBox
     */
    public int getPos() {
        return this.pos;
    }

    /**
     * @param player to be add to BoardBox
     */
    public void addPlayer(Player player) {
    	playersIn.add(player);
    }

    /**
     * @param player to be removed from BoardBox
     */
    public void removePlayer(Player player) {
        playersIn.remove(player);
    }

    /**
     * @return players in BoardBox
     */
    public Vector<Player> getPlayersIn() {
        return playersIn;
    }

    /**
     * @param player behavior with BoardBox
     */
    public void behaviorWithPlayer(Player player) {
        // TODO implement here
    }

}