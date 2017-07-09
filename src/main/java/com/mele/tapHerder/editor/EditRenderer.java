package com.mele.tapHerder.editor;

import java.applet.AudioClip;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mele.games.hex.ui.HexArrayController;
import com.mele.games.hex.ui.HexArrayRenderer;
import com.mele.games.utils.GameException;

/**
 * Manages the display responsibilities of the game state.
 * Most work is passed off to HexArrayRenderer.
 * 
 * @author Ayar
 *
 */
public class EditRenderer extends Frame {
	private static final long serialVersionUID = 1L;
	protected static Logger log = LogManager.getLogger(EditRenderer.class);
	protected Graphics2D offScreenGraphicsCtx = null;
	protected Image offScreenImage = null;
	protected boolean kill = false;
	protected boolean initialized = false;

	protected HexArrayController controller = new HexArrayController(this);
	protected EditCommandBar editCommand = new EditCommandBar();
	protected AudioClip song; // Sound player
	
	// Graphic components
	protected Image backgroundImage = null;
	
	protected void init() {
		setVisible(true);
		setTitle("Tap Herder Map Editor");
		setSize(480, 640);
		
		this.addWindowListener(new WindowAdapter() {
		      public void windowClosing(WindowEvent e) {
		    	kill = true;
		        //System.exit(0);
		      }
		    });
		
		if (offScreenGraphicsCtx == null) {
			offScreenImage = createImage(getSize().width, getSize().height);
			offScreenGraphicsCtx = (Graphics2D) offScreenImage.getGraphics();
		}//end if
		
		controller.size(1,1);
		controller.snap();
		
		
		initialized = true;
	}
	

	/**
	 * 
	 */
	public void run() {
		if (!initialized) {
			init();
		}
		
		while (!kill) {
			display();
			
			try {
				Thread.sleep(83);
			} catch (InterruptedException e) {
				log.error(GameException.fullExceptionInfo(e));
			}
		}

	}

	
	public void display() {
		if (!initialized) {
			init();
		} else {
			update(this.getGraphics());
		}
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Container#update(java.awt.Graphics)
	 */
	public void update(Graphics g) {
		offScreenGraphicsCtx.drawImage(backgroundImage, 0, 0, this);
		HexArrayRenderer har = controller.getView();
		har.update(offScreenGraphicsCtx);
		
		editCommand.update(offScreenGraphicsCtx);
		
		// Draw the scene onto the screen
		if (offScreenImage != null) {
			g.drawImage(offScreenImage, 0, 0, this);
		}
	}


	public void kill() {
		song.stop();
		kill = true;
	}

	public boolean isKilled() {
		return kill;
	}

	public String commandAtPoint(Point p) {
		return editCommand.commandAtPoint(p);
	}
}
