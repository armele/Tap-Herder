package com.mele.games.mechanics;

/**
 * @author Ayar
 *
 */
public class TestGameRenderer implements IGameRenderer {
	protected boolean kill = false;
	protected boolean ran = false;
	protected int displayCount = 0;
	protected IGameManager gameManager = null;
	
	public void run() {
		while (!kill) {
			ran = true;
		}
	}

	/**
	 * @return the ran
	 */
	public boolean isRan() {
		return ran;
	}

	/**
	 * @param ran the ran to set
	 */
	public void setRan(boolean ran) {
		this.ran = ran;
	}

	/**
	 * @return the displayCount
	 */
	public int getDisplayCount() {
		return displayCount;
	}

	/**
	 * @param displayCount the displayCount to set
	 */
	public void setDisplayCount(int displayCount) {
		this.displayCount = displayCount;
	}

	@Override
	public void kill() {
		kill = true;
	}

	public boolean isKill() {
		return kill;
	}

	@Override
	public void setGameManager(IGameManager gameManager) {
		this.gameManager = gameManager;
	}

	@Override
	public void display() {
		displayCount++;
		
	}

}
