package com.mele.games.utils.hexarray;

public class HexPoint {
	protected int x = 0;
	protected int y = 0;
	
	public HexPoint() {
		// No-op
	}
	
	public HexPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Given "cartesian" coordinates (where x is the number of columns over
	 * from the left of the map, and y is the number of cells down from the
	 * top of the map), convert them into hex coordinates (where x is the 
	 * number of columns over from the left of the map, but y represents
	 * the diagonal upon which the cell lies).
	 * 
	 * @param x
	 * @param cartesianY
	 */
	public void setCartesianPoint (int x, int cartesianY) {
		this.x = x;
		this.y = (x/2) + cartesianY;
	}
	
	public int getCartesianY() {
		return (y - (x/2));
	}
	
	/**
	 * Given a vector, return the point of a potential adjacent cell.
	 * Note - the actual hex map MAY NOT INCLUDE a hex at this point!
	 * 
	 * @param vector
	 * @return
	 */
	public HexPoint adjacent(EHexVector vector) {
		HexPoint point = null;
		int newx = 0;
		int newy = 0;
		
		switch (vector) {
		case NORTH: 
			newy = y-1;
			newx = x;
			break;
		case NORTHEAST:
			newx = x+1;
			newy = y;
			break;
		case SOUTHEAST:
			newx = x+1;
			newy = y+1;
			break;
		case SOUTH: 
			newx = x;
			newy = y+1;
			break;
		case SOUTHWEST:
			newx = x-1;
			newy = y;
			break;
		case NORTHWEST: 
			newx = x-1;
			newy = y-1;
			break;
		}
		
		point = new HexPoint(newx, newy);
		return point;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HexPoint other = (HexPoint) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	public String toString() {
		return "("+x+","+y+")";
	}
	
}
