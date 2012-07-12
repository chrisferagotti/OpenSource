package com.UnsettlingTrend.games.PacMan;
import java.awt.Graphics;


public interface PCPanelState {
	
	public void Update(PCPanel panel, Graphics graphics);
	
	public String getState();


}
