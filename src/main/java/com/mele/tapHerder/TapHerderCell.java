package com.mele.tapHerder;

import java.util.HashMap;
import java.util.Map;

import com.mele.games.utils.hexarray.Cell;
import com.mele.games.utils.hexarray.HexPoint;
import com.mele.tapHerder.residents.BaseResident;

/**
 * Specific game implementation of a hexagonal array Cell.
 * This class defines aspects of a cell, such as what terrain
 * type it has and what resident resides within it.
 * 
 * @author Ayar
 *
 */
public class TapHerderCell extends Cell {
	protected BaseResident resident = null;
	protected ETerrainType type = ETerrainType.FIELD;
	protected Map<String, String> properties = new HashMap<String, String>();
	
	// Property key constants
	public static final String PROPKEY_NEWTYPE = "NEWTYPE";
	
	// Property value constants
	public static final String PROPVAL_TRUE = "TRUE";
	public static final String PROPVAL_FALSE = "FALSE";
	
	public TapHerderCell(HexPoint point) {
		super(point);
	}

	public TapHerderCell() {
		
	}
	
	protected boolean selected = false;

	/**
	 * @return the selected
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * @param selected the selected to set
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	/**
	 * @return the resident
	 */
	public BaseResident getResident() {
		return resident;
	}

	/**
	 * @param resident the resident to set
	 */
	public void setResident(BaseResident newResident) {
		this.resident = newResident;
	}

	/**
	 * @return the type
	 */
	public ETerrainType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(ETerrainType type) {
		this.type = type;
	}
	
	public void setProperty(String key, String value) {
		properties.put(key, value);
	}
	
	public String getProperty(String key) {
		return properties.get(key);
	}

}
