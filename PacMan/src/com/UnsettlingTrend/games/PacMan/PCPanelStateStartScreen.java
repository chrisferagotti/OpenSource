package com.UnsettlingTrend.games.PacMan;
import java.awt.Color;
import java.awt.Graphics;

import com.UnsettlingTrend.games.PacMan.Ghosts.Ghost;


public class PCPanelStateStartScreen implements PCPanelState {
	
        Ghost B =new Ghost(0);
        Ghost I = new Ghost(1);
        Ghost P = new Ghost(2);
        Ghost C = new Ghost(3);
	
	
	public void Update(PCPanel panel, Graphics graphics) {
		
                panel.Board.setLives(2);
		panel.Board.ResetScore();
		
		if (panel.getKeyPressed() != 0)
                {
			panel.setPanelStart();
			panel.setKeyPressed(0);
		}
		else
		{		
			// Draw The Black Background
			graphics.setColor(Color.BLACK);
			graphics.fillRect(0, 0, 580, 730);
                        graphics.setColor(Color.YELLOW);
                        graphics.drawString("PacMan", 280, 100);
                       
                        B.setLocation(260, 140);
                        B.Draw(graphics);
                        graphics.setColor(Color.RED);
                        graphics.drawString("Blinky", 300, 150);
                        
                        I.setLocation(260, 180);
                        I.Draw(graphics);
                        graphics.setColor(Color.CYAN);
                        graphics.drawString("Inky", 300, 190);
                        
                        P.setLocation(260, 220);
                        P.Draw(graphics);
                        graphics.setColor(Color.PINK);
                        graphics.drawString("Pinky", 300, 230);
                        
                        C.setLocation(260, 260);
                        C.Draw(graphics);
                        graphics.setColor(Color.ORANGE);
                        graphics.drawString("Clyde", 300, 270);
                        
                        graphics.setColor(Color.WHITE);
                        graphics.fillOval(250, 320, 4, 4);
                        graphics.drawString("10 Points", 280, 325);
                                
                        graphics.fillOval(250, 340, 10, 10);
                        graphics.drawString("50 Points", 280, 345);
                        
                        graphics.setColor(Color.WHITE);
                        graphics.drawString("Press any key to begin...", 230, 500);
			
			

			
		}
	}

	public String getState() {
		
		return "StartScreen";
	}

}
