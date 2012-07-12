package com.UnsettlingTrend.games.PacMan;
import java.awt.Color;
import java.awt.Graphics;
import java.text.AttributedCharacterIterator;
import java.util.Map;
import java.util.Set;


public class PCPanelStatePaused implements PCPanelState {
	
	public void Update(PCPanel panel, Graphics graphics)
	{	if (panel.PC.getKeyPressed() == 80)
		{	panel.PC.setKeyPressed(0);
			panel.setPanelGameInPlay();		
		}
		
		else
		{	// Draw all elements of the board
			panel.Board.DrawBoard(graphics);
			panel.AllPellets.DrawAllPellets(graphics);
			panel.Inky.Draw(graphics);
			panel.Pinky.Draw(graphics);
			panel.Blinky.Draw(graphics);
			panel.Clyde.Draw(graphics);
			panel.PC.Draw(graphics);
		
			// Paused message
			graphics.setColor(Color.BLACK);
			graphics.fillRect(220, 375, 140, 30);
			graphics.setColor(Color.WHITE);
			
			graphics.drawString("Game Paused", 250, 395);
		}
		
	}

	public String getState() {
		
		return "Paused";
	}

}
