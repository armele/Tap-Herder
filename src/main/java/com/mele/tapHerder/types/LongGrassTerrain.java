package com.mele.tapHerder.types;

import com.mele.games.animation.ERenderPass;
import com.mele.games.animation.SpriteAnimated;
import com.mele.games.animation.SpriteFrame;
import com.mele.games.hex.ui.CellTypeMetadata;


@CellTypeMetadata(symbol="#")
@SpriteAnimated(spriteTag="LONG_GRASS", 
frames = { @SpriteFrame(frameCount = 1, frameVariation = 0, imageName = "/TallGrass.png") },
renderPass = ERenderPass.BOTTOM
	)
public class LongGrassTerrain extends BaseTerrainType {

	public LongGrassTerrain() {
		super(false, null, false, true);
	}
	

}
