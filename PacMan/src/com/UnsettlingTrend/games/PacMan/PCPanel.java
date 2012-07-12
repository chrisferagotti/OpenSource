package com.UnsettlingTrend.games.PacMan;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.UnsettlingTrend.games.PacMan.Ghosts.Ghost;
import com.UnsettlingTrend.games.PacMan.PacMan.PacMan;

public class PCPanel extends JPanel {

	public GameBoard Board = new GameBoard();
	public PacMan PC = new PacMan();
	public Ghost Blinky = new Ghost(0);
	public Ghost Inky = new Ghost(1);
	public Ghost Pinky = new Ghost(2);
	public Ghost Clyde = new Ghost(3);
	private int GameSpeed = 40;
	private int SpacesToMove = 5;
	private int KeyPressed;
	public Pellets AllPellets = new Pellets();

	// This is the State Pattern Stuff...
	private PCPanelState Paused;
	private PCPanelState GameInPlay;
	private PCPanelState Start;
	private PCPanelState StartScreen;
	private PCPanelState PacManDying;
	private PCPanelState GameOver;
	private PCPanelState PowerPelletEaten;
	private PCPanelState HighScores;

	private PCPanelState CurrentState;

	// End State Pattern Stuff.

	public PCPanel() {
		super();

		PC.setSpacesToMove(SpacesToMove);

		Start = new PCPanelStateStart();
		GameInPlay = new PCPanelStateGameInPlay();
		Paused = new PCPanelStatePaused();
		StartScreen = new PCPanelStateStartScreen();
		PacManDying = new PCPanelStatePacManDying();
		GameOver = new PCPanelStateGameOver();
		PowerPelletEaten = new PCPanelStatePowerPelletEaten();
		this.setPanelStartScreen();

		PC.setGameBoard(Board);
		PC.setDirection(180);
		Blinky.setGameBoard(Board);
		Inky.setGameBoard(Board);
		Pinky.setGameBoard(Board);
		Clyde.setGameBoard(Board);
		AddAllPellets();

		ActionListener RefreshScreen = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				repaint();
			}
		};

		Timer RefreshTimer = new Timer(GameSpeed, RefreshScreen);
		RefreshTimer.start();

	}

	public void paint(Graphics graphics) {
		this.Update(graphics);
	}

	public void setKeyPressed(int value) {
		KeyPressed = value;
		PC.setKeyPressed(value);
		System.out.println(value);

	}

	public int getKeyPressed() {
		return KeyPressed;
	}

	public void AddAllPellets() { // Add all the Normal Pellets
		AllPellets.AddPellet(40, 70);
		AllPellets.AddPellet(40, 90);
		AllPellets.AddPellet(40, 130);
		AllPellets.AddPellet(40, 150);
		AllPellets.AddPellet(40, 170);
		AllPellets.AddPellet(40, 190);
		AllPellets.AddPellet(40, 210);
		AllPellets.AddPellet(60, 70);
		AllPellets.AddPellet(80, 70);
		AllPellets.AddPellet(100, 70);
		AllPellets.AddPellet(120, 70);
		AllPellets.AddPellet(140, 70);
		AllPellets.AddPellet(160, 70);
		AllPellets.AddPellet(180, 70);
		AllPellets.AddPellet(200, 70);
		AllPellets.AddPellet(220, 70);
		AllPellets.AddPellet(240, 70);
		AllPellets.AddPellet(260, 70);
		AllPellets.AddPellet(260, 90);
		AllPellets.AddPellet(260, 110);
		AllPellets.AddPellet(260, 130);
		AllPellets.AddPellet(260, 150);
		AllPellets.AddPellet(320, 70);
		AllPellets.AddPellet(340, 70);
		AllPellets.AddPellet(360, 70);
		AllPellets.AddPellet(380, 70);
		AllPellets.AddPellet(400, 70);
		AllPellets.AddPellet(420, 70);
		AllPellets.AddPellet(440, 70);
		AllPellets.AddPellet(460, 70);
		AllPellets.AddPellet(480, 70);
		AllPellets.AddPellet(500, 70);
		AllPellets.AddPellet(520, 70);
		AllPellets.AddPellet(540, 70);
		AllPellets.AddPellet(320, 90);
		AllPellets.AddPellet(320, 110);
		AllPellets.AddPellet(320, 130);
		AllPellets.AddPellet(320, 150);
		AllPellets.AddPellet(540, 90);
		AllPellets.AddPellet(540, 130);
		AllPellets.AddPellet(540, 150);
		AllPellets.AddPellet(540, 170);
		AllPellets.AddPellet(540, 190);
		AllPellets.AddPellet(540, 210);
		AllPellets.AddPellet(60, 150);
		AllPellets.AddPellet(80, 150);
		AllPellets.AddPellet(100, 150);
		AllPellets.AddPellet(120, 150);
		AllPellets.AddPellet(140, 150);
		AllPellets.AddPellet(160, 150);
		AllPellets.AddPellet(180, 150);
		AllPellets.AddPellet(200, 150);
		AllPellets.AddPellet(220, 150);
		AllPellets.AddPellet(240, 150);
		AllPellets.AddPellet(280, 150);
		AllPellets.AddPellet(300, 150);
		AllPellets.AddPellet(340, 150);
		AllPellets.AddPellet(360, 150);
		AllPellets.AddPellet(380, 150);
		AllPellets.AddPellet(400, 150);
		AllPellets.AddPellet(420, 150);
		AllPellets.AddPellet(440, 150);
		AllPellets.AddPellet(460, 150);
		AllPellets.AddPellet(480, 150);
		AllPellets.AddPellet(500, 150);
		AllPellets.AddPellet(520, 150);
		AllPellets.AddPellet(140, 90);
		AllPellets.AddPellet(140, 110);
		AllPellets.AddPellet(140, 130);
		AllPellets.AddPellet(140, 170);
		AllPellets.AddPellet(140, 190);
		AllPellets.AddPellet(140, 210);
		AllPellets.AddPellet(140, 230);
		AllPellets.AddPellet(140, 250);
		AllPellets.AddPellet(140, 270);
		AllPellets.AddPellet(140, 290);
		AllPellets.AddPellet(140, 310);
		AllPellets.AddPellet(140, 330);
		AllPellets.AddPellet(140, 350);
		AllPellets.AddPellet(140, 370);
		AllPellets.AddPellet(140, 390);
		AllPellets.AddPellet(140, 410);
		AllPellets.AddPellet(140, 430);
		AllPellets.AddPellet(140, 450);
		AllPellets.AddPellet(140, 470);
		AllPellets.AddPellet(140, 490);
		AllPellets.AddPellet(140, 510);
		AllPellets.AddPellet(140, 530);
		AllPellets.AddPellet(140, 550);
		AllPellets.AddPellet(140, 570);
		AllPellets.AddPellet(440, 90);
		AllPellets.AddPellet(440, 110);
		AllPellets.AddPellet(440, 130);
		AllPellets.AddPellet(440, 170);
		AllPellets.AddPellet(440, 190);
		AllPellets.AddPellet(440, 210);
		AllPellets.AddPellet(440, 230);
		AllPellets.AddPellet(440, 250);
		AllPellets.AddPellet(440, 270);
		AllPellets.AddPellet(440, 290);
		AllPellets.AddPellet(440, 310);
		AllPellets.AddPellet(440, 330);
		AllPellets.AddPellet(440, 350);
		AllPellets.AddPellet(440, 370);
		AllPellets.AddPellet(440, 390);
		AllPellets.AddPellet(440, 410);
		AllPellets.AddPellet(440, 430);
		AllPellets.AddPellet(440, 450);
		AllPellets.AddPellet(440, 470);
		AllPellets.AddPellet(440, 490);
		AllPellets.AddPellet(440, 510);
		AllPellets.AddPellet(440, 530);
		AllPellets.AddPellet(440, 550);
		AllPellets.AddPellet(440, 570);
		AllPellets.AddPellet(60, 210);
		AllPellets.AddPellet(80, 210);
		AllPellets.AddPellet(100, 210);
		AllPellets.AddPellet(120, 210);
		AllPellets.AddPellet(460, 210);
		AllPellets.AddPellet(480, 210);
		AllPellets.AddPellet(500, 210);
		AllPellets.AddPellet(520, 210);
		AllPellets.AddPellet(200, 170);
		AllPellets.AddPellet(200, 190);
		AllPellets.AddPellet(200, 210);
		AllPellets.AddPellet(380, 170);
		AllPellets.AddPellet(380, 190);
		AllPellets.AddPellet(380, 210);
		AllPellets.AddPellet(220, 210);
		AllPellets.AddPellet(240, 210);
		AllPellets.AddPellet(260, 210);
		AllPellets.AddPellet(320, 210);
		AllPellets.AddPellet(340, 210);
		AllPellets.AddPellet(360, 210);
		AllPellets.AddPellet(40, 450);
		AllPellets.AddPellet(60, 450);
		AllPellets.AddPellet(80, 450);
		AllPellets.AddPellet(100, 450);
		AllPellets.AddPellet(120, 450);
		AllPellets.AddPellet(160, 450);
		AllPellets.AddPellet(180, 450);
		AllPellets.AddPellet(200, 450);
		AllPellets.AddPellet(220, 450);
		AllPellets.AddPellet(240, 450);
		AllPellets.AddPellet(260, 450);
		AllPellets.AddPellet(320, 450);
		AllPellets.AddPellet(340, 450);
		AllPellets.AddPellet(360, 450);
		AllPellets.AddPellet(380, 450);
		AllPellets.AddPellet(400, 450);
		AllPellets.AddPellet(420, 450);
		AllPellets.AddPellet(460, 450);
		AllPellets.AddPellet(480, 450);
		AllPellets.AddPellet(500, 450);
		AllPellets.AddPellet(520, 450);
		AllPellets.AddPellet(540, 450);
		AllPellets.AddPellet(40, 470);
		AllPellets.AddPellet(40, 490);
		AllPellets.AddPellet(540, 470);
		AllPellets.AddPellet(540, 490);
		AllPellets.AddPellet(260, 470);
		AllPellets.AddPellet(260, 490);
		AllPellets.AddPellet(260, 510);
		AllPellets.AddPellet(320, 470);
		AllPellets.AddPellet(320, 490);
		AllPellets.AddPellet(320, 510);
		AllPellets.AddPellet(60, 510);
		AllPellets.AddPellet(80, 510);
		AllPellets.AddPellet(160, 510);
		AllPellets.AddPellet(180, 510);
		AllPellets.AddPellet(200, 510);
		AllPellets.AddPellet(220, 510);
		AllPellets.AddPellet(240, 510);
		AllPellets.AddPellet(340, 510);
		AllPellets.AddPellet(360, 510);
		AllPellets.AddPellet(380, 510);
		AllPellets.AddPellet(400, 510);
		AllPellets.AddPellet(420, 510);
		AllPellets.AddPellet(500, 510);
		AllPellets.AddPellet(520, 510);
		AllPellets.AddPellet(80, 530);
		AllPellets.AddPellet(80, 550);
		AllPellets.AddPellet(80, 570);
		AllPellets.AddPellet(200, 530);
		AllPellets.AddPellet(200, 550);
		AllPellets.AddPellet(200, 570);
		AllPellets.AddPellet(380, 530);
		AllPellets.AddPellet(380, 550);
		AllPellets.AddPellet(380, 570);
		AllPellets.AddPellet(500, 530);
		AllPellets.AddPellet(500, 550);
		AllPellets.AddPellet(500, 570);
		AllPellets.AddPellet(40, 570);
		AllPellets.AddPellet(60, 570);
		AllPellets.AddPellet(100, 570);
		AllPellets.AddPellet(120, 570);
		AllPellets.AddPellet(220, 570);
		AllPellets.AddPellet(240, 570);
		AllPellets.AddPellet(260, 570);
		AllPellets.AddPellet(320, 570);
		AllPellets.AddPellet(340, 570);
		AllPellets.AddPellet(360, 570);
		AllPellets.AddPellet(460, 570);
		AllPellets.AddPellet(480, 570);
		AllPellets.AddPellet(500, 570);
		AllPellets.AddPellet(520, 570);
		AllPellets.AddPellet(540, 570);
		AllPellets.AddPellet(40, 590);
		AllPellets.AddPellet(40, 610);
		AllPellets.AddPellet(260, 590);
		AllPellets.AddPellet(260, 610);
		AllPellets.AddPellet(320, 590);
		AllPellets.AddPellet(320, 610);
		AllPellets.AddPellet(540, 590);
		AllPellets.AddPellet(540, 610);
		AllPellets.AddPellet(40, 630);
		AllPellets.AddPellet(60, 630);
		AllPellets.AddPellet(80, 630);
		AllPellets.AddPellet(100, 630);
		AllPellets.AddPellet(120, 630);
		AllPellets.AddPellet(140, 630);
		AllPellets.AddPellet(160, 630);
		AllPellets.AddPellet(180, 630);
		AllPellets.AddPellet(200, 630);
		AllPellets.AddPellet(220, 630);
		AllPellets.AddPellet(240, 630);
		AllPellets.AddPellet(260, 630);
		AllPellets.AddPellet(280, 630);
		AllPellets.AddPellet(300, 630);
		AllPellets.AddPellet(320, 630);
		AllPellets.AddPellet(340, 630);
		AllPellets.AddPellet(360, 630);
		AllPellets.AddPellet(380, 630);
		AllPellets.AddPellet(400, 630);
		AllPellets.AddPellet(420, 630);
		AllPellets.AddPellet(440, 630);
		AllPellets.AddPellet(460, 630);
		AllPellets.AddPellet(480, 630);
		AllPellets.AddPellet(500, 630);
		AllPellets.AddPellet(520, 630);
		AllPellets.AddPellet(540, 630);

		// Add all the Power Pellets
		AllPellets.AddPowerPellet(40, 110);
		AllPellets.AddPowerPellet(40, 510);
		AllPellets.AddPowerPellet(540, 110);
		AllPellets.AddPowerPellet(540, 510);

	}

	public void Update(Graphics graphics) {
		CurrentState.Update(this, graphics);
	}

	// Returns current state
	public String getState() {
		return CurrentState.getState();
	}

	// Sets current state
	public void setState(PCPanelState state) {
		this.CurrentState = state;
	}

	public void setPanelStart() {
		this.CurrentState = Start;
	}

	public void setPanelGameInPlay() {
		this.CurrentState = GameInPlay;
	}

	public void setPanelPaused() {
		this.CurrentState = Paused;
	}

	public void setPanelStartScreen() {
		this.CurrentState = StartScreen;
	}

	public void setPanelPacManDying() {
		this.CurrentState = PacManDying;
	}

	public void setPanelGameOver() {
		this.CurrentState = GameOver;
	}

	public void setPanelGhostEaten(Integer GhostsEaten, Ghost G) {
		this.CurrentState = new PCPanelStateGhostEaten(GhostsEaten, G);
	}

	public void setPanelPowerPelletEaten() {
		this.CurrentState = PowerPelletEaten;
	}

	public void setPanelHighScores() {
		this.CurrentState = new PCPanelStateHighScores();
	}

	public void setGameSpeed(int value) {
		GameSpeed = value;
	}

	public int getGameSpeed() {
		return GameSpeed;
	}

	public void setRefreshTime(int value) {
		GameSpeed = value;
	}

	public void resetRefreshTime() {
		// This has to be added to account for the level changes
		GameSpeed = 30;
	}

	public void ResetBoard() {
		AllPellets.RemoveAllPellets();
		AddAllPellets();
	}

	protected boolean IsGhostCollision(Ghost g, PacMan p) { // This is the
															// variable that
															// determines if the
															// distance between
															// the ghost's and
															// pacman's center
															// results in a
															// collision.
		int CollisionDistance = 20;

		double Distance = Math.sqrt(Math.pow(
				((double) (p.getLocation()).x - (double) (g.getLocation()).x),
				2)
				+ Math.pow(((double) (p.getLocation()).y - (double) (g
						.getLocation()).y), 2));

		if (Distance <= CollisionDistance)
			return true;

		return false;
	}

	public int getSpacesToMove() {
		return SpacesToMove;
	}

	public void setSpacesToMove(int value) {
		SpacesToMove = value;
	}

}
