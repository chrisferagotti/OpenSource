package com.UnsettlingTrend.games.PacMan;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;


public class Pellets {
	
	private int BlinkingCounter = 0;
	Vector Pellets = new Vector();
	Vector PowerPellets = new Vector();
	
	// These two "How Many..." functions check to see if there are pellets,
	// removes them if they exist, and returns true / false accordingly
	public int HowManyPelletsAtPoint(Point p1)
	{	int counter = 0;
		Point p2 = new Point();
	
		for (int i = 0; i < Pellets.size(); i++)
		{	p2 = (Point)(Pellets.get(i));
			double distance = Math.sqrt(  Math.pow( ((double)(p1.getLocation()).x - (double)(p2.getLocation()).x), 2) + 
										  Math.pow( ((double)(p1.getLocation()).y - (double)(p2.getLocation()).y), 2));
			
			if (distance <= 15)
			{	counter++;
				Pellets.remove(i);
			}
		}
		
		return counter;
	}
	
	public int HowManyPowerPelletsAtPoint(Point p1)
	{	int counter = 0;
		Point p2 = new Point();

		for (int i = 0; i < PowerPellets.size(); i++)
		{	p2 = (Point)(PowerPellets.get(i));
			double distance = Math.sqrt(  	Math.pow(((double)(p1.getLocation()).x - (double)(p2.getLocation()).x), 2) + 
									  		Math.pow(((double)(p1.getLocation()).y - (double)(p2.getLocation()).y), 2));
		
			if (distance <= 15)
			{	counter++;
				PowerPellets.remove(i);
			}
		}
	
		return counter;
	}
	
	public void AddPellet(Point p)
	{
		Pellets.add(p);
	}
	
	public void AddPellet(int x, int y)
	{
		Point p = new Point(x,y);
		AddPellet(p);
	}
	
	public void AddPowerPellet(Point p)
	{
		PowerPellets.add(p);
	}
	
	public void AddPowerPellet(int x, int y)
	{
		Point p = new Point(x,y);
		AddPowerPellet(p);
	}
	
	public void DrawAllPellets(Graphics graphics)
	{	
		for (int i = 0 ; i < Pellets.size() ; i++)
		{	graphics.setColor(Color.WHITE);
			graphics.fillOval(((Point) Pellets.get(i)).x - 2,
							  ((Point) Pellets.get(i)).y - 2, 4, 4);
		}
		
		if (BlinkingCounter > 10)
		{
			for (int i = 0 ; i < PowerPellets.size() ; i++)
			{	graphics.setColor(Color.WHITE);
				graphics.fillOval(((Point) PowerPellets.get(i)).x - 7,
								  ((Point) PowerPellets.get(i)).y - 7, 14, 14);
			}		
		}
		
		if (BlinkingCounter > 20)
			BlinkingCounter = 0;
		BlinkingCounter++;
		
	}

	public int HowManyPelletsLeft()
	{
		return Pellets.size();
	}

	public void RemoveAllPellets()
	{
		Pellets.clear();
		PowerPellets.clear();
	}

	public boolean IsThereAPowerPellet(Point p1)
	{	
		Point p2 = new Point();

		for (int i = 0; i < PowerPellets.size(); i++)
		{	p2 = (Point)(PowerPellets.get(i));
			double distance = Math.sqrt(  	Math.pow(((double)(p1.getLocation()).x - (double)(p2.getLocation()).x), 2) + 
									  		Math.pow(((double)(p1.getLocation()).y - (double)(p2.getLocation()).y), 2));		
			if (distance <= 15)
			{	return true;
			}
		}
	
			return false;
	}
}
