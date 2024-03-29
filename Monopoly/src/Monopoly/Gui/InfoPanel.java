package Monopoly.Gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.plaf.synth.SynthSeparatorUI;

import Monopoly.Logic.Board;
import Monopoly.Logic.Game;
import Monopoly.Logic.Player;
import Monopoly.Logic.PlayerSymbol;
import Monopoly.Logic.Property;

public class InfoPanel extends ImagesLoad implements ActionListener {
	Timer timer = new Timer(200, this);
	Game game;

	public InfoPanel(Game game) {
		super();
		this.game = game;
		timer.start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.white);
		doDrawing(g);
	}

	private void doDrawing(Graphics g) {

		if (game.getPlayers() != null) {

			int nPlayers = game.getPlayers().size();
			int boxWidht = this.getWidth();
			int boxHeight = this.getHeight() / nPlayers;
			int xIn = 0;
			int yIn = 0;

			ArrayList<Graphics2D> g1 = new ArrayList<Graphics2D>();

			int propertySize = 132;
			int xi = 100;
			int yi = 60;//10;
			int xf = xi + propertySize;
			int yf = 130;//70;
			for (Player p : game.getPlayers()) {
				g.drawImage(p.getSymbol().getPiece(), 0, yIn, 90, yIn + 90, 0, 0, p.getSymbol().getPiece().getWidth(),
						p.getSymbol().getPiece().getHeight(), null);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setColor(Color.BLACK);
				g2d.drawRect(xIn, yIn, boxWidht, boxHeight);
				g2d.setFont(new Font("LucidaSan", Font.PLAIN, 13)); 
				g2d.drawString(p.getName(), 10, yIn + 90 + 20);
				g2d.drawString(String.valueOf(p.getBalance())+ " M ", 10, yIn + 90 + 40);
				g2d.drawString(p.getMensage(), xi, yIn + 50);
				g2d.setFont(new Font("LucidaSan", Font.PLAIN, 20));
				g2d.drawString(p.getPos().getName(), xi, yIn + 20);
				//Dice value
				if (p.equals(game.getCurrentPlayer()) && game.getDice1().getValue() != 0 && game.getTimeToShowDice() != 0){
					g.drawImage(game.getDice1().getImage(), 10, yIn+145, 10+35, yIn+145+35, 0, 0, game.getDice1().getImage().getWidth(), game.getDice1().getImage().getHeight(), null);
					g.drawImage(game.getDice2().getImage(),50, yIn+145, 50+35, yIn+145+35, 0, 0, game.getDice2().getImage().getWidth(), game.getDice2().getImage().getHeight(), null);	
					game.decTimeToShowDice();
				}
				
				
				// TODO
				int yAuxI = yi;
				int yAuxF = yf;
				for (int j = 0; j < p.getPropertiesOwned().size(); j++) {
					g.drawImage(p.getPropertiesOwned().get(j).getIconImage(), xi, yi, xf, yf, 0, 0,
							p.getPropertiesOwned().get(j).getIconImage().getWidth(),
							p.getPropertiesOwned().get(j).getIconImage().getHeight(), null);
					if (p.getPropertiesOwned().get(j).getMortgage())
						g.drawImage(mortgage, xi+50, yi, xf, yf-20, 0, 0, mortgage.getWidth(), mortgage.getHeight(), null);
					xi += propertySize;
					xf += propertySize;
					if (j == 3 || j == 7 || j == 11 || j == 15 || j == 19 || j == 23 || j == 27) {
						xi = 100;
						xf = xi + propertySize;
						yi += 70;//60;
						yf += 70;//60;
					}

				}
				for (int j = 0; j < p.getNrCardJail(); j++) {
					g.drawImage(chanceJailCard, xi, yi, xf, yf, 0, 0, chanceJailCard.getWidth(),
							chanceJailCard.getHeight(), null);
					xi += propertySize;
					xf += propertySize;
					if (j == 3 || j == 7 || j == 11 || j == 15 || j == 19 || j == 23 || j == 27) {
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

		} else
			g.drawImage(wait, 0, 0, wait.getWidth(), wait.getHeight(), 0, 0, wait.getWidth(), wait.getHeight(), null);

	}

	public void actionPerformed(ActionEvent ev) {
		repaint();
	}

}
