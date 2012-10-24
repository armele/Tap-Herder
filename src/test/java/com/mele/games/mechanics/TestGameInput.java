package com.mele.games.mechanics;

public class TestGameInput implements IGameInput {
	protected int runCount = 0;
	protected boolean command = false;
	protected boolean kill = false;
	
	public void run() {
		while (!kill) {
			runCount++;
		}
	}

	@Override
	public ICommand readCommand() {
		command = true;
		return null;
	}

	/**
	 * @return the runCount
	 */
	public int getRunCount() {
		return runCount;
	}

	/**
	 * @param runCount the runCount to set
	 */
	public void setRunCount(int runCount) {
		this.runCount = runCount;
	}

	/**
	 * @return the command
	 */
	public boolean isCommand() {
		return command;
	}

	/**
	 * @param command the command to set
	 */
	public void setCommand(boolean command) {
		this.command = command;
	}

	/**
	 * @return the kill
	 */
	public boolean isKill() {
		return kill;
	}

	/**
	 * @param kill the kill to set
	 */
	public void setKill(boolean kill) {
		this.kill = kill;
	}
	
	public void kill() {
		kill = true;
	}

	@Override
	public void setGameManager(IGameManager game) {
		// TODO Auto-generated method stub
		
	}

}
