package Monopoly.Gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Monopoly.Logic.Board;
import Monopoly.Logic.Game;
import Monopoly.Logic.Player;
import Monopoly.Logic.PlayerSymbol;
import Monopoly.Logic.Property;

public class InfoPanel extends ImagesLoad {

	Game game;

	public InfoPanel(Game game) {
		super();
		this.game = game;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.white);
		doDrawing(g);
	}

	private void doDrawing(Graphics g) {
		int nPlayers = game.getPlayers().size();
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
		for (Player p : game.getPlayers()) {
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
			for(int j = 0; j < p.getPropertiesOwned().size(); j++){
			//for (Property pro: p.getPropertiesOwned()) {
			//	g.drawImage(pro.getImage(), xi, yi, xf, yf, 0, 0, pro.getImage().getWidth(), pro.getImage().getHeight(), null);
				g.drawImage(p.getPropertiesOwned().get(j).getImage(), xi, yi, xf, yf, 0, 0, p.getPropertiesOwned().get(j).getImage().getWidth(), p.getPropertiesOwned().get(j).getImage().getHeight(), null);
				xi += propertySize;
				xf += propertySize;
				//if (p.getPropertiesOwned().size() == 4 || p.getPropertiesOwned().size() == 8 || p.getPropertiesOwned().size() == 12 || p.getPropertiesOwned().size() == 16) {
				if (j == 3 || j == 7 || j == 11 || j == 15) {
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
