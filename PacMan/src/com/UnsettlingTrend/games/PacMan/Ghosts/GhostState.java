package com.UnsettlingTrend.games.PacMan.Ghosts;

import com.UnsettlingTrend.games.PacMan.PCPanel;

public interface GhostState {

	public String getState();

	public void Update(Ghost PC, PCPanel panel);

}
