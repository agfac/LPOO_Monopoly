package Monopoly.Logic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

/**
 * Board Class
 */
public class Board {

	private Vector<Player> players;
	private Vector<GroupProperty> groupProperty = new Vector<GroupProperty>();
	private BoardBox[] boxs;
	private Vector<Card> chanceCards = new Vector<Card>();
	private Vector<Card> communityCards = new Vector<Card>();

	// SmallProperties
	protected static BufferedImage g1p1, g1p3, g2p5, g2p15, g2p25, g2p35, g3p6, g3p8, g3p9, g4p11, g4p13, g4p14, g5p16,
			g5p18, g5p19, g6p21, g6p23, g6p24, g7p26, g7p27, g7p29, g8p31, g8p32, g8p34, g9p37, g9p39, g10p12, g10p28;

	// NormalProperties
	protected static BufferedImage g1p1RP, g1p3RP, g2p5RP, g2p15RP, g2p25RP, g2p35RP, g3p6RP, g3p8RP, g3p9RP, g4p11RP,
			g4p13RP, g4p14RP, g5p16RP, g5p18RP, g5p19RP, g6p21RP, g6p23RP, g6p24RP, g7p26RP, g7p27RP, g7p29RP, g8p31RP,
			g8p32RP, g8p34RP, g9p37RP, g9p39RP, g10p12RP, g10p28RP;

	// Chance Cards
	protected static BufferedImage chance1, chance2, chance3, chance4, chance5, chance6, chance7, chance8, chance9,
			chance10, chance11, chance12, chance13, chance14, chance15;

	// Community Cards
	protected static BufferedImage community1, community2, community3, community4, community5, community6, community7,
			community8, community9, community10, community11, community12, community13, community14, community15,
			community16;

	/**
	 * Board Constructor
	 */
	public Board() {

		imageLoader();

		generateBoardBoxs();

		generateGroupProperty();

		generateChance();

		generateCommunity();
	}

	/**
	 * Method to load the necessary images
	 */
	private void imageLoader() {
		try {
			g1p1 = ImageIO.read(new File("resources/images/propertiesOwned/group1/MediterraneanAvenue.png"));
			g1p3 = ImageIO.read(new File("resources/images/propertiesOwned/group1/BalticAvenue.png"));
			g2p5 = ImageIO.read(new File("resources/images/propertiesOwned/group2/ReadingRailRoad.png"));
			g2p15 = ImageIO.read(new File("resources/images/propertiesOwned/group2/PennsylvaniaRailRoad.png"));
			g2p25 = ImageIO.read(new File("resources/images/propertiesOwned/group2/BORailRoad.png"));
			g2p35 = ImageIO.read(new File("resources/images/propertiesOwned/group2/ShortLineRailRoad.png"));
			g3p6 = ImageIO.read(new File("resources/images/propertiesOwned/group3/OrientalAvenue.png"));
			g3p8 = ImageIO.read(new File("resources/images/propertiesOwned/group3/VermontAvenue.png"));
			g3p9 = ImageIO.read(new File("resources/images/propertiesOwned/group3/ConnecticutAvenue.png"));
			g4p11 = ImageIO.read(new File("resources/images/propertiesOwned/group4/STCharlesPlace.png"));
			g4p13 = ImageIO.read(new File("resources/images/propertiesOwned/group4/StatesAvenue.png"));
			g4p14 = ImageIO.read(new File("resources/images/propertiesOwned/group4/VirginiaAvenue.png"));
			g5p16 = ImageIO.read(new File("resources/images/propertiesOwned/group5/STJamesPlace.png"));
			g5p18 = ImageIO.read(new File("resources/images/propertiesOwned/group5/TennesseAvenue.png"));
			g5p19 = ImageIO.read(new File("resources/images/propertiesOwned/group5/NewYorkAvenue.png"));
			g6p21 = ImageIO.read(new File("resources/images/propertiesOwned/group6/KentuckyAvenue.png"));
			g6p23 = ImageIO.read(new File("resources/images/propertiesOwned/group6/IndianaAvenue.png"));
			g6p24 = ImageIO.read(new File("resources/images/propertiesOwned/group6/IllinoisAvenue.png"));
			g7p26 = ImageIO.read(new File("resources/images/propertiesOwned/group7/AtlanticAvenue.png"));
			g7p27 = ImageIO.read(new File("resources/images/propertiesOwned/group7/VentnorAvenue.png"));
			g7p29 = ImageIO.read(new File("resources/images/propertiesOwned/group7/MarvinGardens.png"));
			g8p31 = ImageIO.read(new File("resources/images/propertiesOwned/group8/PacificAvenue.png"));
			g8p32 = ImageIO.read(new File("resources/images/propertiesOwned/group8/NorthCarolinaAvenue.png"));
			g8p34 = ImageIO.read(new File("resources/images/propertiesOwned/group8/PennsylvaniaAvenue.png"));
			g9p37 = ImageIO.read(new File("resources/images/propertiesOwned/group9/ParkPlace.png"));
			g9p39 = ImageIO.read(new File("resources/images/propertiesOwned/group9/BoardWalk.png"));
			g10p12 = ImageIO.read(new File("resources/images/propertiesOwned/group10/ElectricCompany.png"));
			g10p28 = ImageIO.read(new File("resources/images/propertiesOwned/group10/WaterWorks.png"));

			// Normal Properties
			g1p1RP = ImageIO.read(new File("resources/images/properties/group1/MediterraneanAvenue.png"));
			g1p3RP = ImageIO.read(new File("resources/images/properties/group1/BalticAvenue.png"));
			g2p5RP = ImageIO.read(new File("resources/images/properties/group2/ReadingRailRoad.png"));
			g2p15RP = ImageIO.read(new File("resources/images/properties/group2/PennsylvaniaRailRoad.png"));
			g2p25RP = ImageIO.read(new File("resources/images/properties/group2/BORailRoad.png"));
			g2p35RP = ImageIO.read(new File("resources/images/properties/group2/ShortLineRailRoad.png"));
			g3p6RP = ImageIO.read(new File("resources/images/properties/group3/OrientalAvenue.png"));
			g3p8RP = ImageIO.read(new File("resources/images/properties/group3/VermontAvenue.png"));
			g3p9RP = ImageIO.read(new File("resources/images/properties/group3/ConnecticutAvenue.png"));
			g4p11RP = ImageIO.read(new File("resources/images/properties/group4/STCharlesPlace.png"));
			g4p13RP = ImageIO.read(new File("resources/images/properties/group4/StatesAvenue.png"));
			g4p14RP = ImageIO.read(new File("resources/images/properties/group4/VirginiaAvenue.png"));
			g5p16RP = ImageIO.read(new File("resources/images/properties/group5/STJamesPlace.png"));
			g5p18RP = ImageIO.read(new File("resources/images/properties/group5/TennesseAvenue.png"));
			g5p19RP = ImageIO.read(new File("resources/images/properties/group5/NewYorkAvenue.png"));
			g6p21RP = ImageIO.read(new File("resources/images/properties/group6/KentuckyAvenue.png"));
			g6p23RP = ImageIO.read(new File("resources/images/properties/group6/IndianaAvenue.png"));
			g6p24RP = ImageIO.read(new File("resources/images/properties/group6/IllinoisAvenue.png"));
			g7p26RP = ImageIO.read(new File("resources/images/properties/group7/AtlanticAvenue.png"));
			g7p27RP = ImageIO.read(new File("resources/images/properties/group7/VentnorAvenue.png"));
			g7p29RP = ImageIO.read(new File("resources/images/properties/group7/MarvinGardens.png"));
			g8p31RP = ImageIO.read(new File("resources/images/properties/group8/PacificAvenue.png"));
			g8p32RP = ImageIO.read(new File("resources/images/properties/group8/NorthCarolinaAvenue.png"));
			g8p34RP = ImageIO.read(new File("resources/images/properties/group8/PennsylvaniaAvenue.png"));
			g9p37RP = ImageIO.read(new File("resources/images/properties/group9/ParkPlace.png"));
			g9p39RP = ImageIO.read(new File("resources/images/properties/group9/BoardWalk.png"));
			g10p12RP = ImageIO.read(new File("resources/images/properties/group10/ElectricCompany.png"));
			g10p28RP = ImageIO.read(new File("resources/images/properties/group10/WaterWorks.png"));

			// Chance cards
			chance1 = ImageIO.read(new File("resources/images/cards/chance/1.jpg"));
			chance2 = ImageIO.read(new File("resources/images/cards/chance/2.jpg"));
			chance3 = ImageIO.read(new File("resources/images/cards/chance/3.jpg"));
			chance4 = ImageIO.read(new File("resources/images/cards/chance/4.jpg"));
			chance5 = ImageIO.read(new File("resources/images/cards/chance/5.jpg"));
			chance6 = ImageIO.read(new File("resources/images/cards/chance/6.jpg"));
			chance7 = ImageIO.read(new File("resources/images/cards/chance/7.jpg"));
			chance8 = ImageIO.read(new File("resources/images/cards/chance/8.jpg"));
			chance9 = ImageIO.read(new File("resources/images/cards/chance/9.jpg"));
			chance10 = ImageIO.read(new File("resources/images/cards/chance/10.jpg"));
			chance11 = ImageIO.read(new File("resources/images/cards/chance/11.jpg"));
			chance12 = ImageIO.read(new File("resources/images/cards/chance/12.jpg"));
			chance13 = ImageIO.read(new File("resources/images/cards/chance/13.jpg"));
			chance14 = ImageIO.read(new File("resources/images/cards/chance/14.jpg"));
			chance15 = ImageIO.read(new File("resources/images/cards/chance/15.jpg"));

			// Chance cards
			community1 = ImageIO.read(new File("resources/images/cards/community/1.jpg"));
			community2 = ImageIO.read(new File("resources/images/cards/community/2.jpg"));
			community3 = ImageIO.read(new File("resources/images/cards/community/3.jpg"));
			community4 = ImageIO.read(new File("resources/images/cards/community/4.jpg"));
			community5 = ImageIO.read(new File("resources/images/cards/community/5.jpg"));
			community6 = ImageIO.read(new File("resources/images/cards/community/6.jpg"));
			community7 = ImageIO.read(new File("resources/images/cards/community/7.jpg"));
			community8 = ImageIO.read(new File("resources/images/cards/community/8.jpg"));
			community9 = ImageIO.read(new File("resources/images/cards/community/9.jpg"));
			community10 = ImageIO.read(new File("resources/images/cards/community/10.jpg"));
			community11 = ImageIO.read(new File("resources/images/cards/community/11.jpg"));
			community12 = ImageIO.read(new File("resources/images/cards/community/12.jpg"));
			community13 = ImageIO.read(new File("resources/images/cards/community/13.jpg"));
			community14 = ImageIO.read(new File("resources/images/cards/community/14.jpg"));
			community15 = ImageIO.read(new File("resources/images/cards/community/15.jpg"));
			community16 = ImageIO.read(new File("resources/images/cards/community/15.jpg"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Generate and add Board Boxs
	 */
	private void generateBoardBoxs() {
		boxs = new BoardBox[40];

		GoBox Go = new GoBox();
		boxs[0] = Go;
		NormalProperty MediterraneanAvenue = new NormalProperty(1, "Mediterranean Avenue", 1, 60, 30, 2,
				new int[] { 10, 30, 90, 160 }, 250, g1p1, g1p1RP);
		boxs[1] = MediterraneanAvenue;
		CommunityBox CommunityBox1 = new CommunityBox(2);
		boxs[2] = CommunityBox1;
		NormalProperty BalticAvenue = new NormalProperty(3, "Baltic Avenue", 1, 60, 30, 4,
				new int[] { 20, 60, 180, 320 }, 450, g1p3, g1p3RP);
		boxs[3] = BalticAvenue;
		TaxBox IncomeTax = new TaxBox(4, "Income Tax", 200);
		boxs[4] = IncomeTax;
		RailRoadProperty ReadingRailRoad = new RailRoadProperty(5, "Reading RailRoad", g2p5, g2p5RP);
		boxs[5] = ReadingRailRoad;
		NormalProperty OrientalAvenue = new NormalProperty(6, "Oriental Avenue", 3, 100, 50, 6,
				new int[] { 30, 90, 270, 400 }, 550, g3p6, g3p6RP);
		boxs[6] = OrientalAvenue;
		ChanceBox ChanceBox1 = new ChanceBox(7);
		boxs[7] = ChanceBox1;
		NormalProperty VermontAvenue = new NormalProperty(8, "Vermont Avenue", 3, 100, 50, 6,
				new int[] { 30, 90, 270, 400 }, 550, g3p8, g3p8RP);
		boxs[8] = VermontAvenue;
		NormalProperty ConnecticutAvenue = new NormalProperty(9, "Connecticut Avenue", 3, 120, 60, 8,
				new int[] { 40, 100, 300, 450 }, 600, g3p9, g3p9RP);
		boxs[9] = ConnecticutAvenue;
		JailBox JailBox1 = new JailBox();
		boxs[10] = JailBox1;
		NormalProperty STCharlesPlace = new NormalProperty(11, "ST Charles Place", 4, 140, 70, 10,
				new int[] { 50, 150, 450, 625 }, 750, g4p11, g4p11RP);
		boxs[11] = STCharlesPlace;
		ServiceProperty ElectricCompany = new ServiceProperty(12, "Electric Company", 10, 150, 75, new int[] { 4, 10 },
				g10p12, g10p12RP);
		boxs[12] = ElectricCompany;
		NormalProperty StatesAvenue = new NormalProperty(13, "States Avenue", 4, 140, 70, 10,
				new int[] { 50, 150, 450, 625 }, 750, g4p13, g4p13RP);
		boxs[13] = StatesAvenue;
		NormalProperty VirginiaAvenue = new NormalProperty(14, "Virginia Avenue", 4, 160, 80, 12,
				new int[] { 60, 180, 500, 700 }, 900, g4p14, g4p14RP);
		boxs[14] = VirginiaAvenue;
		RailRoadProperty PennsylvaniaRailRoad = new RailRoadProperty(15, "Pennsylvania RailRoad", g2p15, g2p15RP);
		boxs[15] = PennsylvaniaRailRoad;
		NormalProperty STJamesPlace = new NormalProperty(16, "ST James Place", 5, 180, 90, 14,
				new int[] { 70, 200, 550, 750 }, 950, g5p16, g5p16RP);
		boxs[16] = STJamesPlace;
		CommunityBox CommunityBox2 = new CommunityBox(17);
		boxs[17] = CommunityBox2;
		NormalProperty TennesseAvenue = new NormalProperty(18, "Tennesse Avenue", 5, 180, 90, 14,
				new int[] { 70, 200, 550, 750 }, 950, g5p18, g5p18RP);
		boxs[18] = TennesseAvenue;
		NormalProperty NewYorkAvenue = new NormalProperty(19, "New York Avenue", 5, 200, 100, 16,
				new int[] { 80, 220, 600, 800 }, 1000, g5p19, g5p19RP);
		boxs[19] = NewYorkAvenue;
		FreeParkingBox FreeParking = new FreeParkingBox();
		boxs[20] = FreeParking;
		NormalProperty KentuckyAvenue = new NormalProperty(21, "Kentucky Avenue", 6, 220, 110, 18,
				new int[] { 90, 250, 700, 875 }, 1050, g6p21, g6p21RP);
		boxs[21] = KentuckyAvenue;
		ChanceBox ChanceBox2 = new ChanceBox(22);
		boxs[22] = ChanceBox2;
		NormalProperty IndianaAvenue = new NormalProperty(23, "Indiana Avenue", 6, 220, 110, 18,
				new int[] { 90, 250, 700, 875 }, 1050, g6p23, g6p23RP);
		boxs[23] = IndianaAvenue;
		NormalProperty IllinoisAvenue = new NormalProperty(24, "Illinois Avenue", 6, 240, 120, 20,
				new int[] { 100, 300, 750, 925 }, 1100, g6p24, g6p24RP);
		boxs[24] = IllinoisAvenue;
		RailRoadProperty BORailRoad = new RailRoadProperty(25, "B&O RailRoad", g2p25, g2p25RP);
		boxs[25] = BORailRoad;
		NormalProperty AtlanticAvenue = new NormalProperty(26, "Atlantic Avenue", 7, 260, 130, 22,
				new int[] { 110, 330, 800, 975 }, 1150, g7p26, g7p26RP);
		boxs[26] = AtlanticAvenue;
		NormalProperty VentnorAvenue = new NormalProperty(27, "Ventnor Avenue", 7, 260, 130, 22,
				new int[] { 110, 330, 800, 975 }, 1150, g7p27, g7p27RP);
		boxs[27] = VentnorAvenue;
		ServiceProperty WaterWorks = new ServiceProperty(28, "Water Works", 10, 150, 75, new int[] { 4, 10 }, g10p28,
				g10p28RP);
		boxs[28] = WaterWorks;
		NormalProperty MarvinGardens = new NormalProperty(29, "Marvin Gardens", 7, 280, 140, 24,
				new int[] { 120, 360, 850, 1025 }, 1200, g7p29, g7p29RP);
		boxs[29] = MarvinGardens;
		GoJailBox GoJail = new GoJailBox();
		boxs[30] = GoJail;
		NormalProperty PacificAvenue = new NormalProperty(31, "Pacific Avenue", 8, 300, 150, 26,
				new int[] { 130, 390, 900, 1100 }, 1275, g8p31, g8p31RP);
		boxs[31] = PacificAvenue;
		NormalProperty NorthCarolinaAvenue = new NormalProperty(32, "North Carolina Avenue", 8, 300, 150, 26,
				new int[] { 130, 390, 900, 1100 }, 1275, g8p32, g8p32RP);
		boxs[32] = NorthCarolinaAvenue;
		CommunityBox CommunityBox3 = new CommunityBox(33);
		boxs[33] = CommunityBox3;
		NormalProperty PennsylvaniaAvenue = new NormalProperty(34, "Pennsylvania Avenue", 8, 320, 160, 28,
				new int[] { 150, 450, 1000, 1200 }, 1400, g8p34, g8p34RP);
		boxs[34] = PennsylvaniaAvenue;
		RailRoadProperty ShortLineRailRoad = new RailRoadProperty(35, "Short Line RailRoad", g2p35, g2p35RP);
		boxs[35] = ShortLineRailRoad;
		ChanceBox ChanceBox3 = new ChanceBox(36);
		boxs[36] = ChanceBox3;
		NormalProperty ParkPlace = new NormalProperty(37, "Park Place", 9, 350, 175, 35,
				new int[] { 175, 500, 1100, 1300 }, 1500, g9p37, g9p37RP);
		boxs[37] = ParkPlace;
		TaxBox LuxuryTax = new TaxBox(38, "Luxury Tax", 100);
		boxs[38] = LuxuryTax;
		NormalProperty BoardWalk = new NormalProperty(39, "Board Walk", 9, 400, 200, 50,
				new int[] { 200, 600, 1400, 1700 }, 2000, g9p39, g9p39RP);
		boxs[39] = BoardWalk;
	}

	/**
	 * Generate Group Properties
	 */
	private void generateGroupProperty() {
		groupProperty.add(new GroupProperty(1, "Brown", 2));
		groupProperty.add(new GroupProperty(2, "Rail", 4));
		groupProperty.add(new GroupProperty(3, "Cyan", 3));
		groupProperty.add(new GroupProperty(4, "Pink", 3));
		groupProperty.add(new GroupProperty(5, "Orange", 3));
		groupProperty.add(new GroupProperty(6, "Red", 3));
		groupProperty.add(new GroupProperty(7, "Yellow", 3));
		groupProperty.add(new GroupProperty(8, "Green", 3));
		groupProperty.add(new GroupProperty(9, "Blue", 3));
		groupProperty.add(new GroupProperty(10, "Company", 2));
	}

	/**
	 * Generate Chance Cards
	 */
	private void generateChance() {
		chanceCards.add(new Card("Advance Go", 1, chance1));
		chanceCards.add(new Card("Advance Illinois Avenue", 2, chance2));
		chanceCards.add(new Card("Advance ST Charles Place", 3, chance3));
		chanceCards.add(new Card("Advance nearest RailRoad", 4, chance4));
		chanceCards.add(new Card("Advance nearest Utility", 5, chance5));
		chanceCards.add(new Card("Bank pay 50", 6, chance6));
		chanceCards.add(new Card("Out jail", 7, chance7));
		chanceCards.add(new Card("Back 3 spaces", 8, chance8));
		chanceCards.add(new Card("Go jail", 9, chance9));
		chanceCards.add(new Card("General Repairs", 10, chance10));
		chanceCards.add(new Card("Speeding fine", 11, chance11));
		chanceCards.add(new Card("Advance Reading RailRoad", 12, chance12));
		chanceCards.add(new Card("Advance Board Walk", 13, chance13));
		chanceCards.add(new Card("Receive 150", 14, chance14));
		chanceCards.add(new Card("Pay each player 50", 15, chance15));
	}

	/**
	 * Generate Community Cards
	 */
	private void generateCommunity() {
		communityCards.add(new Card("Advance Go", 1, community1));
		communityCards.add(new Card("Receive 200", 2, community2));
		communityCards.add(new Card("Pay 50", 3, community3));
		communityCards.add(new Card("Receive 50", 4, community4));
		communityCards.add(new Card("Out Jail", 5, community5));
		communityCards.add(new Card("Go Jail", 6, community6));
		communityCards.add(new Card("Receive 20", 7, community7));
		communityCards.add(new Card("Receive 10 each player", 8, community8));
		communityCards.add(new Card("Receive 100", 9, community9));
		communityCards.add(new Card("Pay 100", 10, community10));
		communityCards.add(new Card("Pay 50", 11, community11));
		communityCards.add(new Card("Receive 25", 12, community12));
		communityCards.add(new Card("Street repairs", 13, community13));
		communityCards.add(new Card("Receive 10", 14, community14));
		communityCards.add(new Card("Receive 100", 15, community15));
		communityCards.add(new Card("Receive 100", 16, community16));
	}

	/**
	 * Get number max of properties per group
	 * 
	 * @param index
	 *            Id of group
	 * @return number max of properties per group
	 */
	public int getMaxPropertiesPerGroup(int index) {
		for (GroupProperty gp : groupProperty) {
			if (gp.getId() == index)
				return gp.getTotalProperty();
		}
		return 0;
	}

	/**
	 * Get Board Box
	 * 
	 * @param pos
	 *            integer of position of the board box
	 * @return Board Box
	 */
	public BoardBox getBoardBox(int pos) {
		return boxs[pos];
	}

	/**
	 * Get Community Card
	 * 
	 * @param val
	 *            index position from Community Cards Vector
	 * @return card
	 */
	public Card getCommunityCard(int val) {
		return communityCards.get(val - 1);
	}

	/**
	 * Get Chance card
	 * 
	 * @param val
	 *            index position from Chance Cards Vector
	 * @return card
	 */
	public Card getChanceCard(int val) {
		System.out.println("variaver recebida (getChanceCard )" + val);
		return chanceCards.get(val - 1);
	}
}