package com.mele.games.mechanics;

/**
 * Game input interface
 * @author Ayar
 *
 */
public interface IGameInput {
	public void setGameManager(IGameManager game);
	public ICommand readCommand();
	public void kill();
}
