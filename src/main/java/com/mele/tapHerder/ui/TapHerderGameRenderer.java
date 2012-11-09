package com.mele.tapHerder.ui;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import org.apache.log4j.Logger;

import com.mele.games.mechanics.IGameManager;
import com.mele.games.mechanics.IGameRenderer;
import com.mele.games.utils.hexarray.Cell;
import com.mele.tapHerder.TapHerder;
import com.mele.tapHerder.TapHerderGame;
import com.mele.tapHerder.TapHerderGameManager;

/**
 * Manages the display responsibiities of the game state.
 * Most work is passed off to HexArrayRenderer.
 * 
 * @author Ayar
 *
 */
public class TapHerderGameRenderer extends Frame implements IGameRenderer {
	private static final long serialVersionUID = 1L;
	protected static Logger log = Logger.getLogger(TapHerderGameRenderer.class);
	protected Graphics2D offScreenGraphicsCtx = null;
	protected Image offScreenImage = null;
	protected boolean kill = false;
	protected boolean initialized = false;
	protected TapHerderGameManager gameManager = null;
	protected HexArrayRenderer har = new HexArrayRenderer();
	protected AudioClip song; // Sound player
	
	// Graphic components
	protected Image backgroundImage = null;
	
	protected void init() {
		setVisible(true);
		setTitle("Tap Herder");
		setSize(480, 640);
		
		this.addWindowListener(new WindowAdapter() {
		      public void windowClosing(WindowEvent e) {
		    	gameManager.endGame();
		        System.exit(0);
		      }
		    });
		
		addMouseListener((TapHerderInput)gameManager.getGameInput());
		
		if (offScreenGraphicsCtx == null) {
			offScreenImage = createImage(getSize().width, getSize().height);
			offScreenGraphicsCtx = (Graphics2D) offScreenImage.getGraphics();
		}//end if
		
		har.setHexmap(((TapHerderGame)gameManager.getGame()).getHexmap());
		har.setReferencePointX(30);
		har.setReferencePointY(50);
		har.init();
		
		setSize((int)har.getMaxX() + 10, (int)har.getMaxY() + 10);
		
		// TODO: Make optional, configurable, and variable by board.
		// TODO: Add sound effects for residents, etc.
		String songName = "monkeys wedding.wav";
		URL songPath = TapHerder.class.getResource(songName);
		if (songPath != null) {
			song = Applet.newAudioClip(songPath);
			song.loop();
			log.info("Music: " + songName);
		} else {
			log.error("Cannot load song file: " + songName);
		}
		
		initialized = true;
	}
	
	/*
	@Override
	public void run() {
		if (!initialized) {
			init();
		}
		
		while (!kill) {
			TapHerderGame game = (TapHerderGame)gameManager.getGame();
			if (game.isInitialized()) {
				update(this.getGraphics());
			}
			
			if (img != null) {
				getGraphics().drawImage(img, 100, 100, this);
			}
			
			try {
				Thread.sleep(41);
			} catch (InterruptedException e) {
				log.error(GameException.fullExceptionInfo(e));
			}
			gameManager.setRenderUpToDate(true);
		}

	}
	*/
	
	public void display() {
		if (!initialized) {
			init();
		}

		TapHerderGame game = (TapHerderGame)gameManager.getGame();
		if (game.isInitialized()) {
			update(this.getGraphics());
		}

		gameManager.setRenderUpToDate(true);
	}
	
	/**
	 * @param g
	 */
	protected void drawScore(Graphics g) {
		int scorePosX = 30;
		int scorePosY = 45;
		Color backup = g.getColor();
		
		g.setColor(Color.darkGray);
		TapHerderGame game = (TapHerderGame)gameManager.getGame();
		Integer score = game.getScore();
		g.drawRect(scorePosX, scorePosY - 12, 100, 14);
		g.fillRect(scorePosX, scorePosY - 12, 100, 14);
		
		g.setColor(Color.white);
		g.drawString("Score: " + score.toString(), scorePosX, scorePosY);
		g.setColor(backup);
	}

	/**
	 * @param g
	 */
	protected void drawTapCount(Graphics g) {
		int scorePosX = 120;
		int scorePosY = 45;
		Color backup = g.getColor();
		
		g.setColor(Color.darkGray);
		TapHerderGame game = (TapHerderGame)gameManager.getGame();
		Integer tapCount = game.getTapCount();
		g.drawRect(scorePosX, scorePosY - 12, 100, 14);
		g.fillRect(scorePosX, scorePosY - 12, 100, 14);
		
		g.setColor(Color.white);
		g.drawString("Taps: " + tapCount.toString(), scorePosX, scorePosY);
		g.setColor(backup);
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Container#update(java.awt.Graphics)
	 */
	public void update(Graphics g) {
		offScreenGraphicsCtx.drawImage(backgroundImage, 0, 0, this);
		har.update(offScreenGraphicsCtx);
		
		drawScore(offScreenGraphicsCtx);
		drawTapCount(offScreenGraphicsCtx);
		
		// Draw the scene onto the screen
		if (offScreenImage != null) {
			g.drawImage(offScreenImage, 0, 0, this);
		}
	}

	@Override
	public void kill() {
		song.stop();
		kill = true;
	}

	@Override
	public void setGameManager(IGameManager gameManager) {
		this.gameManager = (TapHerderGameManager) gameManager;
	}

	public Cell getCellAtPoint(Point pt) {
		return har.getCellAtPoint(pt);
	}
}
