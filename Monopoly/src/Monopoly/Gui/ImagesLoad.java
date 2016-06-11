package Monopoly.Gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagesLoad extends JPanel {

	protected static BufferedImage board;
	protected static BufferedImage house;
	protected static BufferedImage hotel;
	protected static BufferedImage chanceJailCard;
	protected static BufferedImage communityJailCard;
	protected static BufferedImage wait;


	
	public ImagesLoad() {

		try {
			board = ImageIO.read(new File("resources/images/Monopoly.jpg"));
			house = ImageIO.read(new File("resources/images/house.png"));
			hotel = ImageIO.read(new File("resources/images/hotel.png"));
			wait = ImageIO.read(new File("resources/images/wait.png"));
			
			chanceJailCard = ImageIO.read(new File("resources/images/cardsOwned/7.jpg"));
			communityJailCard = ImageIO.read(new File("resources/images/cardsOwned/5.jpg"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}