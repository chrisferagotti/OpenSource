package com.UnsettlingTrend.games.PacMan.Ghosts;
import java.util.Random;

import com.UnsettlingTrend.games.PacMan.PCPanel;

public class GhostEntering implements GhostState{
	public String getState() {
		return "entering";
	}

	public void Update(Ghost ghost, PCPanel panel)
        {
            int x = ghost.getLocation().x;
            int y = ghost.getLocation().y;
            int direction = 270;
            ghost.setDirection(270);
            //int xmove = 0;
            //int ymove = 5;

            if((x == 290) && (y == 330)){
                ghost.setEscaping();
            }
            
            else{
               ghost.setLocation(x,y + 5);
            }
            
            
        }
}