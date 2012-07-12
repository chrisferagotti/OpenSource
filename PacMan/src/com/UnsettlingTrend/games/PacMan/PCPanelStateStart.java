package com.UnsettlingTrend.games.PacMan;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


public class PCPanelStateStart implements PCPanelState {
	
	private int Counter = 0;
	
	public void Update(PCPanel panel, Graphics graphics) {
		
		if (Counter < 100)
		{	
			Counter++;
			panel.Board.DrawBoard(graphics);
			panel.AllPellets.DrawAllPellets(graphics);
			
			panel.Blinky.setChasing();
			panel.Blinky.setLocation(290, 270);
			panel.Blinky.setDirection(0);
			panel.Blinky.Draw(graphics);
			
			panel.Inky.setWaiting();
			panel.Inky.setLocation(250, 330);
			panel.Inky.setDirection(0);
			panel.Inky.Draw(graphics);
			
			panel.Pinky.setWaiting();
			panel.Pinky.setLocation(290, 330);
			panel.Pinky.setDirection(0);
			panel.Pinky.Draw(graphics);

			panel.Clyde.setWaiting();
			panel.Clyde.setLocation(330, 330);
			panel.Clyde.setDirection(0);
			panel.Clyde.Draw(graphics);
			
			panel.PC.setPacManStart();
			panel.PC.setLocation(290, 510);
			panel.PC.setDirection(180);
			panel.PC.Update();
			panel.PC.Draw(graphics);
			graphics.setColor(Color.WHITE);
			graphics.drawString("Level "+ panel.Board.getLevel().toString(), 265, 395);
		}
		else
		{
			panel.PC.setPacManChomping();
			panel.setPanelGameInPlay();
			Counter = 0;
		}
		

		
	}

	public String getState() {
		
		return "Start";
	}

}
