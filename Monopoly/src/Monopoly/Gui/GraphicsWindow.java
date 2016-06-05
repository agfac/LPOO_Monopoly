package Monopoly.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Monopoly.Logic.Board;
import Monopoly.Logic.Player;
import Monopoly.Logic.PlayerSymbol;

public class GraphicsWindow extends ImagesLoad{

	private JFrame frame;

	// TODO codigo para testes
	PlayerSymbol dog = new PlayerSymbol(1, "Dog", dogPiece);
	Board boardsdcdc = new Board();
	Vector<Player> players;
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
		players.add(new Player("Pedro", dog, 10000, boardsdcdc.getBoardBox(0)));
		players.get(0).setPosition(1120, 890);
//		players.add(new Player("Faby", dog, 10000, boardsdcdc.getBoardBox(0)));
//		players.get(1).setPosition(1120, 930);
//		players.add(new Player("Andre", dog, 10000, boardsdcdc.getBoardBox(0)));
//		players.get(2).setPosition(1170, 890);
//		players.add(new Player("Paulo", dog, 10000, boardsdcdc.getBoardBox(0)));
//		players.get(3).setPosition(1170, 930);
		initialize();
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

		GamePanel panel = new GamePanel(players);
		panel.setBounds(0, 0, (int) 2 * (frame.getWidth() / 3), frame.getHeight() - 40);
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		frame.getContentPane().add(panel, BorderLayout.WEST);

		InfoPanel panel_1 = new InfoPanel(players);
		panel_1.setBounds((int) 2 * (frame.getWidth() / 3), 0, (int) (frame.getWidth() / 3), frame.getHeight() - 40);
		panel_1.setBorder(BorderFactory.createLineBorder(Color.black));
		frame.getContentPane().add(panel_1, BorderLayout.EAST);

		frame.pack();

		panel.requestFocus();

	}

}
