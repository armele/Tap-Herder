package com.mele.games.mechanics;

import java.util.Date;

import org.apache.log4j.Logger;

import com.mele.games.utils.GameException;

public abstract class BaseGameManager implements IGameManager {
	protected static Logger log = Logger.getLogger(BaseGameManager.class);
	
	protected IGameInput input = null;
	protected IGameRenderer renderer = null;
	protected IGame game = null;
	protected EGameState managerState = EGameState.UNINITIALIZED;
	protected static long NORMAL_PAUSE = 50;
	
	@Override
	public EGameState state() {
		EGameState state = managerState;
		
		if ((game != null) && (managerState == EGameState.UNINITIALIZED)) {
			state = game.getState();
		}
		return state;
	}

	protected void startRenderer() {
		//new Thread(renderer).start();
	}
	
	protected void startInput() {
		//new Thread(input).start();
	}
	
	@Override
	public void startGame() {
		log.info("Starting game manager...");
		initialize();
		
		if ((input != null) && (renderer != null) && (game != null)) {
			startRenderer();
			startInput();
			runGame();
		} else {
			managerState = EGameState.ERROR;
			String error = "Input, renderer or game not set by game manager.";
			log.error(error);
			throw new GameException(error);
		}
	}

	@Override
	public void endGame() {
		game.setState(EGameState.HALTED);
		cleanup();
		input.kill();
		renderer.kill();
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
				game.setState(EGameState.ERROR);
			}
		}
	}
	
	/**
	 * Indicates whether the game loop should terminate
	 * @return
	 */
	protected boolean isStopped() {
		boolean stopped = false;
		
		if ((game == null) || (game.getState() == EGameState.HALTED || game.getState() == EGameState.ERROR)) {
			stopped = true;
		}
		
		return stopped;
	}
	
	
	/**
	 * Main loop - keeps the renderer displaying, and the input reads coming, feeding them 
	 * all to the game itself.
	 */
	protected void runGame() {

		game.setState(EGameState.IN_PROGRESS);
		
		while (!isStopped()) {
			long passStart = (new Date()).getTime();
			
			ICommand cmd = input.readCommand();

			if (cmd != null) {
				game.processCommand(cmd);
			}

			game.mainGameLoop();
			
			// Throttle such that the pause varies based on how much time was 
			// spent in the game loop.  Result should be a constant loop time
			// unless the game loop exceeds the normal pause duration.
			long passEnd = (new Date()).getTime();
			rest (NORMAL_PAUSE - (passEnd - passStart));
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		startGame();
	}
}
