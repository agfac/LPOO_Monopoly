package Monopoly.Gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

	protected static BufferedImage diceFace1;
	protected static BufferedImage diceFace2;
	protected static BufferedImage diceFace3;
	protected static BufferedImage diceFace4;
	protected static BufferedImage diceFace5;
	protected static BufferedImage diceFace6;
	
	
	public static void ImagesLoad() {

		try {
			
			diceFace1 = ImageIO.read(new File("resources/images/dice/face1.png"));
			diceFace2 = ImageIO.read(new File("resources/images/dice/face2.png"));
			diceFace3 = ImageIO.read(new File("resources/images/dice/face3.png"));
			diceFace4 = ImageIO.read(new File("resources/images/dice/face4.png"));
			diceFace5 = ImageIO.read(new File("resources/images/dice/face5.png"));
			diceFace6 = ImageIO.read(new File("resources/images/dice/face6.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
