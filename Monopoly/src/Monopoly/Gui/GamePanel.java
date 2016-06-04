package Monopoly.Gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

public class GamePanel extends ImagesLoad 
implements MouseListener, MouseMotionListener {
	
	private int dimXFrame;
	private int dimYFrame;

	public GamePanel() {
		super();

		this.addMouseListener(this);
		this.addMouseMotionListener(this);
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
					
	}
	
	
	
	
	
	
	
	//APAGAR APOS TESTE
	@Override
	public void mouseDragged(MouseEvent e) {
			System.out.println("x -> " + e.getX());		
			System.out.println("y -> " + e.getY());	
		requestFocus();		
	}
	
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
