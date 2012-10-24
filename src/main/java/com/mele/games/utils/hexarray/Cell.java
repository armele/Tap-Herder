package com.mele.games.utils.hexarray;


public class Cell {
	protected HexArray parent = null;
	protected HexPoint point = null;
	
	/**
	 * @param x
	 * @param y
	 */
	protected Cell(HexPoint point) {
		this.point = point;
	}
	
	public Cell() {
		
	}
	
	public void setPoint(HexPoint point) {
		this.point = point;
	}
	
	public Cell findAdjacentCell(EHexVector vector) {
		HexPoint newPoint = point.adjacent(vector);
		return parent.getCell(newPoint);
	}
	
	public void setParent(HexArray map) {
		parent = map;
	}
	
	public HexArray getParent() {
		return parent;
	}
	
	public HexPoint getPoint() {
		return point;
	}
}
