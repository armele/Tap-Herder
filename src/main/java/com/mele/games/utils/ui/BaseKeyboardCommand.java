package com.mele.games.utils.ui;

import com.mele.games.mechanics.ICommand;

public class BaseKeyboardCommand implements ICommand {
	protected String keyInput = null;

	BaseKeyboardCommand(String val) {
		keyInput = val;
	}
	
	/**
	 * @return the keyInput
	 */
	public String getKeyInput() {
		return keyInput;
	}

	/**
	 * @param keyInput the keyInput to set
	 */
	public void setKeyInput(String keyInput) {
		this.keyInput = keyInput;
	}
	
	public String toString() {
		return keyInput;
	}
}
