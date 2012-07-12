package com.UnsettlingTrend.games.PacMan.Ghosts;

import com.UnsettlingTrend.games.PacMan.PCPanel;

public class GhostScatter implements GhostState{
    public String getState() {
		return "running";
	}
    public void Update(Ghost PC, PCPanel panel)
	{	int x, y, x1, y1;
		int direction = PC.getDirection();
    }
}
