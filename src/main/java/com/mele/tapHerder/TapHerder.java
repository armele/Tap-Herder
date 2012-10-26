package com.mele.tapHerder;

import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.mele.games.mechanics.EGameState;
import com.mele.games.mechanics.IGameManager;
import com.mele.games.utils.GameException;

/**
 * Main class for the Tap Herder game.
 * 
 * @author Al
 *
 */
public class TapHerder {
	protected static Logger log = Logger.getLogger(TapHerder.class);

	/**
	 * Initialize logging.
	 */
	static public void configureLogging() {
		URL loggingConfig = TapHerder.class.getClassLoader().getResource(
				"com/mele/tapHerder/log4j.xml");
		if (loggingConfig != null) {
			System.out.println("Logging with config: "
					+ loggingConfig.getFile());

			DOMConfigurator.configure(loggingConfig);
		} else {
			System.out.println("Logging configuration not found.");
		}
		log.info("Logging configured.");
	}
	
	/**
	 * Set up a new game board and initiate the main run loop.
	 */
	public static void startGame() {
		IGameManager gm = new TapHerderGameManager();
		
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
	 * @param args
	 */
	public static void main(String args[]) {
		configureLogging();		
		startGame();
	}
}
