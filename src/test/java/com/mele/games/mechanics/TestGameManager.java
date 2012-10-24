package com.mele.games.mechanics;

public class TestGameManager extends BaseGameManager {
	boolean initialized = false;
	boolean cleaned = false;
	
	TestGameManager(IGameInput testinput, IGameRenderer testrenderer) {
		input = testinput;
		renderer = testrenderer;
	}
	
	@Override
	protected void initialize() {
		game = new TestGame();
		initialized = true;
		
		if (input != null) {
			input.setGameManager(this);
		}
		if (renderer != null) {
			renderer.setGameManager(this);
		}
		
		game.setGameManager(this);

	}

	@Override
	protected void cleanup() {
		cleaned = true;
	}

	/**
	 * @return the initialized
	 */
	public boolean isInitialized() {
		return initialized;
	}

	/**
	 * @param initialized the initialized to set
	 */
	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}

	/**
	 * @return the cleaned
	 */
	public boolean isCleaned() {
		return cleaned;
	}

	/**
	 * @param cleaned the cleaned to set
	 */
	public void setCleaned(boolean cleaned) {
		this.cleaned = cleaned;
	}

	@Override
	public void run() {
		startGame();
	}

	@Override
	public IGameRenderer getRenderer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IGame getGame() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IGameInput getGameInput() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
