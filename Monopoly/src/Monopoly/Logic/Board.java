package Monopoly.Logic;

import java.util.*;

/**
 * Board Class
 */
public class Board {
	
	public Vector<Player> players;
    public Vector<Deck> deck;
    private Vector<GroupProperty> groupProperty = new Vector<GroupProperty>();
    public BoardBox[] boxs;
    public Vector<Card> chanceCards = new Vector<Card>();
    public Vector<Card> communityCards = new Vector<Card>();
    
    /**
     * Board Constructor
     */
    public Board() {
       	
    	boxs = new BoardBox[40];
    	
    	GoBox Go = new GoBox();
    	boxs[0] = Go;
    	NormalProperty MediterraneanAvenue = new NormalProperty(1,"Mediterranean Avenue", 1, 60, 30, 2, new int[]{10,30,90,160}, 250);
    	boxs[1] = MediterraneanAvenue;
    	CommunityBox CommunityBox1 = new CommunityBox(2);
    	boxs[2] = CommunityBox1;
    	NormalProperty BalticAvenue = new NormalProperty(3,"Baltic Avenue", 1, 60, 30, 4, new int[]{20,60,180,320}, 450);
    	boxs[3] = BalticAvenue;
    	TaxBox IncomeTax = new TaxBox(4,"Income Tax",200);
    	boxs[4] = IncomeTax;
    	RailRoadProperty ReadingRailRoad = new RailRoadProperty(5, "Reading RailRoad");
    	boxs[5] = ReadingRailRoad;
    	NormalProperty OrientalAvenue = new NormalProperty(6, "Oriental Avenue", 3, 100, 50, 6, new int[]{30,90,270,400}, 550);
    	boxs[6] = OrientalAvenue;
    	ChanceBox ChanceBox1 = new ChanceBox(7);
    	boxs[7] = ChanceBox1;
    	NormalProperty VermontAvenue = new NormalProperty(8, "Vermont Avenue", 3, 100, 50, 6, new int[]{30,90,270,400}, 550);
    	boxs[8] = VermontAvenue;
    	NormalProperty ConnecticutAvenue = new NormalProperty(9, "Connecticut Avenue", 3, 120, 60, 8, new int[]{40,100,300,450}, 600);
    	boxs[9] = ConnecticutAvenue;
    	JailBox JailBox1 = new JailBox();
    	boxs[10] = JailBox1;
    	NormalProperty STCharlesPlace = new NormalProperty(11, "ST Charles Place", 4, 140, 70, 10, new int[]{50,150,450,625}, 750);
    	boxs[11] = STCharlesPlace;
    	ServiceProperty ElectricCompany = new ServiceProperty(12, "Electric Company", 10, 150, 75, new int[]{4,10});
    	boxs[12] = ElectricCompany;
    	NormalProperty StatesAvenue = new NormalProperty(13, "States Avenue", 4, 140, 70, 10, new int[]{50,150,450,625}, 750);
    	boxs[13] = StatesAvenue;
    	NormalProperty VirginiaAvenue = new NormalProperty(14, "Virginia Avenue", 4, 160, 80, 12, new int[]{60,180,500,700}, 900);
    	boxs[14] = VirginiaAvenue;
    	RailRoadProperty PennsylvaniaRailRoad = new RailRoadProperty(15, "Pennsylvania RailRoad");
    	boxs[15] = PennsylvaniaRailRoad;
    	NormalProperty STJamesPlace = new NormalProperty(16, "ST James Place", 5, 180, 90, 14, new int[]{70,200,550,750}, 950);
    	boxs[16] = STJamesPlace;
    	CommunityBox CommunityBox2 = new CommunityBox(17);
    	boxs[17] = CommunityBox2;
    	NormalProperty TennesseAvenue = new NormalProperty(18, "Tennesse Avenue", 5, 180, 90, 14, new int[]{70,200,550,750}, 950);
    	boxs[18] = TennesseAvenue;
    	NormalProperty NewYorkAvenue = new NormalProperty(19, "New York Avenue", 5, 200, 100, 16, new int[]{80,220,600,800}, 1000);
    	boxs[19] = NewYorkAvenue;
    	FreeParkingBox FreeParking = new FreeParkingBox();
    	boxs[20] = FreeParking;
    	NormalProperty KentuckyAvenue = new NormalProperty(21, "Kentucky Avenue", 6, 220, 110, 18, new int[]{90,250,700,875}, 1050);
    	boxs[21] = KentuckyAvenue;
    	ChanceBox ChanceBox2 = new ChanceBox(22);
    	boxs[22] = ChanceBox2;
    	NormalProperty IndianaAvenue = new NormalProperty(23, "Indiana Avenue", 6, 220, 110, 18, new int[]{90,250,700,875}, 1050);
    	boxs[23] = IndianaAvenue;
    	NormalProperty IllinoisAvenue = new NormalProperty(24, "Illinois Avenue", 6, 240, 120, 20, new int[]{100,300,750,925}, 1100);
    	boxs[24] = IllinoisAvenue;
    	RailRoadProperty BORailRoad = new RailRoadProperty(25, "B&O RailRoad");
    	boxs[25] = BORailRoad;
    	NormalProperty AtlanticAvenue = new NormalProperty(26, "Atlantic Avenue", 7, 260, 130, 22, new int[]{110,330,800,975}, 1150);
    	boxs[26] = AtlanticAvenue;
    	NormalProperty VentnorAvenue = new NormalProperty(27, "Ventnor Avenue", 7, 260, 130, 22, new int[]{110,330,800,975}, 1150);
    	boxs[27] = VentnorAvenue;
    	ServiceProperty WaterWorks = new ServiceProperty(28, "Water Works", 10, 150, 75, new int[]{4,10});
    	boxs[28] = WaterWorks;
    	NormalProperty MarvinGardens = new NormalProperty(29, "Marvin Gardens", 7, 280, 140, 24, new int[]{120,360,850,1025}, 1200);
    	boxs[29] = MarvinGardens;
    	GoJailBox GoJail = new GoJailBox();
    	boxs[30] = GoJail;
    	NormalProperty PacificAvenue = new NormalProperty(31, "Pacific Avenue", 8, 300, 150, 26, new int[]{130,390,900,1100}, 1275);
    	boxs[31] = PacificAvenue;
    	NormalProperty NorthCarolinaAvenue = new NormalProperty(32, "North Carolina Avenue", 8, 300, 150, 26, new int[]{130,390,900,1100}, 1275);
    	boxs[32] = NorthCarolinaAvenue;
    	CommunityBox CommunityBox3 = new CommunityBox(33);
    	boxs[33] = CommunityBox3;
    	NormalProperty PennsylvaniaAvenue = new NormalProperty(34, "Pennsylvania Avenue", 8, 320, 160, 28, new int[]{150,450,1000,1200}, 1400);
    	boxs[34] = PennsylvaniaAvenue;
    	RailRoadProperty ShortLineRailRoad = new RailRoadProperty(35, "Short Line RailRoad");
    	boxs[35] = ShortLineRailRoad;
    	ChanceBox ChanceBox3 = new ChanceBox(36);
    	boxs[36] = ChanceBox3;
    	NormalProperty ParkPlace = new NormalProperty(37, "Park Place", 9, 350, 175, 35, new int[]{175,500,1100,1300}, 1500);
    	boxs[37] = ParkPlace;
    	TaxBox LuxuryTax = new TaxBox(38,"Luxury Tax",100);
    	boxs[38] = LuxuryTax;
    	NormalProperty BoardWalk = new NormalProperty(39, "Board Walk", 9, 400, 200, 50, new int[]{200,600,1400,1700}, 2000);
    	boxs[39] = BoardWalk;
    	
    	
    	groupProperty.add(new GroupProperty(1,"Brown",2));
    	groupProperty.add(new GroupProperty(2,"Rail",4));
    	groupProperty.add(new GroupProperty(3,"Cyan",3));
    	groupProperty.add(new GroupProperty(4,"Pink",3));
    	groupProperty.add(new GroupProperty(5,"Orange",3));
    	groupProperty.add(new GroupProperty(6,"Red",3));
    	groupProperty.add(new GroupProperty(7,"Yellow",3));
    	groupProperty.add(new GroupProperty(8,"Green",3));
    	groupProperty.add(new GroupProperty(9,"Blue",3));
    	groupProperty.add(new GroupProperty(10,"Company",2));
    	
    	
    	chanceCards.add(new Card("Advance Go",1));
    	chanceCards.add(new Card("Advance Illinois Avenue",2));
    	chanceCards.add(new Card("Advance ST Charles Place",3));
    	chanceCards.add(new Card("Advance nearest RailRoad",4));
    	chanceCards.add(new Card("Advance nearest Utility",5));
    	chanceCards.add(new Card("Bank pay 50",6));
    	chanceCards.add(new Card("Out jail",7));
    	chanceCards.add(new Card("Back 3 spaces",8));
    	chanceCards.add(new Card("Go jail",9));
    	chanceCards.add(new Card("General Repairs",10));
    	chanceCards.add(new Card("Speeding fine",11));
    	chanceCards.add(new Card("Advance Reading RailRoad",12));
    	chanceCards.add(new Card("Advance Board Walk",13));
    	chanceCards.add(new Card("Receive 150",14));
    	chanceCards.add(new Card("Pay each player 50",15));
    	
    	communityCards.add(new Card("Advance Go",1));
    	communityCards.add(new Card("Receive 200",2));
    	communityCards.add(new Card("Pay 50",3));
    	communityCards.add(new Card("Receive 50",4));
    	communityCards.add(new Card("Out Jail",5));
    	communityCards.add(new Card("Go Jail",6));
    	communityCards.add(new Card("Receive 20",7));
    	communityCards.add(new Card("Receive 10 each player",8));
    	communityCards.add(new Card("Receive 100",9));
    	communityCards.add(new Card("Pay 100",10));
    	communityCards.add(new Card("Pay 50",11));
    	communityCards.add(new Card("Receive 25",12));
    	communityCards.add(new Card("Street repairs",13));
    	communityCards.add(new Card("Receive 10",14));
    	communityCards.add(new Card("Receive 100",15));
    	communityCards.add(new Card("Receive 100",16));
    }

    public int getMaxPropertiesPerGroup(int index){
    	for(GroupProperty gp: groupProperty){
    		if(gp.getId() == index)
    			return gp.getTotalProperty();
    	}
    	return 0;
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

	public BoardBox[] getBoxs() {
		return boxs;
	}
	
	public BoardBox getBoardBox(int pos){
		return boxs[pos];
	}
	
	public Card getCard(int val){
		return communityCards.get(val-1);
	}
}