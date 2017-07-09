package com.mele.games.mechanics;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mele.games.utils.GameException;
import com.mele.tapHerder.TapHerderGame;

/**
 * Base class which is "game agnostic" as to the game, renderer and input being
 * managed.  This class handles the game state, and the game thread.
 * @author Ayar
 *
 */
public abstract class BaseGameManager  {
	protected static Logger log = LogManager.getLogger(BaseGameManager.class);

	protected TapHerderGame game = new TapHerderGame();
	protected EGameState managerState = EGameState.UNINITIALIZED;
	protected static long NORMAL_PAUSE = 50;

	/**
	 * @return
	 */
	public EGameState state() {
		EGameState state = managerState;
		
		if ((game != null) && (managerState == EGameState.UNINITIALIZED)) {
			state = game.getGameState();
		}
		return state;
	}

	/**
	 * 
	 */
	public void startGame() {
		log.info("Starting game manager...");
		initialize(); 
		
		if (game != null) {
			runGame();
		} else {
			managerState = EGameState.ERROR;
			String error = "Input, renderer or game not set by game manager.";
			log.error(error);
			throw new GameException(error);
		}
	}

	
	/**
	 * 
	 */
	public void endGame() {
		game.setGameState(EGameState.HALTED);
		cleanup();
	}

	/**
	 * Initialization in the game manager must include assignment of the renderer and command input.
	 */
	abstract protected void initialize();
	abstract protected void cleanup();
	
	/**
	 * Pause the game for the specified number of seconds.  A negative
	 * value should force the use of the default pause internal.
	 * 
	 * @param seconds
	 */
	public void rest(long milliseconds) {
		if (milliseconds > 0) {	
			try {
				Thread.sleep(milliseconds);
			} catch (Exception ie) {
				log.error(ie.getLocalizedMessage());
				game.setGameState(EGameState.ERROR);
			}
		}
	}
	
	/**
	 * Indicates whether the game loop should terminate
	 * @return
	 */
	protected boolean isStopped() {
		boolean stopped = false;
		
		if ((game == null) || (game.getGameState() == EGameState.HALTED || game.getGameState() == EGameState.ERROR)) {
			stopped = true;
		}
		
		return stopped;
	}
	
	
	/**
	 * Main loop - keeps the renderer displaying, and the input reads coming, feeding them 
	 * all to the game itself.
	 */
	protected void runGame() {
		int tick = 0;
		game.setGameState(EGameState.IN_PROGRESS);
		
		while (!isStopped()) {
			long passStart = (new Date()).getTime();

			game.mainGameLoop(tick);
			
			// Throttle such that the pause varies based on how much time was 
			// spent in the game loop.  Result should be a constant loop time
			// unless the game loop exceeds the normal pause duration.
			long passEnd = (new Date()).getTime();
			rest (NORMAL_PAUSE - (passEnd - passStart));
			tick++;
			game.display();
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		startGame();
	}
}
