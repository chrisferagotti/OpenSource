package com.UnsettlingTrend.games.PacMan;
import java.awt.Color;
import java.awt.Graphics;


public class PCPanelStateGameInPlay implements PCPanelState {

	public void Update(PCPanel panel, Graphics graphics) {
		panel.Board.setBoardColor(Color.BLUE);
                
		if (panel.AllPellets.HowManyPelletsLeft() == 0)
		{	
			panel.ResetBoard();
			panel.Board.IncrementLevel();
			panel.setPanelStart();
		}
		
		else if (panel.PC.getKeyPressed() == 80)
		{
			panel.PC.setKeyPressed(0);
			panel.setPanelPaused();
		}
		else
		{	int HowManyPellets = 0;
			int HowManyPowerPellets = 0;
		
			panel.Board.DrawBoard(graphics);
			panel.AllPellets.DrawAllPellets(graphics);
			panel.Inky.Update(panel);
			panel.Inky.Draw(graphics);
			panel.Pinky.Update(panel);
			panel.Pinky.Draw(graphics);
			panel.Blinky.Update(panel);
			panel.Blinky.Draw(graphics);
			panel.Clyde.Update(panel);
			panel.Clyde.Draw(graphics);
			panel.PC.Update();
			panel.PC.Draw(graphics);
		
			if (panel.AllPellets.IsThereAPowerPellet(panel.PC.getLocation()))
			{
				panel.Board.AddToScore(panel.AllPellets.HowManyPelletsAtPoint(panel.PC.getLocation()) * 10);
				panel.Board.AddToScore(panel.AllPellets.HowManyPowerPelletsAtPoint(panel.PC.getLocation()) * 50);
				
				panel.setPanelPowerPelletEaten();
				panel.Blinky.setRunning();
				panel.Inky.setRunning();
				panel.Pinky.setRunning();
				panel.Clyde.setRunning();
				
			}
			
			else
			{
				// This is the collision check for Pellets
				panel.Board.AddToScore(panel.AllPellets.HowManyPelletsAtPoint(panel.PC.getLocation()) * 10);
				panel.Board.AddToScore(panel.AllPellets.HowManyPowerPelletsAtPoint(panel.PC.getLocation()) * 50);
			
				// This is the collision check for Ghosts
				if (panel.IsGhostCollision(panel.Blinky, panel.PC) && panel.Blinky.getState() == "chasing")
					panel.setPanelPacManDying();
				else if (panel.IsGhostCollision(panel.Pinky, panel.PC) && panel.Pinky.getState() == "chasing")
					panel.setPanelPacManDying();
				else if (panel.IsGhostCollision(panel.Inky, panel.PC) && panel.Inky.getState() == "chasing")
					panel.setPanelPacManDying();
				else if (panel.IsGhostCollision(panel.Clyde, panel.PC) && panel.Clyde.getState() == "chasing")
					panel.setPanelPacManDying();
				
				if (panel.getState() == "PacManDying")
					panel.Board.SubtractLife();
			}
		}
		
	}


	
	public String getState() {
		return "GameInPlay";	
	}

}
