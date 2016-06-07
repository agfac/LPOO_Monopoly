package Monopoly.Gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
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

public class GamePanel extends ImagesLoad implements MouseListener, KeyListener, ActionListener {
	Timer timer = new Timer(200, this);
	private int piecesSize = 45;
	private int houseSize = 20;
	private int hotelWight = 35;
	private int deltaX = 105;
	private int deltaY = 81;
	
	//SOUTH
	private int xInHouseS = 1015;
	private int yInHouseS = 865;
	private int xInHotelS = 1040;
	private int yInHotelS = 856;
	//WEST
	private int xInHouseW = 137;
	private int yInHouseW = 780;
	private int xInHotelW = 130;
	private int yInHotelW = 800;
	//NORTH
	private int xInHouseN = 180;
	private int yInHouseN = 105;
	private int xInHotelN = 197;
	private int yInHotelN = 97;
	//EAST
	private int xInHouseE = 1120;
	private int yInHouseE = 132;
	private int xInHotelE = 1113;
	private int yInHotelE = 156;
	
	Game game;

	public GamePanel(Game game) {
		super();
		this.addKeyListener(this);
		this.addMouseListener(this); // TODO APAGAR
		this.game = game;
		timer.start();
		
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.white);
		g.drawImage(board, 0, 0, board.getWidth(), board.getHeight(), 0, 0, board.getWidth(), board.getHeight(), null);
		
		for (Player p : game.getPlayers()){
			draw(g, p, p.getPosition().getX(), p.getPosition().getY());
			
			if ( (p.getPos() instanceof Property) && p.getDicesValue() !=  0 && !p.getInJail()){
				g.drawImage(((Property)p.getPos()).getImage(), 524, 330, 524+((Property)p.getPos()).getImage().getWidth(), 330+((Property)p.getPos()).getImage().getHeight(), 0, 0, ((Property)p.getPos()).getImage().getWidth(), ((Property)p.getPos()).getImage().getHeight(), null);
			}
			
			showPropertiesHousesAndHotels(g, p);
	
			if(game.getChanceOption() != null){
				teste(game.getBoard().getChanceCard(game.getChanceOption()).getImage(), "chance"); //x->171 y->133
//				g.drawImage(game.getBoard().getChanceCard(game.getChanceOption()).getImage(), 175, 158, 175+game.getBoard().getChanceCard(game.getChanceOption()).getImage().getWidth(), 158+game.getBoard().getChanceCard(game.getChanceOption()).getImage().getHeight(), 0, 0, game.getBoard().getChanceCard(game.getChanceOption()).getImage().getWidth(), game.getBoard().getChanceCard(game.getChanceOption()).getImage().getHeight(), null);
//				game.setChanceOption(null);
			}
			if(game.getCommunityOption() != null){
				teste(game.getBoard().getCommunityCard(game.getCommunityOption()).getImage(), "community");
//				g.drawImage(game.getBoard().getCommunityCard(game.getCommunityOption()).getImage(), 713, 689, 713+game.getBoard().getCommunityCard(game.getCommunityOption()).getImage().getWidth(), 689+game.getBoard().getCommunityCard(game.getCommunityOption()).getImage().getHeight(), 0, 0, board.getWidth(), board.getHeight(), null);
//				game.setCommunityOption(null);
			}
		}
	}

	public void draw(Graphics g, Player player, int x, int y) {
		g.drawImage(player.getSymbol().getPiece(), x, y, x + piecesSize, y + piecesSize, 0, 0,
				player.getSymbol().getPiece().getWidth(), player.getSymbol().getPiece().getHeight(), null);
	}
	
	public void actionPerformed(ActionEvent ev) {
		if (ev.getSource() == timer) {
			for (Player p : game.getPlayers()) {
				if (p.getCellsToMove() != 0) { // && !p.getInJail()
					p.updateGUIPosition();
					p.setCellsToMove(p.getCellsToMove() - 1);
					System.out.println("p.getCellsToMove()" + p.getCellsToMove());
					
				} else{
					// game.updateGame(p);
					//fazer pequena pausa
					if ( ((p.getPos().getPos() == 2 ||  p.getPos().getPos() == 17  || p.getPos().getPos() == 33) && game.getCommunityOption()== null) 
							|| ((p.getPos().getPos() == 7 ||  p.getPos().getPos() == 22  || p.getPos().getPos() == 36) && game.getChanceOption()== null)  ) 
						game.verificarSeTemPlayers(p);
					game.buyProperty(p);
				}
			}
			repaint();
		}
	}

	public void teste(BufferedImage image, String cardTipe){
		JDialog dialog = new JDialog();
		dialog.setPreferredSize(new Dimension(400,200));
		dialog.setUndecorated(true);
		if (cardTipe == "chance")
			dialog.setBounds(175, 158, 400, 200);
		else
			dialog.setBounds(713, 685, 400, 200);
		JLabel picLabel = new JLabel(new ImageIcon(image));
//		JOptionPane.showMessageDialog(null, picLabel, "About", JOptionPane.PLAIN_MESSAGE, null);
		dialog.add( picLabel );
		dialog.pack();
		dialog.setVisible(true);

	}
	public void showPropertiesHousesAndHotels(Graphics g, Player p){			
		for(Property pro: p.getPropertiesOwned()){
			//TODO tambem é para apagar
//			if (game.getPlayers().get(0).haveAllPropertiesGroup((Property) game.getBoard().getBoardBox(1))){
//				game.createHouses(game.getPlayers().get(0), (NormalProperty) game.getBoard().getBoardBox(1), 4);
//				
//				game.createHotel(game.getPlayers().get(0), ((NormalProperty) game.getBoard().getBoardBox(1)));
//				((NormalProperty)pro).setHouseN(0);}
//				
			//TODO apagar depoie dos teste // apagar tambem linha 22 a 29 NP
//			if ( (pro instanceof NormalProperty)){
//				((NormalProperty)pro).setHouseN(4);
//				((NormalProperty)pro).setHotelN(1);
//			}
			//-----------------------------------------
			
			int aux = ((pro.getPos()-1)*deltaX);   //SOUTH
			int aux2 = ((pro.getPos()-11)*deltaY); //WEST
			int aux3 = ((pro.getPos()-21)*deltaX); //NORTH
			int aux4 = ((pro.getPos()-31)*deltaY); //EAST
			if(pro.getPos() >= 1 && pro.getPos() <= 9){
				//SOUTH
				if ( (pro instanceof NormalProperty) && ((NormalProperty)pro).getNrHotels() > 0){
					g.drawImage(hotel, xInHotelS-aux, yInHotelS, xInHotelS+hotelWight-aux, yInHotelS+hotelWight, 0, 0, hotel.getWidth(), hotel.getHeight(), null);
				}
				if ( (pro instanceof NormalProperty) && ((NormalProperty)pro).getNrHouses() > 0){
					for(int i = 0; i < ((NormalProperty)pro).getNrHouses(); i++){
						g.drawImage(house, xInHouseS+i*houseSize-aux, yInHouseS, xInHouseS+(i+1)*houseSize-aux, yInHouseS+houseSize, 0, 0, house.getWidth(), house.getHeight(), null);
					}
				}
			}else if(pro.getPos() >= 11 && pro.getPos() <= 19){
				//WEST
				if ( (pro instanceof NormalProperty) && ((NormalProperty)pro).getNrHotels() > 0){
					g.drawImage(hotel, xInHotelW, yInHotelW-aux2, xInHotelW+hotelWight, yInHotelW+hotelWight-aux2, 0, 0, hotel.getWidth(), hotel.getHeight(), null);
				}
				if ( (pro instanceof NormalProperty) && ((NormalProperty)pro).getNrHouses() > 0){
					for(int i = 0; i < ((NormalProperty)pro).getNrHouses(); i++){
						g.drawImage(house, xInHouseW, yInHouseW+i*houseSize-aux2, xInHouseW+houseSize, yInHouseW+(i+1)*houseSize-aux2, 0, 0, house.getWidth(), house.getHeight(), null);
					}
				}
			}else if(pro.getPos() >= 21 && pro.getPos() <= 29){
				//NORTH
				if ( (pro instanceof NormalProperty) && ((NormalProperty)pro).getNrHotels() > 0){
					g.drawImage(hotel, xInHotelN+aux3, yInHotelN, xInHotelN+hotelWight+aux3, yInHotelN+hotelWight, 0, 0, hotel.getWidth(), hotel.getHeight(), null);
				}
				if ( (pro instanceof NormalProperty) && ((NormalProperty)pro).getNrHouses() > 0){
					for(int i = 0; i < ((NormalProperty)pro).getNrHouses(); i++){
						g.drawImage(house, xInHouseN+i*houseSize+aux3, yInHouseN, xInHouseN+(i+1)*houseSize+aux3, yInHouseN+houseSize, 0, 0, house.getWidth(), house.getHeight(), null);
					}
				}
			}else{
				//EAST
				if ( (pro instanceof NormalProperty) && ((NormalProperty)pro).getNrHotels() > 0){
					g.drawImage(hotel, xInHotelE, yInHotelE+aux4, xInHotelE+hotelWight, yInHotelE+hotelWight+aux4, 0, 0, hotel.getWidth(), hotel.getHeight(), null);
				}
				if ( (pro instanceof NormalProperty) && ((NormalProperty)pro).getNrHouses() > 0){
					for(int i = 0; i < ((NormalProperty)pro).getNrHouses(); i++){
						g.drawImage(house, xInHouseE, yInHouseE+i*houseSize+aux4, xInHouseE+houseSize, yInHouseE+(i+1)*houseSize+aux4, 0, 0, house.getWidth(), house.getHeight(), null);
					}
				}
			}
			
		}//for property
	}
	
	
	
	
	
	
	
	
	// APAGAR APOS TESTE
	@Override
	public void mousePressed(MouseEvent e) {
		 System.out.println("x -> " + e.getX());
		 System.out.println("y -> " + e.getY());

		game.updateGame(game.getPlayers().get(0));
		repaint();

		this.requestFocus();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		game.updateGame(game.getPlayers().get(1));
		repaint();
		this.requestFocus();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
