package com.UnsettlingTrend.games.PacMan.PacMan;

public class PacManStateStopped implements PacManState{

	public String getState() {
		return "pcStopped";
	}
	
	public void Update(PacMan PC)	{	
		int x, y;
		int direction = PC.getDirection();
		
		// Find where User wants to go next
		switch (PC.getKeyPressed()) {
			case 37: x = PC.getLocation().x - PC.getSpacesToMove(); y = PC.getLocation().y; direction = 180; 	break;
			case 38: x = PC.getLocation().x; y = PC.getLocation().y - PC.getSpacesToMove(); direction = 90; 	break;
			case 39: x = PC.getLocation().x + PC.getSpacesToMove(); y = PC.getLocation().y; direction = 0; 	break;
			case 40: x = PC.getLocation().x; y = PC.getLocation().y + PC.getSpacesToMove(); direction = 270; 	break;
			default: x = 0; y = 0;
		}
		
		
		
		if (PC.Board.IsLegalMove(x, y))
		{	PC.setLocation(x, y);
			PC.setDirection(direction);
			
			PC.setPacManChomping();
		} 	
		

	}

}
