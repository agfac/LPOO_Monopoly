package Monopoly.Gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagesLoad extends JPanel {

	protected static BufferedImage board;
	protected static BufferedImage dogPiece;
	//Group Properties
//	protected static BufferedImage g1p1, g1p3, 
//	g2p5, g2p15, g2p25, g2p35, 
//	g3p6, g3p8, g3p9, 
//	g4p11, g4p13, g4p14, 
//	g5p16, g5p18, g5p19, 
//	g6p21, g6p23, g6p24, 
//	g7p26, g7p27, g7p29, 
//	g8p31, g8p32, g8p34, 
//	g9p37, g9p39, 
//	g10p12, g10p28; 
	
	//Chance Cards
	

	public ImagesLoad() {

		try {
			dogPiece = ImageIO.read(new File("resources/images/pieces/dog.png"));
			
			board = ImageIO.read(new File("resources/Monopoly.jpg"));
			
//			g1p1 = ImageIO.read(new File("resources/images/propertiesOwned/group1/MediterraneanAvenue.png"));
//			g1p3 = ImageIO.read(new File("resources/images/propertiesOwned/group1/BalticAvenue.png"));
//			g2p5 = ImageIO.read(new File("resources/images/propertiesOwned/group2/ReadingRailRoad.png"));
//			g2p15 = ImageIO.read(new File("resources/images/propertiesOwned/group2/PennsylvaniaRailRoad.png"));
//			g2p25 = ImageIO.read(new File("resources/images/propertiesOwned/group2/BORailRoad.png"));
//			g2p35 = ImageIO.read(new File("resources/images/propertiesOwned/group2/ShortLineRailRoad.png"));
//			g3p6 = ImageIO.read(new File("resources/images/propertiesOwned/group3/OrientalAvenue.png"));
//			g3p8 = ImageIO.read(new File("resources/images/propertiesOwned/group3/VermontAvenue.png"));
//			g3p9 = ImageIO.read(new File("resources/images/propertiesOwned/group3/ConnecticutAvenue.png"));
//			g4p11 = ImageIO.read(new File("resources/images/propertiesOwned/group4/STCharlesPlace.png"));
//			g4p13 = ImageIO.read(new File("resources/images/propertiesOwned/group4/StatesAvenue.png"));
//			g4p14 = ImageIO.read(new File("resources/images/propertiesOwned/group4/VirginiaAvenue.png"));
//			g5p16 = ImageIO.read(new File("resources/images/propertiesOwned/group5/STJamesPlace.png"));
//			g5p18 = ImageIO.read(new File("resources/images/propertiesOwned/group5/TennesseAvenue.png"));
//			g5p19 = ImageIO.read(new File("resources/images/propertiesOwned/group5/NewYorkAvenue.png"));
//			g6p21 = ImageIO.read(new File("resources/images/propertiesOwned/group6/KentuckyAvenue.png"));
//			g6p23 = ImageIO.read(new File("resources/images/propertiesOwned/group6/IndianaAvenue.png"));
//			g6p24 = ImageIO.read(new File("resources/images/propertiesOwned/group6/IllinoisAvenue.png"));
//			g7p26 = ImageIO.read(new File("resources/images/propertiesOwned/group7/AtlanticAvenue.png"));
//			g7p27 = ImageIO.read(new File("resources/images/propertiesOwned/group7/VentnorAvenue.png"));
//			g7p29 = ImageIO.read(new File("resources/images/propertiesOwned/group7/MarvinGardens.png"));
//			g8p31 = ImageIO.read(new File("resources/images/propertiesOwned/group8/PacificAvenue.png"));
//			g8p32 = ImageIO.read(new File("resources/images/propertiesOwned/group8/NorthCarolinaAvenue.png"));
//			g8p34 = ImageIO.read(new File("resources/images/propertiesOwned/group8/PennsylvaniaAvenue.png"));
//			g9p37 = ImageIO.read(new File("resources/images/propertiesOwned/group9/ParkPlace.png"));
//			g9p39 = ImageIO.read(new File("resources/images/propertiesOwned/group9/BoardWalk.png"));
//			g10p12 = ImageIO.read(new File("resources/images/propertiesOwned/group10/ElectricCompany.png"));
//			g10p28 = ImageIO.read(new File("resources/images/propertiesOwned/group10/WaterWorks.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}