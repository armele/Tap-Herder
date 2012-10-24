package com.mele.games.utils.hexarray;

/**
 * Represents the directions of hexagonal cell neighbors.
 * 
 * @author Ayar
 *
 */
public enum EHexVector {
	NORTH	 ,
	NORTHEAST,
	SOUTHEAST,
	SOUTH	 ,
	SOUTHWEST,
	NORTHWEST;
	
	public EHexVector reverse() {
		EHexVector reverse = null;
		
		switch (this) {
		case NORTH:
			reverse = SOUTH;
			break;
		case NORTHEAST:
			reverse = SOUTHWEST;
			break;
		case SOUTHEAST:
			reverse = NORTHWEST;
			break;
		case SOUTH:
			reverse = NORTH;
			break;
		case SOUTHWEST:
			reverse = NORTHEAST;
			break;
		case NORTHWEST:
			reverse = SOUTHEAST;
			break;
		}
		
		return reverse;
	}
}
