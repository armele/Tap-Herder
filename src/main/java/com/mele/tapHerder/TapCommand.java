package com.mele.tapHerder;

import com.mele.games.mechanics.ICommand;
import com.mele.games.utils.hexarray.Cell;

/**
 * Command representing a "tap" on a given hex.
 * 
 * @author Ayar
 *
 */
public class TapCommand implements ICommand {
	protected Cell tapLocation = null;

	/**
	 * @return the tapLocation
	 */
	public Cell getTapLocation() {
		return tapLocation;
	}

	/**
	 * @param tapLocation the tapLocation to set
	 */
	public void setTapLocation(Cell tapLocation) {
		this.tapLocation = tapLocation;
	}
	
	
}
