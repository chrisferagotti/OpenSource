package com.UnsettlingTrend.games.PacMan;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;


public class GameBoard {
	private Integer Score;
	private Integer HighScore = 0;
	private int Lives;
	private Vector Points = new Vector();
	private Vector GhostJailPoints = new Vector();
	private Point TP;
	private Color BoardColor;
	private Integer Level = 1;
	
	
	public GameBoard()
	{	Lives = 2;
		Score = 0;
		AddAllLinePoints();
		AddAllGhostJailLinePoints();
		BoardColor = Color.BLUE;
	} 
	
	public boolean IsLegalMove(int x, int y)
	{
		double Cross;
		double Dot;
		double Squared;
		Point A = new Point();
		Point B = new Point();
		Point C = new Point(x, y);
				
		for (int i = 0; i < Points.size() - 1; i +=2)
		{	A = new Point((Point)Points.get(i));
			B = new Point((Point)Points.get(i+1));
			Cross   = (C.y - A.y) * (B.x - A.x) - (C.x - A.x) * (B.y - A.y);
			Dot     = (C.x - A.x) * (B.x - A.x) + (C.y - A.y) * (B.y - A.y);
			Squared = (B.x - A.x) * (B.x - A.x) + (B.y - A.y) * (B.y - A.y);
			
			if ((Math.abs(Cross) == 0) && (Dot >= 0) && (Dot <= Squared))
				return true;			
		}
		
		return false;
	}
	
	public boolean IsLegalGhostJailMove(int x, int y)
	{
		double Cross;
		double Dot;
		double Squared;
		Point A = new Point();
		Point B = new Point();
		Point C = new Point(x, y);
				
		for (int i = 0; i < GhostJailPoints.size() - 1; i +=2)
		{	A = new Point((Point)GhostJailPoints.get(i));
			B = new Point((Point)GhostJailPoints.get(i+1));
			Cross   = (C.y - A.y) * (B.x - A.x) - (C.x - A.x) * (B.y - A.y);
			Dot     = (C.x - A.x) * (B.x - A.x) + (C.y - A.y) * (B.y - A.y);
			Squared = (B.x - A.x) * (B.x - A.x) + (B.y - A.y) * (B.y - A.y);
			
			if ((Math.abs(Cross) == 0) && (Dot >= 0) && (Dot <= Squared))
				return true;			
		}
		
		return false;
	}
	
	private void AddAllLinePoints()
	{ 	// This Adds all the lines to the game
		
		// Line 1
		TP = new Point(40,   70); Points.add(TP);
		TP = new Point(260,  70); Points.add(TP);
		// Line 2
		TP = new Point(40,   70); Points.add(TP);
		TP = new Point(40,  210); Points.add(TP);
		// Line 3
		TP = new Point(260,  70); Points.add(TP);
		TP = new Point(260, 150); Points.add(TP);
		// Line 4
		TP = new Point(40,  150); Points.add(TP);
		TP = new Point(540, 150); Points.add(TP);
		// Line 5
		TP = new Point(320,  70); Points.add(TP);
		TP = new Point(320, 150); Points.add(TP);
		// Line 6
		TP = new Point(140,  70); Points.add(TP);
		TP = new Point(140, 570); Points.add(TP);
		// Line 7
		TP = new Point(320,  70); Points.add(TP);
		TP = new Point(540,  70); Points.add(TP);
		// Line 8
		TP = new Point(440,  70); Points.add(TP);
		TP = new Point(440, 570); Points.add(TP);
		// Line 9
		TP = new Point(540,  70); Points.add(TP);
		TP = new Point(540, 210); Points.add(TP);
		// Line 10
		TP = new Point(40,  210); Points.add(TP);
		TP = new Point(140, 210); Points.add(TP);
		// Line 11
		TP = new Point(440, 210); Points.add(TP);
		TP = new Point(540, 210); Points.add(TP);
		// Line 12
		TP = new Point(200, 150); Points.add(TP);
		TP = new Point(200, 210); Points.add(TP);
		// Line 13
		TP = new Point(380, 150); Points.add(TP);
		TP = new Point(380, 210); Points.add(TP);
		// Line 14
		TP = new Point(200, 210); Points.add(TP);
		TP = new Point(260, 210); Points.add(TP);
		// Line 15
		TP = new Point(320, 210); Points.add(TP);
		TP = new Point(380, 210); Points.add(TP);
		// Line 16
		TP = new Point(260, 210); Points.add(TP);
		TP = new Point(260, 270); Points.add(TP);
		// Line 17
		TP = new Point(320, 210); Points.add(TP);
		TP = new Point(320, 270); Points.add(TP);
		// Line 18
		TP = new Point(200, 270); Points.add(TP);
		TP = new Point(380, 270); Points.add(TP);
		// Line 19
		TP = new Point(200, 270); Points.add(TP);
		TP = new Point(200, 450); Points.add(TP);
		// Line 20
		TP = new Point(380, 270); Points.add(TP);
		TP = new Point(380, 450); Points.add(TP);
		// Line 21
		TP = new Point(0,   330); Points.add(TP);
		TP = new Point(200, 330); Points.add(TP);
		// Line 22
		TP = new Point(380, 330); Points.add(TP);
		TP = new Point(580, 330); Points.add(TP);
		// Line 23
		TP = new Point(40,  450); Points.add(TP);
		TP = new Point(260, 450); Points.add(TP);
		// Line 24
		TP = new Point(320, 450); Points.add(TP);
		TP = new Point(540, 450); Points.add(TP);
		// Line 25
		TP = new Point(40,  450); Points.add(TP);
		TP = new Point(40,  510); Points.add(TP);
		// Line 26
		TP = new Point(540, 450); Points.add(TP);
		TP = new Point(540, 510); Points.add(TP);
		// Line 27
		TP = new Point(40,  510); Points.add(TP);
		TP = new Point(80,  510); Points.add(TP);
		// Line 28
		TP = new Point(500, 510); Points.add(TP);
		TP = new Point(540, 510); Points.add(TP);
		// Line 29
		TP = new Point(260, 450); Points.add(TP);
		TP = new Point(260, 510); Points.add(TP);
		// Line 30
		TP = new Point(320, 450); Points.add(TP);
		TP = new Point(320, 510); Points.add(TP);
		// Line 31
		TP = new Point(140, 510); Points.add(TP);
		TP = new Point(440, 510); Points.add(TP);
		// Line 32
		TP = new Point(80,  510); Points.add(TP);
		TP = new Point(80,  570); Points.add(TP);
		// Line 33
		TP = new Point(500, 510); Points.add(TP);
		TP = new Point(500, 570); Points.add(TP);
		// Line 34
		TP = new Point(200, 510); Points.add(TP);
		TP = new Point(200, 570); Points.add(TP);
		// Line 35
		TP = new Point(380, 510); Points.add(TP);
		TP = new Point(380, 570); Points.add(TP);
		// Line 36
		TP = new Point(40,  570); Points.add(TP);
		TP = new Point(140, 570); Points.add(TP);
		// Line 37
		TP = new Point(440, 570); Points.add(TP);
		TP = new Point(540, 570); Points.add(TP);
		// Line 38
		TP = new Point(200, 570); Points.add(TP);
		TP = new Point(260, 570); Points.add(TP);
		// Line 39
		TP = new Point(320, 570); Points.add(TP);
		TP = new Point(380, 570); Points.add(TP);
		// Line 40
		TP = new Point(40,  570); Points.add(TP);
		TP = new Point(40,  630); Points.add(TP);
		// Line 41
		TP = new Point(540, 570); Points.add(TP);
		TP = new Point(540, 630); Points.add(TP);
		// Line 42
		TP = new Point(260, 570); Points.add(TP);
		TP = new Point(260, 630); Points.add(TP);
		// Line 43
		TP = new Point(320, 570); Points.add(TP);
		TP = new Point(320, 630); Points.add(TP);
		// Line 44
		TP = new Point(40,  630); Points.add(TP);
		TP = new Point(540, 630); Points.add(TP);
		// Line 45
		TP = new Point(200, 390); Points.add(TP);
		TP = new Point(380, 390); Points.add(TP);
		
	}
	
	private void AddAllGhostJailLinePoints()
	{
		// These are the lines in the ghost cage
		TP = new Point(290, 270); GhostJailPoints.add(TP);
		TP = new Point(290, 330); GhostJailPoints.add(TP);

		TP = new Point(250, 330); GhostJailPoints.add(TP);
		TP = new Point(330, 330); GhostJailPoints.add(TP);
	}
	
	public void AddToScore(int value)
	{
		Score += value;
	}

        public Integer getScore()
        {
            return Score;
        }    
        
        public Color getBoardColor()
	{
		return BoardColor;
	}
	
	public void setBoardColor(Color value)
	{
		BoardColor = value;
	}
	
	// This is just a test Function to see if the lines are correct
	public void DrawAllLines(Graphics graphics)
	{	graphics.setColor(Color.YELLOW);
		
		for (int i = 0; i < Points.size() - 1; i += 2)
		{
			graphics.drawLine(	((Point)Points.get(i)).x,   ((Point)Points.get(i)).y,
								((Point)Points.get(i+1)).x, ((Point)Points.get(i+1)).y);
		}
	}
	
	public void DrawBoard(Graphics graphics)
	{

        //Stage Color
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, 580, 730);
        
        // Draw PacManLives
		DrawPacManLives(graphics);
		
		// Draw Scores
		DrawScores(graphics);
		
        //Wall/Obstacle Color
        graphics.setColor(BoardColor);
        
        //Top Wall
        graphics.drawLine(20,  45,  560, 45);
        graphics.drawLine(20,  50,  275, 50);
        graphics.drawLine(305, 50,  560, 50);
        graphics.drawLine(15,  50,  15,  230);
        graphics.drawLine(20,  50,  20,  230);
        graphics.drawLine(20,  230, 115, 230);
        graphics.drawLine(20,  235, 115, 235);
        graphics.drawLine(115, 235, 115, 305);
        graphics.drawLine(120, 235, 120, 305);
        graphics.drawLine(0,   305, 115, 305);
        graphics.drawLine(0,   310, 115, 310);
        graphics.drawLine(565, 50,  565, 230);
        graphics.drawLine(560, 50,  560, 230);
        graphics.drawLine(560, 230, 465, 230);
        graphics.drawLine(560, 235, 465, 235);
        graphics.drawLine(460, 235, 460, 305);
        graphics.drawLine(465, 235, 465, 305);
        graphics.drawLine(465, 305, 580, 305);
        graphics.drawLine(465, 310, 580, 310);
        
        //Center Obstacle
        graphics.drawLine(280, 55,  280, 125);
        graphics.drawLine(285, 130, 295, 130);
        graphics.drawLine(300, 55,  300, 125);
        
        //Obstacle 1
        graphics.drawLine(65,  90,  115, 90);
        graphics.drawLine(60,  95,  60,  125);
        graphics.drawLine(120, 95,  120, 125);
        graphics.drawLine(65,  130, 115, 130);
        
        //Obstacle 2
        graphics.drawLine(165, 90,  235, 90);
        graphics.drawLine(160, 95,  160, 125);
        graphics.drawLine(240, 95,  240, 125);
        graphics.drawLine(165, 130, 235, 130);
        
        //Obstacle 3
        graphics.drawLine(345, 90,  415, 90);
        graphics.drawLine(340, 95,  340, 125);
        graphics.drawLine(420, 95,  420, 125);
        graphics.drawLine(345, 130, 415, 130);
        
        //Obstacle 4
        graphics.drawLine(465, 90,  515, 90);
        graphics.drawLine(460, 95,  460, 125);
        graphics.drawLine(520, 95,  520, 125);
        graphics.drawLine(465, 130, 515, 130);
        
        //Obstacle 5
        graphics.drawLine(65,  170, 115, 170);
        graphics.drawLine(60,  175, 60,  185);
        graphics.drawLine(120, 175, 120, 185);
        graphics.drawLine(65,  190, 115, 190);
        
        //Obstacle 6
        graphics.drawLine(160, 175, 160, 305);
        graphics.drawLine(165, 170, 175, 170);
        graphics.drawLine(180, 175, 180, 225);
        graphics.drawLine(185, 230, 235, 230);
        graphics.drawLine(240, 235, 240, 245);
        graphics.drawLine(185, 250, 235, 250); 
        graphics.drawLine(180, 255, 180, 305);
        graphics.drawLine(165, 310, 175, 310);
                
        //Obstacle 7
        graphics.drawLine(225, 170, 355, 170);
        graphics.drawLine(220, 175, 220, 185);
        graphics.drawLine(225, 190, 275, 190);
        graphics.drawLine(360, 175, 360, 185);
        graphics.drawLine(305, 190, 355, 190);
        graphics.drawLine(280, 195, 280, 245);
        graphics.drawLine(300, 195, 300, 245);
        graphics.drawLine(285, 250, 295, 250);
        
        //Obstacle 8
        graphics.drawLine(405, 170, 415, 170);
        graphics.drawLine(400, 175, 400, 225);
        graphics.drawLine(345, 230, 395, 230);
        graphics.drawLine(340, 235, 340, 245);
        graphics.drawLine(345, 250, 395, 250);
        graphics.drawLine(400, 255, 400, 305);
        graphics.drawLine(405, 310, 415, 310);
        graphics.drawLine(420, 175, 420, 305);
                
        //Obstacle 9
        graphics.drawLine(465, 170, 515, 170);
        graphics.drawLine(460, 175, 460, 185);
        graphics.drawLine(520, 175, 520, 185);
        graphics.drawLine(465, 190, 515, 190);
        
        //Obstacle 10
        graphics.drawLine(165, 350, 175, 350);
        graphics.drawLine(160, 355, 160, 425);
        graphics.drawLine(165, 430, 175, 430);
        graphics.drawLine(180, 355, 180, 425);
        
        //Obstacle 11
        graphics.drawLine(225, 410, 355, 410);
        graphics.drawLine(220, 415, 220, 425);
        graphics.drawLine(225, 430, 275, 430);
        graphics.drawLine(360, 415, 360, 425);
        graphics.drawLine(305, 430, 355, 430);
        graphics.drawLine(280, 435, 280, 485);
        graphics.drawLine(300, 435, 300, 485);
        graphics.drawLine(285, 490, 295, 490);
        
        //Obstacle 12
        graphics.drawLine(405, 350, 415, 350);
        graphics.drawLine(400, 355, 400, 425);
        graphics.drawLine(405, 430, 415, 430);
        graphics.drawLine(420, 355, 420, 425);
        
        //Obstacle 13
        graphics.drawLine(65,  470, 115, 470);
        graphics.drawLine(60,  475, 60,  485);
        graphics.drawLine(65,  490, 95,  490);
        graphics.drawLine(100, 495, 100, 545);
        graphics.drawLine(105, 550, 115, 550);
        graphics.drawLine(120, 475, 120, 545);
        
        //Obstacle 14
        graphics.drawLine(165, 470, 235, 470);
        graphics.drawLine(160, 475, 160, 485);
        graphics.drawLine(165, 490, 235, 490);
        graphics.drawLine(240, 475, 240, 485);
        
        //Obstacle 15
        graphics.drawLine(345, 470, 415, 470);
        graphics.drawLine(340, 475, 340, 485);
        graphics.drawLine(345, 490, 415, 490);
        graphics.drawLine(420, 475, 420, 485);
        
        //Obstacle 16
        graphics.drawLine(465, 470, 515, 470);
        graphics.drawLine(460, 475, 460, 545);
        graphics.drawLine(465, 550, 475, 550);
        graphics.drawLine(480, 545, 480, 495);
        graphics.drawLine(485, 490, 515, 490);
        graphics.drawLine(520, 475, 520, 485);
        
        //Obstacle 17
        graphics.drawLine(65,  610, 235, 610);
        graphics.drawLine(60,  595, 60,  605);
        graphics.drawLine(240, 595, 240, 605);
        graphics.drawLine(65,  590, 155, 590);
        graphics.drawLine(185, 590, 235, 590);
        graphics.drawLine(160, 535, 160, 585);
        graphics.drawLine(180, 535, 180, 585);
        graphics.drawLine(165, 530, 175, 530);
        
        //Obstacle 18
        graphics.drawLine(225, 530, 355, 530);
        graphics.drawLine(220, 535, 220, 545);
        graphics.drawLine(225, 550, 275, 550);
        graphics.drawLine(360, 535, 360, 545);
        graphics.drawLine(305, 550, 355, 550);
        graphics.drawLine(280, 555, 280, 605);
        graphics.drawLine(300, 555, 300, 605);
        graphics.drawLine(285, 610, 295, 610);
        
        //Obstacle 19
        graphics.drawLine(345, 610, 515, 610);
        graphics.drawLine(340, 595, 340, 605);
        graphics.drawLine(520, 595, 520, 605);
        graphics.drawLine(345, 590, 395, 590);
        graphics.drawLine(425, 590, 515, 590);
        graphics.drawLine(400, 585, 400, 535);
        graphics.drawLine(420, 585, 420, 535);
        graphics.drawLine(405, 530, 415, 530);
        
        //Bottom Wall
        graphics.drawLine(0,   350, 115, 350);
        graphics.drawLine(0,   355, 115, 355);
        graphics.drawLine(115, 355, 115, 425);
        graphics.drawLine(120, 355, 120, 425);
        graphics.drawLine(115, 425, 20,  425);
        graphics.drawLine(115, 430, 20,  430);
        graphics.drawLine(15,  430, 15,  650);
        graphics.drawLine(20,  430, 20,  525);
        graphics.drawLine(20,  555, 20,  650);
        graphics.drawLine(25,  530, 55,  530);
        graphics.drawLine(25,  550, 55,  550);
        graphics.drawLine(60,  535, 60,  545);
        graphics.drawLine(20,  650, 560, 650);
        graphics.drawLine(20,  655, 560, 655);
        graphics.drawLine(565, 650, 565, 430);
        graphics.drawLine(560, 650, 560, 555);
        graphics.drawLine(560, 430, 560, 525);
        graphics.drawLine(555, 530, 525, 530);
        graphics.drawLine(555, 550, 525, 550);
        graphics.drawLine(520, 535, 520, 545);
        graphics.drawLine(560, 430, 465, 430);
        graphics.drawLine(560, 425, 465, 425);
        graphics.drawLine(460, 355, 460, 425);
        graphics.drawLine(465, 355, 465, 425);
        graphics.drawLine(465, 355, 580, 355);
        graphics.drawLine(465, 350, 580, 350);
        		
        //Ghost Cage
        graphics.drawLine(260, 290, 260, 295);
        graphics.drawLine(220, 290, 260, 290);
        graphics.drawLine(225, 295, 260, 295);
        graphics.drawLine(220, 290, 220, 370);
        graphics.drawLine(225, 295, 225, 365);
        graphics.drawLine(220, 370, 360, 370);
        graphics.drawLine(225, 365, 355, 365);
        graphics.drawLine(355, 365, 355, 295);
        graphics.drawLine(360, 370, 360, 290);
        graphics.drawLine(355, 365, 355, 295);
        graphics.drawLine(360, 290, 320, 290);
        graphics.drawLine(355, 295, 320, 295);
        graphics.drawLine(320, 290, 320, 295);        
        graphics.setColor(Color.WHITE);
        graphics.fillRect(261, 291, 58, 3);
        
        graphics.setColor(BoardColor);
        //Draw all top left corners
        DrawTopLeft(graphics, 15,   45);
        DrawTopLeft(graphics, 300,  50);      
        DrawTopLeft(graphics, 60,   90);
        DrawTopLeft(graphics, 160,  90);
        DrawTopLeft(graphics, 340,  90);
        DrawTopLeft(graphics, 460,  90);
        DrawTopLeft(graphics, 60,  170);
        DrawTopLeft(graphics, 160, 170);
        DrawTopLeft(graphics, 220, 170);
        DrawTopLeft(graphics, 400, 170);
        DrawTopLeft(graphics, 460, 170);
        DrawTopLeft(graphics, 300, 190);
        DrawTopLeft(graphics, 340, 230);
        DrawTopLeft(graphics, 180, 250);
        DrawTopLeft(graphics, 460, 230);
        DrawTopLeft(graphics, 160, 350);
        DrawTopLeft(graphics, 400, 350);
        DrawTopLeft(graphics, 460, 350);
        DrawTopLeft(graphics, 220, 410);
        DrawTopLeft(graphics, 15,  425);
        DrawTopLeft(graphics, 60,  470);
        DrawTopLeft(graphics, 300, 430);
        DrawTopLeft(graphics, 60,  470);
        DrawTopLeft(graphics, 340, 470);
        DrawTopLeft(graphics, 460, 470);
        DrawTopLeft(graphics, 480, 490);
        DrawTopLeft(graphics, 160, 530);
        DrawTopLeft(graphics, 220, 530);
        DrawTopLeft(graphics, 400, 530);
        DrawTopLeft(graphics, 520, 530);
        DrawTopLeft(graphics, 20,  550);
        DrawTopLeft(graphics, 300, 550);
        DrawTopLeft(graphics, 60,  590);
        DrawTopLeft(graphics, 340, 590);
        DrawTopLeft(graphics, 160, 470);
        
        //Draw all top right corners
        DrawTopRight(graphics, 565, 45);
        DrawTopRight(graphics, 120, 90);
        DrawTopRight(graphics, 240, 90);
        DrawTopRight(graphics, 420, 90);
        DrawTopRight(graphics, 520, 90);
        DrawTopRight(graphics, 120, 170);
        DrawTopRight(graphics, 360, 170);
        DrawTopRight(graphics, 520, 170);
        DrawTopRight(graphics, 420, 170);
        DrawTopRight(graphics, 180, 170);
        DrawTopRight(graphics, 240, 230);
        DrawTopRight(graphics, 120, 350);
        DrawTopRight(graphics, 180, 350);
        DrawTopRight(graphics, 420, 350);
        DrawTopRight(graphics, 360, 410);
        DrawTopRight(graphics, 120, 470);
        DrawTopRight(graphics, 240, 470);
        DrawTopRight(graphics, 420, 470);
        DrawTopRight(graphics, 520, 470);
        DrawTopRight(graphics, 60,  530);
        DrawTopRight(graphics, 360, 530);
        DrawTopRight(graphics, 420, 530);
        DrawTopRight(graphics, 180, 530);
        DrawTopRight(graphics, 240, 590);
        DrawTopRight(graphics, 520, 590);
        DrawTopRight(graphics, 560, 550);
        DrawTopRight(graphics, 280, 190);
        DrawTopRight(graphics, 280, 430);
        DrawTopRight(graphics, 400, 250);
        DrawTopRight(graphics, 120, 230);
        DrawTopRight(graphics, 100, 490);
        DrawTopRight(graphics, 280,  50);
        DrawTopRight(graphics, 280, 550);
        DrawTopRight(graphics, 565, 425);
        
        // Draw all bottom left corners
        DrawBottomLeft(graphics, 15,  655);
        DrawBottomLeft(graphics, 15,  235);
        DrawBottomLeft(graphics, 60,  130);
        DrawBottomLeft(graphics, 160, 130);
        DrawBottomLeft(graphics, 340, 130);
        DrawBottomLeft(graphics, 460, 130);
        DrawBottomLeft(graphics, 60,  190);
        DrawBottomLeft(graphics, 220, 190);
        DrawBottomLeft(graphics, 460, 190);
        DrawBottomLeft(graphics, 180, 230);
        DrawBottomLeft(graphics, 280, 250);
        DrawBottomLeft(graphics, 340, 250);
        DrawBottomLeft(graphics, 160, 310);
        DrawBottomLeft(graphics, 400, 310);
        DrawBottomLeft(graphics, 460, 310);
        DrawBottomLeft(graphics, 160, 430);
        DrawBottomLeft(graphics, 400, 430);
        DrawBottomLeft(graphics, 460, 430);
        DrawBottomLeft(graphics, 220, 430);
        DrawBottomLeft(graphics, 60,  490);
        DrawBottomLeft(graphics, 160, 490);
        DrawBottomLeft(graphics, 340, 490);
        DrawBottomLeft(graphics, 100, 550);
        DrawBottomLeft(graphics, 220, 550);
        DrawBottomLeft(graphics, 460, 550);
        DrawBottomLeft(graphics, 520, 550);
        DrawBottomLeft(graphics, 60,  610);
        DrawBottomLeft(graphics, 180, 590);
        DrawBottomLeft(graphics, 420, 590);
        DrawBottomLeft(graphics, 280, 610);
        DrawBottomLeft(graphics, 340, 610);
        DrawBottomLeft(graphics, 20,  530);
        DrawBottomLeft(graphics, 280, 130);
        DrawBottomLeft(graphics, 280, 490);
        
        // Draw all bottom right corners
        DrawBottomRight(graphics, 565, 655);
        DrawBottomRight(graphics, 120, 130);
        DrawBottomRight(graphics, 240, 130);
        DrawBottomRight(graphics, 300, 130);
        DrawBottomRight(graphics, 420, 130);
        DrawBottomRight(graphics, 520, 130);
        DrawBottomRight(graphics, 120, 190);
        DrawBottomRight(graphics, 360, 190);
        DrawBottomRight(graphics, 520, 190);
        DrawBottomRight(graphics, 565, 235);
        DrawBottomRight(graphics, 300, 250);
        DrawBottomRight(graphics, 240, 250);
        DrawBottomRight(graphics, 400, 230);
        DrawBottomRight(graphics, 120, 310);
        DrawBottomRight(graphics, 180, 310);
        DrawBottomRight(graphics, 420, 310);
        DrawBottomRight(graphics, 120, 430);
        DrawBottomRight(graphics, 180, 430);
        DrawBottomRight(graphics, 360, 430);
        DrawBottomRight(graphics, 420, 430);
        DrawBottomRight(graphics, 240, 490);
        DrawBottomRight(graphics, 420, 490);
        DrawBottomRight(graphics, 520, 490);
        DrawBottomRight(graphics, 160, 590);
        DrawBottomRight(graphics, 120, 550);
        DrawBottomRight(graphics, 360, 550);
        DrawBottomRight(graphics, 480, 550);
        DrawBottomRight(graphics, 560, 530);
        DrawBottomRight(graphics, 240, 610);
        DrawBottomRight(graphics, 300, 610);
        DrawBottomRight(graphics, 400, 590);
        DrawBottomRight(graphics, 520, 610);
        DrawBottomRight(graphics, 300, 490);
        DrawBottomRight(graphics, 60,  550);
       
	}
	
	private void DrawTopLeft(Graphics g, int x, int y)
	{
		g.drawArc(x, y, 10, 10, 90, 90);
	}
	
	private void DrawTopRight(Graphics g, int x, int y)
	{
		g.drawArc(x-10, y, 10, 10, 0, 90);
	}
	
	private void DrawBottomLeft(Graphics g, int x, int y)
	{
		g.drawArc(x, y-10, 10, 10, 180, 90);
	}
	
	private void DrawBottomRight(Graphics g, int x, int y)
	{
		g.drawArc(x-10, y-10, 10, 10, 0, -90);
	}
	
	private void DrawPacManLives(Graphics graphics)
	{
		for (int i = 0 ; i < Lives ; i++)
		{	graphics.setColor(Color.YELLOW);
			graphics.fillArc((50 + i * 40) - 15, 680 - 15, 30, 30, 135, -270);
		}
	}

	private void DrawScores(Graphics graphics)
	{
		graphics.setColor(Color.WHITE);
		graphics.drawString("Score", 275, 15);
		graphics.drawString(Score.toString(), 290 - (Score.toString().length())*4, 30);
	}

	public void AddLife()
	{
		Lives++;
	}

	public void SubtractLife()
	{
		Lives--;
	}
	
	public int getLives()
	{
		return Lives;
	}
	
	public Integer getLevel()
	{
		return Level;
	}

	public void IncrementLevel()
	{
		Level++;
	}

	public void SetLevel(Integer value)
	{
		Level = value;
	}
	
	public void ResetScore()
	{
		Score = 0;
	}
        
        public void setLives(int value)
        {
            Lives = value;
        }
}
