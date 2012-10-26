package com.mele.games.mechanics;

/**
 * Defines the interface for the basic game manager mechanics.
 * 
 * @author Ayar
 *
 */
public interface IGameManager extends Runnable {
	public EGameState state();
	public void startGame();
	public void endGame();
	public IGameRenderer getRenderer();
	public IGame getGame();
	public IGameInput getGameInput();
}
