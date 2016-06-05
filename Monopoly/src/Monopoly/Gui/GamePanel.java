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

public class GamePanel extends ImagesLoad implements MouseListener, MouseMotionListener {

	private int dimXFrame;
	private int dimYFrame;
	private int piecesSize = 45;

	private int deltaX = 105;
	private int deltaY = 81;
	// SOUTH
	private int xPosUpS = 1120;
	private int xPosDownS = 1170;
	private int yPosUpS = 890;
	private int yPosDownS = 930;
	// WEST
	private int xPosUpW = 10;
	private int xPosDownW = 60;
	private int yPosUpW = 775;
	private int yPosDownW = 820;
	// NORTH
	private int xPosUpN = 175;
	private int xPosDownN = 225;
	private int yPosUpN = 5;
	private int yPosDownN = 55;
	// EAST
	private int xPosUpE = 1160;
	private int xPosDownE = 1210;
	private int yPosUpE = 125;
	private int yPosDownE = 170;

	// TODO codigo para testes
	PlayerSymbol dog = new PlayerSymbol(1, "Dog", dogPiece);
	Board boardsdcdc = new Board();
	Vector<Player> players = new Vector<Player>();
	// fim do codigo para testes
	
	private int position = 0;

	public GamePanel() {
		super();
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		players = new Vector<Player>();
		players.add(new Player("Pedro", dog, 10000, boardsdcdc.getBoardBox(0)));
		players.get(0).setPosition(xPosUpS, yPosUpS);// TODO importante definir
														// está posição ao criar
														// o jogador 1
		players.add(new Player("Faby", dog, 10000, boardsdcdc.getBoardBox(0)));
		players.get(1).setPosition(xPosUpS, yPosDownS);// TODO importante
														// definir está posição
														// ao criar o jogador 2
		players.add(new Player("Andre", dog, 10000, boardsdcdc.getBoardBox(0)));
		players.get(2).setPosition(xPosDownS, yPosUpS);// TODO importante
														// definir está posição
														// ao criar o jogador 3
		players.add(new Player("Paulo", dog, 10000, boardsdcdc.getBoardBox(0)));
		players.get(3).setPosition(xPosDownS, yPosDownS);// TODO importante
															// definir está
															// posição ao criar
															// o jogador 4
	}

	public GamePanel(Vector<Player> players) {
		super();
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.players = players;
	}
	public void teste(Vector<Player> players){
		this.players = players;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.white);
		g.drawImage(board, 0, 0, this.getWidth(), this.getHeight(), 0, 0, board.getWidth(), board.getHeight(), null);

		int x = 0;
		int y = 0;
		for (Player p : players)
			draw(g, p, p.getPosition().getX(), p.getPosition().getY());
	}

	public void draw(Graphics g, Player player, int x, int y) {
		g.drawImage(player.getSymbol().getPiece(), x, y, x + piecesSize, y + piecesSize, 0, 0,
				player.getSymbol().getPiece().getWidth(), player.getSymbol().getPiece().getHeight(), null);
	}

	public void update(Player p) { //pode ser colocada no player
		int x = p.getPosition().getX();
		int y = p.getPosition().getY();
		// SOUTH
		if (p.getValuePosition() >= 0 && p.getValuePosition() < 10)
			x -= deltaX;
		// WEST
		if (p.getValuePosition() == 10) {
			if (players.indexOf(p) == 0) {
				x = xPosUpW;
				y = yPosUpW;
			}
			if (players.indexOf(p) == 1) {
				x = xPosUpW;
				y = yPosDownW;
			}
			if (players.indexOf(p) == 2) {
				x = xPosDownW;
				y = yPosUpW;
			}
			if (players.indexOf(p) == 3) {
				x = xPosDownW;
				y = yPosDownW;
			}
		}
		if (p.getValuePosition() > 10 && p.getValuePosition() < 20)
			y -= deltaY;
		// NORTH
		if (p.getValuePosition() == 20) {
			if (players.indexOf(p) == 0) {
				x = xPosUpN;
				y = yPosUpN;
			}
			if (players.indexOf(p) == 1) {
				x = xPosUpN;
				y = yPosDownN;
			}
			if (players.indexOf(p) == 2) {
				x = xPosDownN;
				y = yPosUpN;
			}
			if (players.indexOf(p) == 3) {
				x = xPosDownN;
				y = yPosDownN;
			}
		}
		if (p.getValuePosition() > 20 && p.getValuePosition() < 30)
			x += deltaX;
		// EAST
		if (p.getValuePosition() == 30) {
			if (players.indexOf(p) == 0) {
				x = xPosUpE;
				y = yPosUpE;
			}
			if (players.indexOf(p) == 1) {
				x = xPosUpE;
				y = yPosDownE;
			}
			if (players.indexOf(p) == 2) {
				x = xPosDownE;
				y = yPosUpE;
			}
			if (players.indexOf(p) == 3) {
				x = xPosDownE;
				y = yPosDownE;
			}
		}
		if (p.getValuePosition() > 30 && p.getValuePosition() < 39)
			y += deltaY;

		if (p.getValuePosition() == 39) {
			if (players.indexOf(p) == 0) {
				x = xPosUpS;
				y = yPosUpS;
			}
			if (players.indexOf(p) == 1) {
				x = xPosUpS;
				y = yPosDownS;
			}
			if (players.indexOf(p) == 2) {
				x = xPosDownS;
				y = yPosUpS;
			}
			if (players.indexOf(p) == 3) {
				x = xPosDownS;
				y = yPosDownS;
			}
			p.setValuePosition(0);
		} else
			p.setValuePosition(p.getValuePosition() + 1); // TODO mudar tambem a
															// boardBox

		p.setPosition(x, y);
		repaint();
		System.out.println("x -> " + x + " y -> " + y);
		System.out.println("valeu position : " + p.getValuePosition());
	}

	// APAGAR APOS TESTE
	@Override
	public void mousePressed(MouseEvent arg0) {
		// System.out.println("x -> " + e.getX());
		// System.out.println("y -> " + e.getY());
		update(players.get(0));
		update(players.get(1));
		update(players.get(2));
		update(players.get(3));
		requestFocus();
	}
	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

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
}
