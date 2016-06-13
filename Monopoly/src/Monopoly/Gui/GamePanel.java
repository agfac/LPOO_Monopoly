package Monopoly.Gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import Monopoly.Logic.Game;
import Monopoly.Logic.NormalProperty;
import Monopoly.Logic.Player;
import Monopoly.Logic.Property;

public class GamePanel extends ImagesLoad implements ActionListener {
	Timer timer = new Timer(500, this);
	private int piecesSize = 45;
	private int houseSize = 20;
	private int hotelWight = 35;
	private int deltaX = 105;
	private int deltaY = 81;

	// SOUTH
	private int xInHouseS = 1015;
	private int yInHouseS = 865;
	private int xInHotelS = 1040;
	private int yInHotelS = 856;
	// WEST
	private int xInHouseW = 137;
	private int yInHouseW = 780;
	private int xInHotelW = 130;
	private int yInHotelW = 800;
	// NORTH
	private int xInHouseN = 180;
	private int yInHouseN = 105;
	private int xInHotelN = 197;
	private int yInHotelN = 97;
	// EAST
	private int xInHouseE = 1120;
	private int yInHouseE = 132;
	private int xInHotelE = 1113;
	private int yInHotelE = 156;

	Game game;

	public GamePanel(Game game) {
		super();
		this.game = game;
		timer.start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g);
		BufferedImage aux;
		g.setColor(Color.white);
		g.drawImage(board, 0, 0, board.getWidth(), board.getHeight(), 0, 0, board.getWidth(), board.getHeight(), null);

		if (game.getPlayers() != null) {
			for (Player p : game.getPlayers()) {
				draw(g, p, p.getPosition().getX(), p.getPosition().getY());
		
				showPropertiesHousesAndHotels(g, p);

				if (game.getChanceOption() != null && game.getTimeToShowChanceCard() != 0) {
					g.drawImage(game.getBoard().getChanceCard(game.getChanceOption()).getImage(), 170, 135, 400+175, 200+158, 0, 0, game.getBoard().getChanceCard(game.getChanceOption()).getImage().getWidth(), game.getBoard().getChanceCard(game.getChanceOption()).getImage().getHeight(), null);
					game.decTimeToShowChanceCard();
				}
				if (game.getCommunityOption() != null && game.getTimeToShowCommunityCard() != 0) {
					g.drawImage(game.getBoard().getCommunityCard(game.getCommunityOption()).getImage(), 710, 655, 400+710, 200+655, 0, 0, game.getBoard().getCommunityCard(game.getCommunityOption()).getImage().getWidth(), game.getBoard().getCommunityCard(game.getCommunityOption()).getImage().getHeight(), null);
					game.decTimeToShowCommunityCard();
				}
			}
			
			//Dice value
			if (game.getDice1().getValue() != 0){
				g.drawImage(game.getDice1().getImage(), 550, 750, 550+80, 750+80, 0, 0, game.getDice1().getImage().getWidth(), game.getDice1().getImage().getHeight(), null);
				g.drawImage(game.getDice2().getImage(),650, 750, 650+80, 750+80, 0, 0, game.getDice2().getImage().getWidth(), game.getDice2().getImage().getHeight(), null);	
			}
			//Player Name
			g2d.setColor(Color.BLACK);
			g2d.setFont(new Font("ArialRoundedMTBold", Font.PLAIN, 35)); //Geogia
			g2d.drawString(game.getCurrentPlayer().getName(), 600, 300);
			
			//Property Image
			if (game.getCurrentPlayer().getDicesValue() != 0 && !game.getCurrentPlayer().getInJail() && game.getCurrentPlayer().getCellsToMove() != 0) {
				aux = game.getBoard().getBoardBox(game.getCurrentPlayer().getValuePosition()).getImage();
				g.drawImage(aux, 524, 350, 524 + aux.getWidth(),350 + aux.getHeight(), 0, 0,aux.getWidth(),aux.getHeight(), null);
			}
			else if(game.getCurrentPlayer().getDicesValue() != 0  && game.getCurrentPlayer().getValuePosition() == game.getCurrentPlayer().getPos().getPos()){
				aux = game.getBoard().getBoardBox(game.getCurrentPlayer().getValuePosition()).getImage();
				g.drawImage(aux, 524, 350,524 + aux.getWidth(),350 + aux.getHeight(), 0, 0,aux.getWidth(),aux.getHeight(), null);
			}
		}
	}

	public void draw(Graphics g, Player player, int x, int y) {
		g.drawImage(player.getSymbol().getPiece(), x, y, x + piecesSize, y + piecesSize, 0, 0,
				player.getSymbol().getPiece().getWidth(), player.getSymbol().getPiece().getHeight(), null);
	}

	public void actionPerformed(ActionEvent ev) {
		if (ev.getSource() == timer && game.getPlayers() != null) {
			
				if (game.getCurrentPlayer().getCellsToMove() != 0) { // && !p.getInJail()
					game.getCurrentPlayer().updateGUIPosition();
					game.getCurrentPlayer().setCellsToMove(game.getCurrentPlayer().getCellsToMove() - 1);

				} 
//				else {
//					if (((game.getCurrentPlayer().getPos().getPos() == 2 || game.getCurrentPlayer().getPos().getPos() == 17 || game.getCurrentPlayer().getPos().getPos() == 33)
//							&& game.getCommunityOption() == null)
//							|| ((game.getCurrentPlayer().getPos().getPos() == 7 || game.getCurrentPlayer().getPos().getPos() == 22 || game.getCurrentPlayer().getPos().getPos() == 36)
//									&& game.getChanceOption() == null))
//						game.checkSpecialBoardBox(game.getCurrentPlayer());
//				}
			repaint();
		}
	}

	public void teste(BufferedImage image, String cardTipe) {
		JDialog dialog = new JDialog();
		dialog.setPreferredSize(new Dimension(400, 200));
		dialog.setUndecorated(true);
		if (cardTipe == "chance")
			dialog.setBounds(175, 158, 400, 200);
		else
			dialog.setBounds(713, 685, 400, 200);
		JLabel picLabel = new JLabel(new ImageIcon(image));
		// JOptionPane.showMessageDialog(null, picLabel, "About",
		// JOptionPane.PLAIN_MESSAGE, null);
		dialog.add(picLabel);
		dialog.pack();
		dialog.setVisible(true);

	}

	public void showPropertiesHousesAndHotels(Graphics g, Player p) {
		for (Property pro : p.getPropertiesOwned()) {
			int aux = ((pro.getPos() - 1) * deltaX); // SOUTH
			int aux2 = ((pro.getPos() - 11) * deltaY); // WEST
			int aux3 = ((pro.getPos() - 21) * deltaX); // NORTH
			int aux4 = ((pro.getPos() - 31) * deltaY); // EAST
			if (pro.getPos() >= 1 && pro.getPos() <= 9) {
				// SOUTH
				if ((pro instanceof NormalProperty) && ((NormalProperty) pro).getNrHotels() > 0) {
					g.drawImage(hotel, xInHotelS - aux, yInHotelS, xInHotelS + hotelWight - aux, yInHotelS + hotelWight,
							0, 0, hotel.getWidth(), hotel.getHeight(), null);
				}
				if ((pro instanceof NormalProperty) && ((NormalProperty) pro).getNrHouses() > 0) {
					for (int i = 0; i < ((NormalProperty) pro).getNrHouses(); i++) {
						g.drawImage(house, xInHouseS + i * houseSize - aux, yInHouseS,
								xInHouseS + (i + 1) * houseSize - aux, yInHouseS + houseSize, 0, 0, house.getWidth(),
								house.getHeight(), null);
					}
				}
			} else if (pro.getPos() >= 11 && pro.getPos() <= 19) {
				// WEST
				if ((pro instanceof NormalProperty) && ((NormalProperty) pro).getNrHotels() > 0) {
					g.drawImage(hotel, xInHotelW, yInHotelW - aux2, xInHotelW + hotelWight,
							yInHotelW + hotelWight - aux2, 0, 0, hotel.getWidth(), hotel.getHeight(), null);
				}
				if ((pro instanceof NormalProperty) && ((NormalProperty) pro).getNrHouses() > 0) {
					for (int i = 0; i < ((NormalProperty) pro).getNrHouses(); i++) {
						g.drawImage(house, xInHouseW, yInHouseW + i * houseSize - aux2, xInHouseW + houseSize,
								yInHouseW + (i + 1) * houseSize - aux2, 0, 0, house.getWidth(), house.getHeight(),
								null);
					}
				}
			} else if (pro.getPos() >= 21 && pro.getPos() <= 29) {
				// NORTH
				if ((pro instanceof NormalProperty) && ((NormalProperty) pro).getNrHotels() > 0) {
					g.drawImage(hotel, xInHotelN + aux3, yInHotelN, xInHotelN + hotelWight + aux3,
							yInHotelN + hotelWight, 0, 0, hotel.getWidth(), hotel.getHeight(), null);
				}
				if ((pro instanceof NormalProperty) && ((NormalProperty) pro).getNrHouses() > 0) {
					for (int i = 0; i < ((NormalProperty) pro).getNrHouses(); i++) {
						g.drawImage(house, xInHouseN + i * houseSize + aux3, yInHouseN,
								xInHouseN + (i + 1) * houseSize + aux3, yInHouseN + houseSize, 0, 0, house.getWidth(),
								house.getHeight(), null);
					}
				}
			} else {
				// EAST
				if ((pro instanceof NormalProperty) && ((NormalProperty) pro).getNrHotels() > 0) {
					g.drawImage(hotel, xInHotelE, yInHotelE + aux4, xInHotelE + hotelWight,
							yInHotelE + hotelWight + aux4, 0, 0, hotel.getWidth(), hotel.getHeight(), null);
				}
				if ((pro instanceof NormalProperty) && ((NormalProperty) pro).getNrHouses() > 0) {
					for (int i = 0; i < ((NormalProperty) pro).getNrHouses(); i++) {
						g.drawImage(house, xInHouseE, yInHouseE + i * houseSize + aux4, xInHouseE + houseSize,
								yInHouseE + (i + 1) * houseSize + aux4, 0, 0, house.getWidth(), house.getHeight(),
								null);
					}
				}
			}

		} // for property
	}

}
