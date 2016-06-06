package Monopoly.Gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.Timer;

import Monopoly.Logic.Board;
import Monopoly.Logic.BoardBox;
import Monopoly.Logic.Game;
import Monopoly.Logic.Player;
import Monopoly.Logic.PlayerSymbol;

public class GamePanel extends ImagesLoad implements MouseListener, KeyListener, ActionListener {
	Timer timer = new Timer(200, this);
	private int piecesSize = 45;
	
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
		g.drawImage(board, 0, 0, this.getWidth(), this.getHeight(), 0, 0, board.getWidth(), board.getHeight(), null);

		for (Player p : game.getPlayers())
			draw(g, p, p.getPosition().getX(), p.getPosition().getY());

	}

	public void draw(Graphics g, Player player, int x, int y) {
		g.drawImage(player.getSymbol().getPiece(), x, y, x + piecesSize, y + piecesSize, 0, 0,
				player.getSymbol().getPiece().getWidth(), player.getSymbol().getPiece().getHeight(), null);
	}

	public void actionPerformed(ActionEvent ev) {
		if (ev.getSource() == timer) {
			for (Player p : game.getPlayers()) {
				if (p.getCellsToMove() != 0) { // && !p.getInJail()
					// for (int i = 0; i < p.getCellsToMove(); i++) {
					p.updateGUIPosition();
					p.setCellsToMove(p.getCellsToMove() - 1);
					System.out.println("p.getCellsToMove()" + p.getCellsToMove());
					repaint();
					// }
				} else{
					// game.updateGame(p);
					//fazer pequena pausa
					game.verificarSeTemPlayers(p);
					game.buyProperty(p);
					continue;
				}
			}

		}
	}

	// APAGAR APOS TESTE
	@Override
	public void mousePressed(MouseEvent e) {
		// System.out.println("x -> " + e.getX());
		// System.out.println("y -> " + e.getY());
		// for (Player p : game.getPlayers()) {
		//
		// game.updateGame(p);
		// }
		 game.updateGame(game.getPlayers().get(0));
//		game.secuenciaDoJogo(game.getPlayers().get(0));
		repaint();
		requestFocus();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		 game.updateGame(game.getPlayers().get(1));

//		game.secuenciaDoJogo(game.getPlayers().get(1));
		repaint();
		requestFocus();
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
