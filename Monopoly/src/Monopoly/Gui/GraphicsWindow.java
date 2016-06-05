package Monopoly.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Monopoly.Logic.Board;
import Monopoly.Logic.Game;
import Monopoly.Logic.Player;
import Monopoly.Logic.PlayerSymbol;


public class GraphicsWindow extends ImagesLoad {
	
	private JFrame frame;
	private GamePanel panel;
	private InfoPanel panel_1 ;
	// TODO codigo para testes
	PlayerSymbol dog = new PlayerSymbol(1, "Dog", dogPiece);
	Board boardsdcdc = new Board();
	Vector<Player> players;
	Game game;
	// fim do codigo para testes

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraphicsWindow window = new GraphicsWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GraphicsWindow() {
		players = new Vector<Player>();
		players.add(new Player("Pedro", dog, 10000, boardsdcdc.getBoardBox(0), 1));
		players.get(0).setPosition(1120, 890);
//		players.add(new Player("Faby", dog, 10000, boardsdcdc.getBoardBox(0), 2));
//		players.get(1).setPosition(1120, 930);
//		players.add(new Player("Andre", dog, 10000, boardsdcdc.getBoardBox(0), 3));
//		players.get(2).setPosition(1170, 890);
//		players.add(new Player("Paulo", dog, 10000, boardsdcdc.getBoardBox(0), 4));
//		players.get(3).setPosition(1170, 930);
		game = new Game(players);
		initialize();
	}

	
	public void gameStatus(){
		panel.repaint();
		panel_1.repaint();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		frame = new JFrame();
		frame.setTitle("Monopoly Game");
		frame.setPreferredSize(new Dimension(dim.width, dim.height - 50));
		frame.setBounds(0, 0, dim.width, dim.height - 50);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		panel = new GamePanel(game);
		panel.setBounds(0, 0, (int) 2 * (frame.getWidth() / 3), frame.getHeight() - 40);
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		frame.getContentPane().add(panel, BorderLayout.WEST);

		panel_1 = new InfoPanel(game);
		panel_1.setBounds((int) 2 * (frame.getWidth() / 3), 0, (int) (frame.getWidth() / 3), frame.getHeight() - 40);
		panel_1.setBorder(BorderFactory.createLineBorder(Color.black));
		frame.getContentPane().add(panel_1, BorderLayout.EAST);

		frame.pack();

	}

}
