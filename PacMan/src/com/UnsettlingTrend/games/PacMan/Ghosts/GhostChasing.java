package com.UnsettlingTrend.games.PacMan.Ghosts;



import java.util.Random;

import com.UnsettlingTrend.games.PacMan.PCPanel;

public class GhostChasing implements GhostState{
	public String getState() {
		return "chasing";
	}

	public void Update(Ghost ghost, PCPanel panel)
        {
            int x = ghost.getLocation().x;
            int y = ghost.getLocation().y;
            int direction = ghost.getDirection();
            int ghostType = ghost.getGhostType();
            int move[] = {0, 0};
            int ms = 5;
            // Set SpacesToMove
            ms = panel.getSpacesToMove();       
            int tmpdirection = direction;
            int nummoves = 0;
            int PM_x = panel.PC.getLocation().x;
            int PM_y = panel.PC.getLocation().y;
            int priority[] = {-1,-1,-1,-1};

            setDir(direction, move, ms);

            nummoves = ghost.getLegalMoves(ghost, x, y, ms);
            if((nummoves == 0) || (nummoves == 1)){
                //System.out.println("SOMETHING IS WRONG!!");
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

                double distance = (Math.sqrt((double)(PM_x - x)*(PM_x - x) + (double)(PM_y - y)*(PM_y - y)));

                if(ghostType == 0){
                    //Blinky always goes straight for you!
                    priority = setDistancePriority(PM_x - x, PM_y - y);
                }
                else if(ghostType == 1){
                    //Inky gets close, then goes random, but will stay on tail if very close
                    if((distance > 150) || (distance < 80)){
                        priority = setDistancePriority(PM_x - x, PM_y - y);
                    }
                    else{
                        priority = setRandomPriority(direction);
                    }
                }
                else if(ghostType == 2){
                    //Pinky stays reasonably close, also will stay on tail
                    if((distance > 200) || (distance < 80)){
                        priority = setDistancePriority(PM_x - x, PM_y - y);
                    }
                    else{
                        priority = setRandomPriority(direction);
                    }
                }
                else if(ghostType == 3){
                    //Clyde heads toward you 3/4 the time, random rest
                    Random rdir = new Random();
                    if((rdir.nextInt(4)) == 1){
                        priority = setRandomPriority(direction);
                    }
                    else{
                        priority = setDistancePriority(PM_x - x, PM_y - y);
                    }
                }
                int count = 0;
                do{
                    //no about face allowed, can cause loops
                    if(((priority[count] + 180) % 360) == (direction)) {count++;}
                    setDir(priority[count], move, ms);
                    count++;
                }while(((ghost.Board.IsLegalMove(x + move[0], y + move[1])) == false));
                
                count--;
                tmpdirection = priority[count];
                
            }
            x = ghost.tunnelCheck(x, move[0]);
            direction = tmpdirection;

            ghost.setLocation(x + move[0],y + move[1]);
            ghost.setDirection(direction);

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
            if((xdiff) > 0){priority[0]=0;priority[3]=180;}
            else{priority[0]=180;priority[3]=0;}
            if((ydiff) > 0){priority[1]=270;priority[2]=90;}
            else{priority[1]=90;priority[2]=270;}
        }
        else{
            if((ydiff) > 0){priority[0]=270;priority[3]=90;}
            else{priority[0]=90;priority[3]=270;}
            if((xdiff) > 0){priority[1]=0;priority[2]=180;}
            else{priority[1]=180;priority[2]=0;}
        }
        return priority;

    }
    private int[] setRandomPriority(int direction){
        int priority[] = {-1,-1,-1,-1};
        int n = 0;
        Random rdir = new Random();
        int tmpdirection = rdir.nextInt(4)* 90;
        while(n < 4){
            priority[n] = (tmpdirection + n*90) % 360;
            n++;
        }
        return priority;

    }
}
