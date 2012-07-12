package com.UnsettlingTrend.games.PacMan.PacMan;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;

import com.UnsettlingTrend.games.PacMan.Character;
import com.UnsettlingTrend.games.PacMan.GameBoard;


public class PacMan extends Character{
	
	private int MouthAngle;
	private boolean IsOpening;
	private int KeyPressed;
	private int CollisionDistance = 15;
	private int SpacesToMove;
	
	// This is the State Pattern Stuff...
	private PacManState pcChomping;
	private PacManState pcStopped;
	private PacManState pcStart;
	private PacManState pcDead;
	
	private PacManState CurrentState;
	// End State Pattern Stuff.
	
	protected GameBoard Board;
	
	
	
	public PacMan()
	{	MouthAngle = 90;
		IsOpening = false;
		Point P = new Point(290,510);
		Location = P;
		Direction = 0;


		
		
		
		pcChomping =new PacManStateChomping();
		pcStopped = new PacManStateStopped();
		pcStart = 	new PacManStateStart();
		pcDead = 	new PacManStateDead();
		this.setPacManStopped();
	}
	
	public void Draw(Graphics graphics)
	{	
		graphics.setColor(Color.YELLOW);
		graphics.fillArc(getLocation().x - 15, getLocation().y - 15, 30, 30, Direction + MouthAngle / 2, 2 * (180 - MouthAngle/2));

	}
	
	public int getMouthAngle()
	{
		return MouthAngle;
	}
	
	public void setMouthAngle(int value)
	{
		MouthAngle = value;
	}
	
	public boolean getIsOpening()
	{
		return IsOpening;
	}
	
	public void setIsOpening(boolean value)
	{
		IsOpening = value;
	}
	
	// Returns current state
	public String getState()
	{
		return CurrentState.getState();
	}
	
	// Sets current state
	public void setState(PacManState state)
	{
		this.CurrentState = state;
	}
	
	public void setPacManChomping()
	{
		this.CurrentState = pcChomping;
	}
	
	public void setPacManStopped()
	{	
		this.CurrentState = pcStopped;
	}
	
	public void setPacManStart()
	{
		this.CurrentState = pcStart;
	}
	
	public void setPacManDead()
	{
		this.CurrentState = pcDead;
	}
	
	// Calls the Update function for CurrentState
	public void Update()
	{	
		CurrentState.Update(this);
	}
	
	public void setGameBoard(GameBoard B)
	{
		Board = B;
	}
	
	public void setKeyPressed(int value)
	{
		KeyPressed = value;
	}

	public int getKeyPressed()
	{
		return KeyPressed;
	}
	
	public void setSpacesToMove(int value)
	{
		SpacesToMove = value;
	}
	
	public int getSpacesToMove()
	{
		return SpacesToMove;
	}
	
	//public boolean CheckCollision()
}
