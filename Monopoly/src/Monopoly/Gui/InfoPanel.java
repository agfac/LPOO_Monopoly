package Monopoly.Gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Monopoly.Logic.Board;
import Monopoly.Logic.Player;
import Monopoly.Logic.PlayerSymbol;

public class InfoPanel extends ImagesLoad {

	private int dimXFrame;
	private int dimYFrame;

	private JTextField textField;
	private JTextField textField_1;

	Vector<Player> players;

	public InfoPanel(Vector<Player> players) {
		super();
		this.players = players;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.white);
		doDrawing(g);
	}

	private void doDrawing(Graphics g) {
		int nPlayers = players.size();
		int boxWidht = this.getWidth();
		int boxHeight = this.getHeight() / nPlayers;
		int xIn = 0;
		int yIn = 0;

		ArrayList<Graphics2D> g1 = new ArrayList<Graphics2D>();

		int propertySize = 132;
		int xi = 100;
		int yi = 10;
		int xf = xi + propertySize;
		int yf = 70;
		for (Player p : players) {
			int nProperties = p.getPropertiesOwned().size();
			g.drawImage(p.getSymbol().getPiece(), 0, yIn, 90, yIn + 90, 0, 0, p.getSymbol().getPiece().getWidth(),
					p.getSymbol().getPiece().getHeight(), null);

			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Color.BLACK);
			g2d.drawRect(xIn, yIn, boxWidht, boxHeight);
			g2d.drawString(p.getName(), 10, yIn + 90 + 20);
			g2d.drawString(String.valueOf(p.getBalance()), 10, yIn + 90 + 40);

			// TODO
			int yAuxI = yi;
			int yAuxF = yf;
			for (int j = 1; j <= nProperties; j++) {
				g.drawImage(teste, xi, yi, xf, yf, 0, 0, teste.getWidth(), teste.getHeight(), null);
				xi += propertySize;
				xf += propertySize;
				if (j == 4 || j == 8 || j == 12 || j == 16) {
					xi = 100;
					xf = xi + propertySize;
					yi += 60;
					yf += 60;
				}
			}
			// Reset values
			xi = 100;
			xf = xi + propertySize;
			yi = yAuxI + boxHeight;
			yf = yAuxF + boxHeight;

			yIn += boxHeight;

		}
	}

}
