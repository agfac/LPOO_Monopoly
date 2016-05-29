package Monopoly.Gui;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

public class GamePanel extends ImagesLoad {
	
	private int dimXFrame;
	private int dimYFrame;


	public GamePanel(int dimXFrame, int dimYFrame) {
		super();
		this.dimXFrame = dimXFrame;
		this.dimYFrame = dimYFrame;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.white);

		g.drawImage(board, 0, 0, this.getWidth(), this.getHeight(), 0, 0, board.getWidth(), board.getHeight(), null);
			
	}	
}
