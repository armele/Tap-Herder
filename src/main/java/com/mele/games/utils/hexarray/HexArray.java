package com.mele.games.utils.hexarray;

import java.util.HashMap;
import java.util.Iterator;

import com.mele.games.utils.GameException;

/**
 * This implementation of an array uses a 2-dimensional array to represent a grid of
 * hex spaces.  In the illustration below, adjacent hexes are considered those diagonal
 * and those up and down.
 * 
 * For example, C3 is adjacent to C2, D3, D4, C4, B3 and B2.
 * 
 * Note that letter assignment is vertical, while numeric assignment is diagonal.
 * 
 * There are "fake" spaces when represented this way that would not actually be present 
 * on a visual representation.  For example, there is no A4 cell despite the fact that
 * one axis of the grid includes A and another includes 4.
 * 
 *   A   B   C   D   E
 *  [A1]	[C2]    [E3]
 * 1    [B2]    [D3]
 *  [A2]    [C3]    [E4]
 * 2    [B3]    [D4]
 *  [A3]    [C4]    [E5]
 * 3
 * 
 * 4
 * 
 * 5
 * 
 * @author Ayar
 *
 */
public class HexArray implements Iterable<Cell> {
	/**
	 * The number of columns in the hex map
	 */
	protected int xsize = 0;
	
	
	/**
	 * The number of cells in the first column of the hex map 
	 * (every odd column - with 0-based counting - will have
	 * one fewer cell than its even neighbors) 
	 */
	protected int ysize = 0;
	
	protected HashMap<HexPoint, Cell> hexmap = new HashMap<HexPoint, Cell>();
	protected Class<?> cellClass = null;
	
	/**
	 * Provide the number of columns and the number of cells in the first column.
	 * 
	 * @param columns
	 * @param rows
	 */
	public HexArray(int columns, int rows, Class<?> cellClass) {
		this.cellClass = cellClass;
		create(columns, rows);
	}
	
	public HexArray(Class<?> cellClass) {
		this.cellClass = cellClass;
	}
	
	/**
	 * Populate the hex array with cells.
	 * 
	 * @param columns
	 * @param rows
	 */
	public void create(int columns, int rows) {
		xsize = columns;
		ysize = rows;
	
		// Map is created using cartesian coordinates.
		for (int x = 1; x <= xsize; x++) {
			int cells = ysize - ((x-1)%2);
			for (int y = 1; y <= cells; y++) {
				HexPoint point = new HexPoint();
				point.setCartesianPoint(x, y);
				Cell c;
				try {
					c = (Cell)cellClass.newInstance();
					c.setPoint(point);
				} catch (InstantiationException e) {
					throw new GameException("Could not instantiate cell class.", e);
				} catch (IllegalAccessException e) {
					throw new GameException("Could not instantiate cell class.", e);
				}
				c.setParent(this);
				hexmap.put(point, c);
			}
		}
	}
	
	public Cell getCell(int x, int y) {
		HexPoint point = new HexPoint(x, y);
		return getCell(point);
	}
	
	public Cell getCell(HexPoint point) {
		return hexmap.get(point);
	}
	
	@Override
	public Iterator<Cell> iterator() {
		return hexmap.values().iterator();
	}

	public int size() {
		return hexmap.size();
	}
	
	public int getRows() {
		return ysize;
	}
	
	public int getColumns() {
		return xsize;
	}
}