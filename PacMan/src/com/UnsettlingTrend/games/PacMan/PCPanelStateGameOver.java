package com.UnsettlingTrend.games.PacMan;
import java.awt.Color;
import java.awt.Graphics;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PCPanelStateGameOver implements PCPanelState{
        
        private int Counter = 0;
        private Scores HighScoresDatabase = new Scores();
        
	public void Update(final PCPanel panel, Graphics graphics) {
                
            if(Counter < 100)
            {
                Counter++;
                
                panel.Board.DrawBoard(graphics);
		panel.AllPellets.DrawAllPellets(graphics);
		panel.Blinky.Draw(graphics);
		panel.Pinky.Draw(graphics);
		panel.Inky.Draw(graphics);
		panel.Clyde.Draw(graphics);
		
		graphics.setColor(Color.RED);
		graphics.drawString("Game Over", 255, 395);   
            }
            
            else
            {   
                try {
                    if (HighScoresDatabase.IsTopTenScore(panel.Board.getScore()))
                    {   // ADD THIS CODE TO LAUNCH SCORE TEXT BOX ***
                        
                            java.awt.EventQueue.invokeLater(new Runnable() {
                            public void run() {
                            try {
                                new ScoreTextBox(panel.Board.getScore(), panel).setVisible(true);
                            } catch (SQLException ex) {
                                Logger.getLogger(ScoreTextBox.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(ScoreTextBox.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (InstantiationException ex) {
                                Logger.getLogger(ScoreTextBox.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IllegalAccessException ex) {
                                Logger.getLogger(ScoreTextBox.class.getName()).log(Level.SEVERE, null, ex);
                             }
                             }
                            });          
                    }
                    panel.setPanelHighScores();
                
                } 
                catch (SQLException ex)
                {
                 Logger.getLogger(PCPanelStateGameOver.class.getName()).log(Level.SEVERE, null, ex);
                }   
            }
            Counter++;
            
      	
	}

	public String getState() {
		return "GameOver";
	}

}
