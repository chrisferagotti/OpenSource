package com.UnsettlingTrend.games.PacMan.Ghosts;
import java.util.Random;

import com.UnsettlingTrend.games.PacMan.PCPanel;

public class GhostWaiting implements GhostState{
	public String getState() {
		return "waiting";
	}

	public void Update(Ghost ghost, PCPanel panel)
        {
            int x = ghost.getLocation().x;
            int y = ghost.getLocation().y;
            int direction = ghost.getDirection();
            int xmove = 0;
            int ymove = 0;

            if(x == 290 && y == 330){
                //escape!
                //needs to be a timer or queue here instead of just hitting the point
                ghost.setDirection(90);
                ghost.setEscaping();
            }
            
            else {
                if(direction == 0){
                    xmove = panel.getSpacesToMove();
                }
                else if(direction == 180){
                    xmove = -panel.getSpacesToMove();
                }
            
                while(((ghost.Board.IsLegalGhostJailMove(x + xmove,y)) == false)){
                    direction = direction + 180 % 360;
                    xmove = -xmove;

                }
            }
            ghost.setLocation(x + xmove,y + ymove);
            ghost.setDirection(direction);
            
            
        }
}