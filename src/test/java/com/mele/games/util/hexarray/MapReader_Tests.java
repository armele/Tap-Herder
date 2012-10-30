package com.mele.games.util.hexarray;

import junit.framework.Assert;

import org.junit.Test;

import com.mele.games.utils.hexarray.HexArray;
import com.mele.tapHerder.ETerrainType;
import com.mele.tapHerder.MapReader;
import com.mele.tapHerder.TapHerderCell;
import com.mele.tapHerder.TapHerderGame;
import com.mele.tapHerder.residents.BaseResident;
import com.mele.tapHerder.residents.Dog;


public class MapReader_Tests {
	@Test
	public void testFileRead() throws Exception {
		TapHerderGame game = new TapHerderGame();
		MapReader mr = new MapReader();
		HexArray hexMap = new HexArray(14, 8, TapHerderCell.class);
		
		mr.setMapTerrain(game, hexMap, "testMap.map");
		
		Assert.assertEquals(ETerrainType.FIELD, ((TapHerderCell)hexMap.getCell(1, 1)).getType());
		Assert.assertEquals(ETerrainType.LONG_GRASS, ((TapHerderCell)hexMap.getCell(2, 2)).getType());

		BaseResident res =  ((TapHerderCell)hexMap.getCell(3, 3)).getResident();
		if  (!(res instanceof Dog )) {
			Assert.fail("No Dog found at 3,3");
		} 
		
		//TODO: Full verification of testMap graph
	}
}
