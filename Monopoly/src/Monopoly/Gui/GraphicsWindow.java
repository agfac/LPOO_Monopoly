package Monopoly.Gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicsWindow {

	private JFrame frame;

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
		frame = new JFrame();
		frame.setTitle("Monopoly Game");
		frame.setBounds(100, 100, 1440, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		GamePanel panel = new GamePanel(frame.getWidth(),frame.getHeight());
		panel.setBounds(0, 0, 1080, 900);
		frame.getContentPane().add(panel);
		
		
		PlayerPanel panel_1 = new PlayerPanel(frame.getWidth(),frame.getHeight());
		panel_1.setBounds(720, 0, 1440, 900);
		frame.getContentPane().add(panel_1);
	}
}
