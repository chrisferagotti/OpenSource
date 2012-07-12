package com.UnsettlingTrend.games.PacMan;
import java.awt.Color;
import java.awt.Graphics;

import com.UnsettlingTrend.games.PacMan.Ghosts.Ghost;


public class PCPanelStateGhostEaten implements PCPanelState{
	
	private Integer Counter = 0;
	private Ghost G;
	private Integer GhostsEaten;
	
	
	PCPanelStateGhostEaten(Integer ge, Ghost g)
	{
		GhostsEaten = ge;
		G = g;
	}
	
	public void Update(PCPanel panel, Graphics graphics) 
	{	
		Double GhostScore = Math.pow(2, GhostsEaten.doubleValue()) * 100;
		Integer GS = new Integer(GhostScore.intValue());
		
		if (Counter < 20)
		{	Counter++;
			graphics.setColor(Color.WHITE);
			graphics.drawString(GS.toString(), G.getLocation().x - 15, G.getLocation().y - 7);
		}
		else
		{	panel.Board.AddToScore(GS);
			Counter = 0;
			panel.setPanelPowerPelletEaten();
		}
	}

	public String getState() {
		
		return "GhostEaten";
	}

}
