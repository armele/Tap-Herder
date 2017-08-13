package com.mele.tapHerder.types;

import java.awt.Color;

import com.mele.games.hex.ui.CellTypeMetadata;


@CellTypeMetadata(symbol=":")
public class PathTerrain extends BaseTerrainType {

	public PathTerrain() {
		super(false, null, false, false);
		backgroundColor = new Color(111, 77, 11); // Brown
	}
	

}
