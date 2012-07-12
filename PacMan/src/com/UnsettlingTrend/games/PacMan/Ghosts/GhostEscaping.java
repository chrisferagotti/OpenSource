package com.UnsettlingTrend.games.PacMan.Ghosts;
import java.util.Random;

import com.UnsettlingTrend.games.PacMan.PCPanel;

public class GhostEscaping implements GhostState{
	public String getState() {
		return "escaping";
	}

	public void Update(Ghost ghost, PCPanel panel)
        {
            int x = ghost.getLocation().x;
            int y = ghost.getLocation().y;
            int direction = ghost.getDirection();


            if(x == 290 && y == 270){
                ghost.setDirection(90);
                ghost.setLocation(x + panel.getSpacesToMove(), y);
                ghost.setChasing();
            }
            
            else{
                ghost.setLocation(x,y - panel.getSpacesToMove());
                ghost.setDirection(direction);
            }
            
            
        }
}