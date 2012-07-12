package com.UnsettlingTrend.games.PacMan;

import java.awt.Color;
import java.awt.Graphics;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PCPanelStateHighScores implements PCPanelState {

	private Scores scoresDB = new Scores();
        private boolean firstRunOfUpdate = true;
        private Vector highScores;
        private int counter = 0;
        public void Update(PCPanel panel, Graphics graphics) 
        {
		
            //if(firstRunOfUpdate == true)
            //{
              //  firstRunOfUpdate = false;
                
            
               try {
                    highScores = scoresDB.GetTopScores(10);
                } catch (SQLException ex) {
                    Logger.getLogger(PCPanelStateHighScores.class.getName()).log(Level.SEVERE, null, ex);
                }
            //}
            if(counter > 100)
               {
                if (panel.PC.getKeyPressed() != 0)
                {
                    panel.PC.setKeyPressed(0);
                    counter = 0;
                    panel.setPanelStartScreen();
                    panel.AddAllPellets();
                }
               }
            else
                panel.PC.setKeyPressed(0);
               
            
            graphics.setColor(Color.BLACK);
            graphics.fillRect(0, 0, 580, 730);
            graphics.setColor(Color.WHITE);
            graphics.drawString("High Scores", 265, 30);
            
            for(int i = 0; i < highScores.size();i++)
            {
                graphics.drawString(((Score)highScores.elementAt(i)).Name(), 125, 60 + i * 20);
                graphics.drawString(((Score)highScores.elementAt(i)).Score().toString(), 383, 60 + i * 20);
            }
            counter++;

        }
	public String getState() {
		return "HighScores";	
	}

}