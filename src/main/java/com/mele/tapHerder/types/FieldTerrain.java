package com.mele.tapHerder.types;

import com.mele.games.hex.ui.CellTypeMetadata;

@CellTypeMetadata(symbol="-")
public class FieldTerrain extends BaseTerrainType {

	public FieldTerrain() {
		super(false, null, false, false);
	}

}
