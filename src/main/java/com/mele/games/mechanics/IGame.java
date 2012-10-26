package com.mele.games.mechanics;

/**
 * Game interface.
 * 
 * @author Ayar
 *
 */
public interface IGame {
	public void setState(EGameState state);
	public EGameState getState();
	public void mainGameLoop();
	public boolean processCommand(ICommand cmd);
	public void setGameManager(IGameManager gameManager);
}
