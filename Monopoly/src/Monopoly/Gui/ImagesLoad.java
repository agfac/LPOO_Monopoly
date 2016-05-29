package Monopoly.Gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagesLoad extends JPanel{

	protected static BufferedImage board;
	protected static BufferedImage dogPiece;
	

	public ImagesLoad(){
		
		 try {
			 board =  ImageIO.read(new File("images/MonopolyBoard.jpg"));
			 dogPiece =  ImageIO.read(new File("images/DogPiece.jpg"));


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
	}
	

}