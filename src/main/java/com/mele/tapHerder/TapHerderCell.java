package com.mele.tapHerder;

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
}
