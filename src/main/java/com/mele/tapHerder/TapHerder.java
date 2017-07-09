package com.mele.tapHerder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mele.games.mechanics.EGameState;
import com.mele.games.utils.GameException;
import com.mele.tapHerder.editor.EditRenderer;

/**
 * Main class for the Tap Herder game.
 * 
 * @author Al
 *
 */
public class TapHerder {
	protected static Logger log = LogManager.getLogger(TapHerder.class);

	
	/**
	 * Set up a new game board and initiate the main run loop.
	 */
	public static void startGame() {
		TapHerderGameManager gm = new TapHerderGameManager();
		
		try {
			gm.startGame();
			
			while (gm.state() != EGameState.HALTED) {
				Thread.sleep(1000);
			}
			
			gm.endGame();
			log.info("Ending game...");
		} catch (Exception ex) {
			log.error(ex.getClass().getName() + ": " + GameException.fullExceptionInfo(ex));
		} catch (Throwable t) {
			log.error(t.getClass().getName() + ": "	+ GameException.fullExceptionInfo(t));
		}
		
		log.debug("Exit from main.");
		System.exit(0); // All done!
	}

	/**
	 * 
	 */
	public static void startEditor() {
		EditRenderer editor = new EditRenderer();

		while (!editor.isKilled()) {
			editor.display();
			
			try {
				Thread.sleep(83);
			} catch (InterruptedException e) {
				log.error(e.getClass().getName() + ": " + GameException.fullExceptionInfo(e));
			}
		}
		
		System.exit(0);
	}
	
	/**
	 * @param args
	 */
	public static void main(String args[]) {	
		
		// TODO: Splash screen for instant acknowledgment that the game has started.
		// TODO: Command line parsing to start in edit mode.
		if (args != null && args.length > 0 && "edit".equalsIgnoreCase(args[0])) {
			startEditor();
		} else {
			startGame();
		}
	}
}
