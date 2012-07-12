package com.UnsettlingTrend.games.PacMan;
import java.awt.Color;
import java.awt.Graphics;


public class PCPanelStatePowerPelletEaten implements PCPanelState {
	
	private Integer Counter = 0;
	private Integer GhostsEaten = 0;
	
	public void Update(PCPanel panel, Graphics graphics) {
		panel.Board.setBoardColor(Color.WHITE);
		
		if (Counter < 140)
		{	Counter++;
		
			if (panel.AllPellets.HowManyPelletsLeft() == 0)
			{	panel.ResetBoard();
				panel.Board.IncrementLevel();
				panel.setPanelStart();
			}
		
			else if (panel.PC.getKeyPressed() == 80)
			{
				panel.PC.setKeyPressed(0);
				panel.setPanelPaused();
			}
			
			else
			{	
				// This is the collision check for Ghosts
				if (panel.IsGhostCollision(panel.Blinky, panel.PC))
				{	if (panel.Blinky.getState() == "running")
					{	GhostsEaten++;
						panel.Blinky.setDead();
						panel.setPanelGhostEaten(GhostsEaten, panel.Blinky);
					}
					else if (panel.Blinky.getState() == "chasing")
					{	panel.setPanelPacManDying();
						panel.PC.setPacManDead();
                                                panel.Board.SubtractLife();
					}
				}
				if (panel.IsGhostCollision(panel.Inky, panel.PC))
				{	if (panel.Inky.getState() == "running")
					{	GhostsEaten++;
						panel.Inky.setDead();
						panel.setPanelGhostEaten(GhostsEaten, panel.Inky);
					}
					else if (panel.Inky.getState() == "chasing")
					{	panel.setPanelPacManDying();
						panel.PC.setPacManDead();
                                                panel.Board.SubtractLife();
					}
				}
				if (panel.IsGhostCollision(panel.Pinky, panel.PC))
				{	if (panel.Pinky.getState() == "running")
					{	GhostsEaten++;
						panel.Pinky.setDead();
						panel.setPanelGhostEaten(GhostsEaten, panel.Pinky);
					}
					else if (panel.Pinky.getState() == "chasing")
					{	panel.setPanelPacManDying();
						panel.PC.setPacManDead();
                                                panel.Board.SubtractLife();
					}
				}
				if (panel.IsGhostCollision(panel.Clyde, panel.PC))
				{	if (panel.Clyde.getState() == "running")
					{	GhostsEaten++;
						panel.Clyde.setDead();
						panel.setPanelGhostEaten(GhostsEaten, panel.Clyde);
					}
					else if (panel.Clyde.getState() == "chasing")
					{	panel.setPanelPacManDying();
						panel.PC.setPacManDead();
                                                panel.Board.SubtractLife();
					}
				}
			
				int HowManyPellets = 0;
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
		
				// This is the collision check for Pellets
				panel.Board.AddToScore(panel.AllPellets.HowManyPelletsAtPoint(panel.PC.getLocation()) * 10);
				panel.Board.AddToScore(panel.AllPellets.HowManyPowerPelletsAtPoint(panel.PC.getLocation()) * 50);
			}		
		
		}
		
		else 
		{	panel.Board.setBoardColor(Color.BLUE);
			
			if (panel.Blinky.getState() == "running")
				panel.Blinky.setChasing();
			if (panel.Inky.getState() == "running")
				panel.Inky.setChasing();
			if (panel.Pinky.getState() == "running")
				panel.Pinky.setChasing();
			if (panel.Clyde.getState() == "running")
				panel.Clyde.setChasing();
			
		
			panel.setPanelGameInPlay();
			Counter = 0;
			GhostsEaten = 0;
		}
		
	}
		


	public String getState() {
		
		return "PowerPelletEaten";
	}

}
