package com.UnsettlingTrend.games.PacMan.PacMan;

public class PacManStateChomping implements PacManState{

	public String getState() {
		return "pcChomping";
	}

	public void Update(PacMan PC)
	{	int x, y, x1 = PC.getLocation().x, y1 = PC.getLocation().y;
		int direction = PC.getDirection();
		
		// Find where User wants to go next
		switch (PC.getKeyPressed()) {
			case 37: x = PC.getLocation().x - PC.getSpacesToMove(); y = PC.getLocation().y; direction = 180; 	break;
			case 38: x = PC.getLocation().x; y = PC.getLocation().y - PC.getSpacesToMove(); direction = 90; 	break;
			case 39: x = PC.getLocation().x + PC.getSpacesToMove(); y = PC.getLocation().y; direction = 0; 	break;
			case 40: x = PC.getLocation().x; y = PC.getLocation().y + PC.getSpacesToMove(); direction = 270; 	break;
			default: x = 0; y = 0;
		}
		
		// Find where PacMan wants to go next
		switch (PC.getDirection()) {
			case 180: 	if ((x1 == 0) && (y1 == 330))
							x1 = 580;
					  	else
					  		x1 -= PC.getSpacesToMove();		break;
			case 90:  		y1 -= PC.getSpacesToMove();  	break;
			case 0:   	if ((x1 == 580) && (y1 == 330))
							x1 = 0;
						else
							x1 += PC.getSpacesToMove(); 	break;
			case 270:  		y1 += PC.getSpacesToMove(); 	break;

		}
		
		// Test if User is Legal Move, and act accordingly
		if (PC.Board.IsLegalMove(x, y))
		{	PC.setLocation(x, y);
			PC.setDirection(direction);
			if (PC.getMouthAngle() >= 90)
				PC.setIsOpening(false);
			else if (PC.getMouthAngle() <= 0)
				PC.setIsOpening(true);
		
			if (PC.getIsOpening()) PC.setMouthAngle(PC.getMouthAngle() + 10);
			else 	PC.setMouthAngle(PC.getMouthAngle() - 10);
		} 	
		// Test if PacMan is a Legal Move, and act accordingly
		else if (PC.Board.IsLegalMove(x1, y1))
		{	PC.setLocation(x1, y1);
			if (PC.getMouthAngle() >= 90)
			PC.setIsOpening(false);
			else if (PC.getMouthAngle() <= 0)
			PC.setIsOpening(true);
	
			if (PC.getIsOpening()) PC.setMouthAngle(PC.getMouthAngle() + 10);
			else 	PC.setMouthAngle(PC.getMouthAngle() - 10);
		}
		
		// If Neither are legal, reset KeyPressed and stop PacMan
		else 
			{ 
			PC.setKeyPressed(0);
			PC.setPacManStopped();			
			}
				
	}
}
