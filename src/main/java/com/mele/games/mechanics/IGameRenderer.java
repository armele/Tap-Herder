package com.mele.games.mechanics;

public interface IGameRenderer {
	public void setGameManager(IGameManager game);
	public void kill();
	public void display();
}
