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
    	//NormalProperty MediterraneanAvenue = new NormalProperty(1,"Mediterranean Avenue", 1, 60, 30, 2, new int[]{10,30,90,160}, 250);
    	CommunityBox CommunityBox1 = new CommunityBox(2);
    	NormalProperty BalticAvenue = new NormalProperty(3,"Baltic Avenue", 1, 60, 30, 4, new int[]{20,60,180,320}, 450);
    	TaxBox IncomeTax = new TaxBox(4,"Income Tax",200);
    	RailRoadProperty ReadingRailRoad = new RailRoadProperty(5, "Reading RailRoad");
    	NormalProperty OrientalAvenue = new NormalProperty(6, "Oriental Avenue", 3, 100, 50, 6, new int[]{30,90,270,400}, 550);
    	ChanceBox ChanceBox1 = new ChanceBox(7);
    	NormalProperty VermontAvenue = new NormalProperty(8, "Vermont Avenue", 3, 100, 50, 6, new int[]{30,90,270,400}, 550);
    	NormalProperty ConnecticutAvenue = new NormalProperty(9, "Connecticut Avenue", 3, 120, 60, 8, new int[]{40,100,300,450}, 600);
    	JailBox JailBox1 = new JailBox();
    	NormalProperty STCharlesPlace = new NormalProperty(11, "ST Charles Place", 4, 140, 70, 10, new int[]{50,150,450,625}, 750);
    	ServiceProperty ElectricCompany = new ServiceProperty(12, "Electric Company", 150);
    	NormalProperty StatesAvenue = new NormalProperty(13, "States Avenue", 4, 140, 70, 10, new int[]{50,150,450,625}, 750);
    	NormalProperty VirginiaAvenue = new NormalProperty(14, "Virginia Avenue", 4, 160, 80, 12, new int[]{60,180,500,700}, 900);
    	RailRoadProperty PennsylvaniaRailRoad = new RailRoadProperty(15, "Pennsylvania RailRoad");
    	NormalProperty STJamesPlace = new NormalProperty(16, "ST James Place", 5, 180, 90, 14, new int[]{70,200,550,750}, 950);
    	CommunityBox CommunityBox2 = new CommunityBox(17);
    	NormalProperty TennesseAvenue = new NormalProperty(18, "Tennesse Avenue", 5, 180, 90, 14, new int[]{70,200,550,750}, 950);
    	NormalProperty NewYorkAvenue = new NormalProperty(19, "New York Avenue", 5, 200, 100, 16, new int[]{80,220,600,800}, 1000);
    	FreeParkingBox FreeParking = new FreeParkingBox();
    	NormalProperty KentuckyAvenue = new NormalProperty(21, "Kentucky Avenue", 6, 220, 110, 18, new int[]{90,250,700,875}, 1050);
    	ChanceBox ChanceBox2 = new ChanceBox(22);
    	NormalProperty IndianaAvenue = new NormalProperty(23, "Indiana Avenue", 6, 220, 110, 18, new int[]{90,250,700,875}, 1050);
    	NormalProperty IllinoisAvenue = new NormalProperty(24, "Illinois Avenue", 6, 240, 120, 20, new int[]{100,300,750,925}, 1100);
    	RailRoadProperty BORailRoad = new RailRoadProperty(25, "B&O RailRoad");
    	NormalProperty AtlanticAvenue = new NormalProperty(26, "Atlantic Avenue", 7, 240, 120, 20, new int[]{100,300,750,925}, 1100);


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