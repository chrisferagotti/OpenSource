package com.UnsettlingTrend.games.PacMan;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;


public class PCFrame extends JFrame implements KeyListener{
	
	private PCPanel PacManPanel = new PCPanel();
	
	public static void main(String[] args)
	{
		PCFrame PacManGame = new PCFrame("PacMan");
		PacManGame.setVisible(true);
	}
	
	protected PCFrame(String Title)
	{
		super(Title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(580, 730);
		Container ContentPane = getContentPane();
		
		//ContentPane.add(new PCPanel());
		ContentPane.add(PacManPanel);	
		
		// KeyListener Stuff
		addKeyListener(this);	
	}

	public void keyPressed(KeyEvent e) 
	{
		PacManPanel.setKeyPressed(e.getKeyCode());
	}

	public void keyReleased(KeyEvent e) {
		//PacManPanel.setKeyPressed(0);
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub	
	}	

}
