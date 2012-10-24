package com.mele.games.mechanics;

public interface IGameInput {
	public void setGameManager(IGameManager game);
	public ICommand readCommand();
	public void kill();
}
