package com.UnsettlingTrend.games.PacMan.Ghosts;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;

import com.UnsettlingTrend.games.PacMan.Character;
import com.UnsettlingTrend.games.PacMan.GameBoard;
import com.UnsettlingTrend.games.PacMan.PCPanel;


public class Ghost extends Character{

	private GhostState chasing;
	private GhostState dead;
	private GhostState running;
    private GhostState waiting;
    private GhostState escaping;
    private GhostState entering;
    private Color gcolor;
    private int ghostType;

	private GhostState CurrentState;
        
        protected GameBoard Board;
        

	public Ghost(int ghostType)
    {
		Point P = new Point();
                this.ghostType = ghostType;
                if(ghostType == 0){
                    P.setLocation(290,270);
                    gcolor = Color.RED;
                }
                else if(ghostType == 1){
                    P.setLocation(250, 330);
                    gcolor = Color.CYAN;
                }
                else if(ghostType == 2){
                    P.setLocation(290, 330);
                    gcolor = Color.PINK;
                }
                else if(ghostType == 3){
                    P.setLocation(330, 330);
                    gcolor = Color.ORANGE;
                }
		Location = P;
		Direction = 0;

		chasing = new GhostChasing();
		dead = new GhostDead();
		running = new GhostRunning();
        waiting = new GhostWaiting();
        escaping = new GhostEscaping();
        entering = new GhostEntering();
        
        //this.setDirection(180);
        
        if(ghostType == 0){
                    P.setLocation(290,270);
                    gcolor = Color.RED;
                    this.setChasing();
                }
                else if(ghostType == 1){
                    P.setLocation(250, 330);
                    gcolor = Color.CYAN;
                    this.setWaiting();
                }
                else if(ghostType == 2){
                    P.setLocation(290, 330);
                    gcolor = Color.PINK;
                    this.setWaiting();
                }
                else if(ghostType == 3){
                    P.setLocation(330, 330);
                    gcolor = Color.ORANGE;
                    this.setWaiting();
                }
		Location = P;
	}

	public void Draw(Graphics graphics)
	{
            Color curcolor;
            if(CurrentState == running)
                curcolor = Color.BLUE;
            else
                curcolor = gcolor;
            
            int curx = getLocation().x;
            int cury = getLocation().y;
            int bottom = cury + 15;
            int foottop = cury + 11;
            int xeyeoffset = 0;
            int yeyeoffset = 0;
            int direction = getDirection();
            if((CurrentState != dead) && (CurrentState != entering)){
                graphics.setColor(curcolor);
                graphics.fillArc(curx - 15, cury - 15, 30, 30, 0, 180);
                int xPoints[] = {curx - 15, curx - 15, curx - 10, curx - 10, curx - 7, curx - 7, curx - 2, curx - 2, curx +1, curx +1, curx +6, curx +6, curx + 10, curx + 10, curx + 15, curx + 15};
                int yPoints[] = {cury, bottom, bottom, foottop, foottop, bottom, bottom, foottop, foottop, bottom, bottom, foottop, foottop, bottom, bottom, cury};
                int numPoints = 16;
                graphics.fillPolygon(xPoints, yPoints, numPoints);
            }
            graphics.setColor(Color.WHITE);
            graphics.fillOval(curx - 10, cury - 7, 7, 7);
            graphics.fillOval(curx + 5, cury - 7, 7, 7);
            graphics.setColor(Color.BLACK);
            if(direction == 0){
                xeyeoffset = 2;
            }
            else if (direction == 90){
                yeyeoffset = -2;
            }
            else if (direction == 180){
                xeyeoffset = -2;
            }
            else if (direction == 270){
                yeyeoffset = 2;
            }
            graphics.fillOval(curx - 8 + xeyeoffset, cury - 5 + yeyeoffset, 4, 4);
            graphics.fillOval(curx + 7 + xeyeoffset, cury - 5 + yeyeoffset, 4, 4);
	}

	public String getState()
	{
            return CurrentState.getState();
	}

	// Sets current state
	public void setState(GhostState state)
	{
            this.CurrentState = state;
	}

	public void setChasing()
	{
            this.CurrentState = chasing;
	}

	public void setDead()
	{
            this.CurrentState = dead;
	}

	public void setRunning()
	{
            this.CurrentState = running;
	}
        
    public void setWaiting()
    {
        this.CurrentState = waiting;
    }
        
    public void setEscaping()
    {
        this.CurrentState = escaping;
    }
    public void setEntering()
    {
        this.CurrentState = entering;
    }

    public int getGhostType()
    {
        return this.ghostType;
    }
    
	public void setGameBoard(GameBoard B)
	{
		Board = B;
	}

	// Calls the Update function for CurrentState
	public void Update(PCPanel panel)
	{
            CurrentState.Update(this, panel);
	}

    protected int tunnelCheck(int x, int xmove){
        if(x + xmove > 580) x = xmove;
        else if(x + xmove < 0) x = 580 + xmove;
        return x;
    }

    protected int getLegalMoves(Ghost ghost, int x, int y, int moverate){
        int nummoves = 0;
        if(ghost.Board.IsLegalMove(x + moverate, y)){
            nummoves++;
        }
        if(ghost.Board.IsLegalMove(x - moverate, y)){
            nummoves++;
        }
        if(ghost.Board.IsLegalMove(x, y + moverate)){
            nummoves++;
        }
        if(ghost.Board.IsLegalMove(x, y - moverate)){
            nummoves++;
        }
        return nummoves;
    }

}