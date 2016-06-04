package Monopoly.Gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class InfoPanel extends ImagesLoad {

	private int dimXFrame;
	private int dimYFrame;

	private JTextField textField;
	private JTextField textField_1;

	public InfoPanel() {
		super();
	}

	public InfoPanel(int dimXFrame, int dimYFrame) {
		super();
		this.dimXFrame = dimXFrame;
		this.dimYFrame = dimYFrame;

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.white);
		doDrawing(g);
	}

	private void doDrawing(Graphics g) {
		int nPlayers = 4;
		int boxWidht = this.getWidth();
		int boxHeight = this.getHeight()/nPlayers;
		int xIn = 0;
		int yIn = 0;
		
		ArrayList<Graphics2D> g1 = new ArrayList<Graphics2D>();
		int nProperties = 10;
		

		int propertySize = 132;
		int xi = 100;
		int yi = 10;
		int xf = xi + propertySize;
		int yf = 70;
		for (int i = 0; i < nPlayers; i++) {

			g.drawImage(dogPiece, 0, yIn, 90, yIn+90, 0, 0, dogPiece.getWidth(),dogPiece.getHeight(), null);
			
			Graphics2D g2d = (Graphics2D) g;
//			g2d.setColor(new Color(212, 212, 212));
			g2d.setColor(Color.BLACK);
			g2d.drawRect(xIn, yIn, boxWidht, boxHeight);
			g2d.drawString("Player Name", 10, yIn+90+20);
			g2d.drawString("Balance", 10, yIn+90+40);
			
			//TODO 
			int yAuxI = yi;
			int yAuxF = yf;
			for(int j = 1; j <= nProperties; j++){
				g.drawImage(teste, xi, yi, xf, yf, 0, 0, teste.getWidth(),teste.getHeight(), null);
				xi += propertySize;
				xf += propertySize;
				if(j == 4 || j == 8 || j == 12 || j == 16){
					xi = 100;
					xf = xi + propertySize;
					yi += 60;
					yf += 60;
				}
			}
			
			xi = 100;
			xf = xi + propertySize;
			yi = yAuxI + boxHeight;
			yf = yAuxF + boxHeight;
			
			yIn += boxHeight;
			
		}
	}

}
