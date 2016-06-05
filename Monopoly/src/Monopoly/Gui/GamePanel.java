package Monopoly.Gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.Timer;

import Monopoly.Logic.Board;
import Monopoly.Logic.BoardBox;
import Monopoly.Logic.Game;
import Monopoly.Logic.Player;
import Monopoly.Logic.PlayerSymbol;

public class GamePanel extends ImagesLoad implements MouseListener, MouseMotionListener, ActionListener {
	Timer timer = new Timer(200, this);
	private int piecesSize = 45;

	Game game;

	public GamePanel(Game game) {
		super();
		this.addMouseListener(this); // TODO APAGAR
		this.addMouseMotionListener(this);// TODO APAGAR
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
			// for (Player p: game.getPlayers()){
			// System.out.println("Hello");
			// System.out.println(p.getValuePosition());
			// System.out.println(p.getPosition().getX());
			// System.out.println(p.getPosition().getY());
			// p.updateGUIPosition();
			// repaint();// this will call at every 1 second
			// }
			for (Player p : game.getPlayers()) {
				if (p.getCellsToMove() != 0) {
					//for (int i = 0; i < p.getCellsToMove(); i++) {
						p.updateGUIPosition();
						p.setCellsToMove(p.getCellsToMove() - 1);
						System.out.println("p.getCellsToMove()" + p.getCellsToMove());
						repaint();
					//}
				}
				else 
					//game.updateGame(p);
					game.buyProperty(p);
			}
			
		}
	}

	// APAGAR APOS TESTE
	@Override
	public void mousePressed(MouseEvent e) {
		// System.out.println("x -> " + e.getX());
		// System.out.println("y -> " + e.getY());
		for (Player p : game.getPlayers()) {

			game.updateGame(p);
		}
		repaint();
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
