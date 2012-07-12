package com.UnsettlingTrend.games.PacMan.PacMan;

public class PacManStateStart implements PacManState{

	public String getState() {
		return "pcStart";
	}

	public void Update(PacMan PC)
	{
		PC.setMouthAngle(0);				
	}
}
