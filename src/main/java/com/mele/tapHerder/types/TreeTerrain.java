package com.mele.tapHerder.types;

import com.mele.games.animation.ERenderPass;
import com.mele.games.animation.SpriteAnimated;
import com.mele.games.animation.SpriteFrame;
import com.mele.games.hex.ui.CellTypeMetadata;


@CellTypeMetadata(symbol="T")
@SpriteAnimated(spriteTag="TREE", 
frames = { @SpriteFrame(frameCount = 1, frameVariation = 0, imageName = "/Tree.png") },
renderPass = ERenderPass.BOTTOM
	)
public class TreeTerrain extends BaseTerrainType {

	public TreeTerrain() {
		super(true, new FieldTerrain(), false, false);
	}

}
