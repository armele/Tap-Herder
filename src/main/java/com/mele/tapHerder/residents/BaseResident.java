package com.mele.tapHerder.residents;

import java.util.HashMap;
import java.util.Map;

import com.mele.games.utils.hexarray.EHexVector;
import com.mele.tapHerder.TapHerderCell;
import com.mele.tapHerder.TapHerderGame;

/**
 * Defines attributes which all resident types will share.
 * 
 * @author Ayar
 *
 */
public abstract class BaseResident implements IGoodResident {
	protected Map<String, String> properties = new HashMap<String, String>();
	protected TapHerderGame game = null;
	protected int moveLength = 1;
	protected String name = null;
	
	public BaseResident() {
		
	}
	
	public String getProperty(String key) {
		return properties.get(key);
	}
	
	public void setGame(TapHerderGame game) {
		this.game = game;
	}
	
	public void setProperty(String key, String value) {
		properties.put(key, value);
	}

	/**
	 * @return the moveLength
	 */
	public int getMoveLength() {
		return moveLength;
	}

	/**
	 * @param moveLength the moveLength to set
	 */
	public void setMoveLength(int moveLength) {
		this.moveLength = moveLength;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 */
	public abstract void kill();
	
	public abstract void react(TapHerderCell homeCell, EHexVector vector); 
}
