package Monopoly.Gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import Monopoly.Logic.Board;
import Monopoly.Logic.Player;
import Monopoly.Logic.PlayerSymbol;

public class GamePanel extends ImagesLoad 
implements MouseListener, MouseMotionListener {
	
	private int dimXFrame;
	private int dimYFrame;
	private int piecesSize = 45;
	
	private int deltaX = 105;
	private int deltaY = 81;
	//SOUTH
	private int xPosUpS = 1120;
	private int xPosDownS = 1170;
	private int yPosUpS  = 890;
	private int yPosDownS  = 930;
	//WEST
	private int xPosUpW = 10;
	private int xPosDownW = 60;
	private int yPosUpW = 775;
	private int yPosDownW = 820;
	//NORTH
	private int xPosUpN = 175;
	private int xPosDownN = 225;
	private int yPosUpN = 5;
	private int yPosDownN = 55;
	//EAST
	private int xPosUpE = 1160;
	private int xPosDownE = 1210;
	private int yPosUpE = 125;
	private int yPosDownE = 170;
	
	Vector<Player> player;
	PlayerSymbol dog = new PlayerSymbol(1, "Dog", dogPiece);
	Board boardsdcdc = new Board();
	
	private int position = 0;
	public GamePanel() {
		super();

		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		//TODO vector deve ser recebido --------------------------------------------------------
		player = new Vector<Player>();
		player.add(new Player("Pedro", dog, 10000, boardsdcdc.getBoardBox(0)));
		
	}
	
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
		
		//SOUTH
		if(position >= 0 && position <= 10){
			//jogador 1
			draw(g, player.get(0), xPosUpS, yPosUpS);
//			//jogador 2
//			draw(g, player.get(1), xPosUpS, yPosDownS);
//			//jogador 3
//			draw(g, player.get(2), xPosDownS, yPosUpS);
//			//jogador 4
//			draw(g, player.get(3), xPosDownS, yPosDownS);
		}
		//WEST
		if (position > 10 && position <= 20){
			//jogador 1
			draw(g, player.get(0), xPosUpW, yPosUpW);
//			//jogador 2
//			draw(g, player.get(1), xPosUpW, yPosDownW);
//			//jogador 3
//			draw(g, player.get(2), xPosDownW, yPosUpW);
//			//jogador 4
//			draw(g, player.get(3), xPosDownW, yPosDownW);
		}
		//NORTH
		if(position > 20 && position <= 30){
			//jogador 1
			draw(g, player.get(0), xPosUpN, yPosUpN);
//			//jogador 2
//			draw(g, player.get(1), xPosUpN, yPosDownN);
//			//jogador 3
//			draw(g, player.get(2), xPosDownN, yPosUpN);
//			//jogador 4
//			draw(g, player.get(3), xPosDownN, yPosDownN);
		}
		//EAST
		if(position > 30 && position <= 39){
			//jogador 1
			draw(g, player.get(0), xPosUpE, yPosUpE);
//			//jogador 2
//			draw(g, player.get(1), xPosUpE, yPosDownE);
//			//jogador 3
//			draw(g, player.get(2), xPosDownE, yPosUpE);
//			//jogador 4
//			draw(g, player.get(3), xPosDownE, yPosDownE);
		}

		
	}
	
	public void draw (Graphics g, Player player, int x, int y){
		
		g.drawImage(player.getSymbol().getPiece(), x, y, x + piecesSize, y + piecesSize, 0, 0, player.getSymbol().getPiece().getWidth(), player.getSymbol().getPiece().getHeight(), null);
	}
	
	public void update(){
		//SOUTH
		if(position >= 0 && position <= 10){
			xPosUpS -=  deltaX;
			xPosDownS -=  deltaX;
		}
		//WEST
		if (position > 10 && position <= 20){
			yPosUpW -= deltaY;
			yPosDownW -= deltaY;
		}
		//NORTH
		if(position > 20 && position <= 30){
			xPosUpN +=  deltaX;
			xPosDownN +=  deltaX;
		}
		//EAST
		if(position > 30 && position <= 39){
			yPosUpE += deltaY;
			yPosDownE += deltaY;
		}
		position++;
		repaint();
		if(position == 40){
			position = 0;
			//NORTH
			xPosUpS = 1120;
			xPosDownS = 1170;
			yPosUpS  = 890;
			yPosDownS  = 930;
			//WEST
			xPosUpW = 10;
			xPosDownW = 60;
			yPosUpW = 775;
			yPosDownW = 820;
			//NORTH
			xPosUpN = 175;
			xPosDownN = 225;
			yPosUpN = 5;
			yPosDownN = 55;
			//EAST
			xPosUpE = 1160;
			xPosDownE = 1210;
			yPosUpE = 125;
			yPosDownE = 170;
		}
		System.out.println("Position -> "+ position);
		
	}
	
	
	
	
	
	//APAGAR APOS TESTE
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}
	
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("x -> " + e.getX());		
		System.out.println("y -> " + e.getY());	
		update();
	requestFocus();
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}	
}
