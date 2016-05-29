package Monopoly.Logic;

import java.util.*;

/**
 * Board Class
 */
public class Board {
	
	public Vector<Player> players;
    public Vector<Deck> deck;
    public BoardBox[] boxs;
    
    /**
     * Board Constructor
     */
    public Board() {
    	
    	GoBox Go = new GoBox();
    	NormalProperty MediterraneanAvenue = new NormalProperty(1,"MediterraneanAvenue", 1, 60, 30, 2, new int[]{10,30,90,160}, 250);
    	CommunityBox CommunityBox1 = new CommunityBox(2);
    	NormalProperty BalticAvenue = new NormalProperty(3,"BalticAvenue", 1, 60, 30, 4, new int[]{20,60,180,320}, 450);
    	TaxBox IncomeTax = new TaxBox(4,"Income Tax",200);
    	RailRoadProperty ReadingRailRoad = new RailRoadProperty(5, "ReadingRailRoad");
    	NormalProperty OrientalAvenue = new NormalProperty(6, "OrientalAvenue", 3, 100, 50, 6, new int[]{30,90,270,400}, 550);
    	NormalProperty VermontAvenue = new NormalProperty(7, "VermontAvenue", 3, 100, 50, 6, new int[]{30,90,270,400}, 550);
    	NormalProperty ConnecticutAvenue = new NormalProperty(8, "ConnecticutAvenue", 3, 120, 60, 8, new int[]{40,100,300,450}, 600);
    }

    /**
     * @param players
     */
    public void Board(Vector<Player> players) {
    	this.players = players;
    	deck.add(new Deck());
    	deck.add(new Deck());
    	
    	
    	// ciclo para criar tds as propiendades e guardar no -> boxs
    }

}