package com.UnsettlingTrend.games.PacMan.Ghosts;
import java.util.Random;

import com.UnsettlingTrend.games.PacMan.PCPanel;

public class GhostRunning implements GhostState{
	
	private int Counter = 0;
	
	public String getState() {
		return "running";
	}

	public void Update(Ghost ghost, PCPanel panel)
        {	
			if (Counter < 2)
			{
				int x = ghost.getLocation().x;
	            int y = ghost.getLocation().y;
	            int direction = ghost.getDirection();
                int move[] = {0,0};
	            int ms;
	            // Fix SpacesToMove
	            ms = panel.getSpacesToMove();
	            int tmpdirection = direction;
	            int nummoves = 0;
	            int PM_x = panel.PC.getLocation().x;
	            int PM_y = panel.PC.getLocation().y;
	            int priority[] = {-1,-1,-1,-1};

                setDir(direction, move, ms);
	
	            nummoves = ghost.getLegalMoves(ghost, x, y, move[1]);
	            if((nummoves == 0) || (nummoves == 1)){
	                System.out.println("SOMETHING IS WRONG!!");
	            }
	
	            else if(nummoves == 2){
	                    while(((ghost.Board.IsLegalMove(x + move[0],y + move[1])) == false)){
	                        Random rdir = new Random();
	                        tmpdirection = rdir.nextInt(4)* 90;
	                        while(((tmpdirection + 180) % 360) == direction){
	                            tmpdirection = rdir.nextInt(4)*90;
	                        }
                            setDir(tmpdirection, move, ms);
	                    }
	            }
	
	            else{
	                //here we have 3 or more options (though backtrack not allowed)
	                //choose best one based on PM location

                    priority = setDistancePriority(PM_x - x, PM_y - y);
	                
	                int count = 0;
	                do{
	                    if(((priority[count] + 180) % 360) == (direction)) {count++;}
	                    //if((priority[count]) == direction){count++;}
                        setDir(priority[count], move, ms);
	                    count++;
                        x = ghost.tunnelCheck(x, move[0]);
	                }while(((ghost.Board.IsLegalMove(x + move[0], y + move[1])) == false));
	                
	                count--;
	                tmpdirection = priority[count];
	                /*
	                do{ 
	                    Random rdir = new Random();
	                    tmpdirection = rdir.nextInt(4)* 90;
	                    while(((tmpdirection + 180) % 360) == direction){
	                        tmpdirection = rdir.nextInt(4)*90;
	                    }
	                    if(tmpdirection == 0) {xmove = ms;ymove = 0;}
	                    else if(tmpdirection == 90) {ymove = -ms;xmove = 0;}
	                    else if(tmpdirection == 180) {xmove = -ms;ymove = 0;}
	                    else if(tmpdirection == 270) {ymove = ms;xmove = 0;}
	                    else { System.out.println("how did I get here?");}
                        //x = ghost.tunnelCheck(x, xmove);
	                }while(((ghost.Board.IsLegalMove(x + xmove,y + ymove)) == false));
	                */
	            }
	            direction = tmpdirection;
	            
	            // This is the check for the tunnel
                //x = ghost.tunnelCheck(x, xmove);
	            ghost.setLocation(x + move[0],y + move[1]);
	            ghost.setDirection(direction);
	            
	            Counter ++;
			}
			else
				Counter = 0;
			

        }
    private void setDir(int direction, int[] move, int moverate){
        if(direction == 0) {move[0] = moverate;move[1] = 0;}
        else if(direction == 90) {move[1] = -moverate;move[0] = 0;}
        else if(direction == 180) {move[0] = -moverate;move[1] = 0;}
        else if(direction == 270) {move[1] = moverate;move[0] = 0;}
        else { System.out.println("how did I get here?");}
    }

    private int[] setDistancePriority(int xdiff, int ydiff){
        int priority[] = {-1,-1,-1,-1};
        if((Math.abs(xdiff) > (Math.abs(ydiff)))){
            if((xdiff) > 0){priority[0]=180;priority[3]=0;}
            else{priority[0]=0;priority[3]=180;}
            if((ydiff) > 0){priority[1]=90;priority[2]=270;}
            else{priority[1]=270;priority[2]=90;}
        }
        else{
            if((ydiff) > 0){priority[0]=90;priority[3]=270;}
            else{priority[0]=270;priority[3]=90;}
            if((xdiff) > 0){priority[1]=180;priority[2]=0;}
            else{priority[1]=0;priority[2]=180;}
        }
        return priority;

    }
}