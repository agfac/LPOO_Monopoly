package Monopoly.Logic;

import java.util.*;

/**
 * 
 */
public class BoardBox {
    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private int pos ;

    /**
     * 
     */
    public Vector<Player> playersIn;



    /**
     * Default constructor
     */
    public BoardBox() {
    }
    
    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @return
     */
    public int getPos() {
        return pos;
    }

    /**
     * @param player 
     * @return
     */
    public void addPlayer(Player player) {
    	playersIn.add(player);
    }

    /**
     * @param player 
     * @return
     */
    public void removePlayer(Player player) {
        playersIn.remove(player);
    }

    /**
     * @return
     */
    public Vector<Player> getPlayersIn() {
        return playersIn;
    }

    /**
     * @param player 
     * @return
     */
    public void behaviorWithPlayer(Player player) {
        // TODO implement here
    }

}