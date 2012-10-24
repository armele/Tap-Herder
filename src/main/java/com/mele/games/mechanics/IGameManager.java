package com.mele.games.mechanics;

public interface IGameManager extends Runnable {
	public EGameState state();
	public void startGame();
	public void endGame();
	public IGameRenderer getRenderer();
	public IGame getGame();
	public IGameInput getGameInput();
}
