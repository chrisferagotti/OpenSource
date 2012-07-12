package com.UnsettlingTrend.games.PacMan;
import java.awt.Color;
import java.awt.Graphics;


public class PCPanelStatePacManDying implements PCPanelState{
	
	int Counter = 0;
	
	public void Update(PCPanel panel, Graphics graphics) {
		panel.setKeyPressed(0);
                
		
		if (Counter < 50)
		{
			panel.PC.setPacManDead();
			panel.PC.Update();
			panel.Board.DrawBoard(graphics);
			panel.AllPellets.DrawAllPellets(graphics);
			panel.Inky.Draw(graphics);
			panel.Pinky.Draw(graphics);
			panel.Blinky.Draw(graphics);
			panel.Clyde.Draw(graphics);
			panel.PC.Draw(graphics);
			
			Counter++;
		}
		
		else
		{	Counter = 0;
			
			if (panel.Board.getLives() < 0)
				panel.setPanelGameOver();	
			else
			{
				panel.Blinky.setLocation(290, 270);
				panel.Blinky.setChasing();
				panel.Inky.setLocation(250, 330);
				panel.Inky.setWaiting();
				panel.Inky.setDirection(0);
				panel.Pinky.setLocation(290, 330);
				panel.Pinky.setWaiting();
				panel.Pinky.setDirection(0);
				panel.Clyde.setLocation(330, 330);
				panel.Clyde.setWaiting();
				panel.Clyde.setDirection(0);
				panel.PC.setLocation(290, 510);
				panel.PC.setDirection(180);
				panel.PC.setMouthAngle(0);
				panel.PC.setPacManChomping();
				panel.setPanelGameInPlay();
			}
		}
		
	}

	public String getState() {
		
		return "PacManDying";
	}

}
