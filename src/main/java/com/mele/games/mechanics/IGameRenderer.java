package com.mele.games.mechanics;

/**
 * Defines the interface for the basic rendering component
 * 
 * @author Ayar
 *
 */
public interface IGameRenderer {
	public void setGameManager(IGameManager game);
	public void kill();
	public void display();
}
