package com.mele.games.util.hexarray;

import junit.framework.Assert;

import org.junit.Test;

import com.mele.games.utils.hexarray.Cell;
import com.mele.games.utils.hexarray.EHexVector;
import com.mele.games.utils.hexarray.HexArray;
import com.mele.games.utils.hexarray.HexPoint;

public class HexArray_Tests {
	@Test
	public void testCartesianCreation() {
		HexPoint point = new HexPoint();
		point.setCartesianPoint(1, 1); // A1
		
		Assert.assertEquals(1, point.getX());
		Assert.assertEquals(1, point.getY());

		point.setCartesianPoint(1, 8); // A8
		
		Assert.assertEquals(1, point.getX());
		Assert.assertEquals(8, point.getY());
		
		point = new HexPoint();
		point.setCartesianPoint(4, 1); // D3
		
		Assert.assertEquals(4, point.getX());
		Assert.assertEquals(3, point.getY());
		
		point = new HexPoint();
		point.setCartesianPoint(5, 4); // E6
		
		Assert.assertEquals(5, point.getX());
		Assert.assertEquals(6, point.getY());
	}
	
	@Test
	public void testMapCreation() {
		HexArray map = new HexArray(4, 5, Cell.class);
		
		Assert.assertEquals(18, map.size());
		
		Cell a1 = map.getCell(new HexPoint(1, 1));
		
		Assert.assertEquals(1, a1.getPoint().getX());
		Assert.assertEquals(1, a1.getPoint().getY());
		
		Cell d6 = map.getCell(new HexPoint(4, 6));
		
		Assert.assertEquals(4, d6.getPoint().getX());
		Assert.assertEquals(6, d6.getPoint().getY());		
	}
	
	@Test
	public void testMapNavigation() {
		HexArray map = new HexArray(4, 5, Cell.class);
		
		Assert.assertEquals(18, map.size());
		
		Cell a1 = map.getCell(new HexPoint(1, 1));
		
		Assert.assertEquals(1, a1.getPoint().getX());
		Assert.assertEquals(1, a1.getPoint().getY());
		
		Cell next = a1.findAdjacentCell(EHexVector.SOUTH);
		
		Assert.assertEquals(1, next.getPoint().getX());
		Assert.assertEquals(2, next.getPoint().getY());
		
		next = next.findAdjacentCell(EHexVector.NORTHEAST);
		
		Assert.assertEquals(2, next.getPoint().getX());
		Assert.assertEquals(2, next.getPoint().getY());		
		
		next = next.findAdjacentCell(EHexVector.SOUTH);
		
		Assert.assertEquals(2, next.getPoint().getX());
		Assert.assertEquals(3, next.getPoint().getY());	
		
		next = next.findAdjacentCell(EHexVector.SOUTHEAST);
		
		Assert.assertEquals(3, next.getPoint().getX());
		Assert.assertEquals(4, next.getPoint().getY());	
		
		next = next.findAdjacentCell(EHexVector.NORTHWEST);
		
		Assert.assertEquals(2, next.getPoint().getX());
		Assert.assertEquals(3, next.getPoint().getY());		
		
		next = next.findAdjacentCell(EHexVector.NORTHEAST);
		
		Assert.assertEquals(3, next.getPoint().getX());
		Assert.assertEquals(3, next.getPoint().getY());		
		
		next = next.findAdjacentCell(EHexVector.NORTHWEST);
		
		Assert.assertEquals(2, next.getPoint().getX());
		Assert.assertEquals(2, next.getPoint().getY());		
		
		next = next.findAdjacentCell(EHexVector.SOUTHWEST);
		
		Assert.assertEquals(1, next.getPoint().getX());
		Assert.assertEquals(2, next.getPoint().getY());	
		
		next = next.findAdjacentCell(EHexVector.SOUTHWEST);		
		
		Assert.assertNull (next);			
	}
}
