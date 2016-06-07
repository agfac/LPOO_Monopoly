package Monopoly.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import Monopoly.Logic.Game;

public class GraphicsWindow extends ImagesLoad {

	public JFrame frame;
	private GamePanel panel;
	private InfoPanel panel_1;
	Game game;

	/**
	 * Create the application.
	 */
	public GraphicsWindow(Game game) {
		this.game = game;
		initialize();
	}

	public void gameStatus() {
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

		System.out.println("x ini-> " + (int) 2 * (frame.getWidth() / 3));
		panel_1.setBounds((int) 2 * (frame.getWidth() / 3), 0, (int) (frame.getWidth() / 3), frame.getHeight() - 40);
		panel_1.setBorder(BorderFactory.createLineBorder(Color.black));
		frame.getContentPane().add(panel_1, BorderLayout.EAST);

		frame.pack();

	}

}
