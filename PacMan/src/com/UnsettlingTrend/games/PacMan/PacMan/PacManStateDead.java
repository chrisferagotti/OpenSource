package com.UnsettlingTrend.games.PacMan.PacMan;

public class PacManStateDead implements PacManState {

	public String getState() {
		return "pcDead";
	}

	public void Update(PacMan PC)
	{		if (PC.getMouthAngle() < 360)
			PC.setMouthAngle(PC.getMouthAngle() + 10);

	}
}
