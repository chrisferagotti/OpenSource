package com.UnsettlingTrend.games.PacMan;
import java.awt.Point;


public abstract class Character {
	
	protected Point Location;
	protected int Direction;	//Direction is measured CCW from +x axis
	
	public Character()
	{
		
	}
	
	public Point getLocation()
	{		return Location;	}
	
	public void setLocation(int xValue, int yValue)
	{	Location.x = xValue;
		Location.y = yValue;		}
	
	// Returns the direction the character is traveling.
	public int getDirection()
	{	return Direction;	}
	
	// Sets the direction the character is traveling.
	public void setDirection(int value)
	{	Direction = value;	}
	

}
