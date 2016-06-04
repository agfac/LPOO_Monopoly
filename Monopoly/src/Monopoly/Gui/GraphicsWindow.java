package Monopoly.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GraphicsWindow {

	private JFrame frame;
	protected static BufferedImage dogPiece;
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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		frame = new JFrame();
		frame.setTitle("Monopoly Game");
		frame.setPreferredSize(new Dimension(dim.width, dim.height-50));
		frame.setBounds(0, 0, dim.width, dim.height-50);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		
		GamePanel panel = new GamePanel();
		panel.setBounds(0, 0, (int) 2*(frame.getWidth()/3), frame.getHeight()-40);
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		frame.getContentPane().add(panel, BorderLayout.WEST);
	
		
		InfoPanel panel_1 = new InfoPanel();
		panel_1.setBounds((int) 2*(frame.getWidth()/3), 0, (int) (frame.getWidth()/3), frame.getHeight()-40);
		panel_1.setBorder(BorderFactory.createLineBorder(Color.black));
		frame.getContentPane().add(panel_1, BorderLayout.EAST);

		frame.pack();
		
		panel.requestFocus();
		
	}
	
}
