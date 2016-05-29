package Monopoly.Gui;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

public class PlayerPanel extends ImagesLoad{

	private int dimXFrame;
	private int dimYFrame;


	public PlayerPanel(int dimXFrame, int dimYFrame) {
		super();
		this.dimXFrame = dimXFrame;
		this.dimYFrame = dimYFrame;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.white);

		g.drawImage(dogPiece, 0, 0, this.getWidth()/2, this.getHeight()/2, 0, 0, dogPiece.getWidth(), dogPiece.getHeight(), null);
			
	}	

}
