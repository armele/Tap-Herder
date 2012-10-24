package com.mele.games.mechanics;

import org.apache.log4j.Logger;

/**
 * @author Ayar
 *
 */
public class TestGame implements IGame {
	protected static Logger log = Logger.getLogger(TestGame.class);
	protected EGameState state = EGameState.UNINITIALIZED;
	protected int gameLoopCount = 0;
	protected int commandCount = 0;
	
	@Override
	public void setState(EGameState state) {
		this.state = state;
	}

	@Override
	public EGameState getState() {
		return state;
	}

	@Override
	public void mainGameLoop() {
		gameLoopCount++;
	}

	@Override
	public boolean processCommand(ICommand cmd) {
		log.info(cmd);
		commandCount++;
		return false;
	}

	@Override
	public void setGameManager(IGameManager gameManager) {
		// TODO Auto-generated method stub
		
	}

}
