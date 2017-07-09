package com.mele.tapHerder.types;

import com.mele.games.animation.ERenderPass;
import com.mele.games.animation.SpriteAnimated;
import com.mele.games.animation.SpriteFrame;
import com.mele.games.hex.ui.CellTypeMetadata;

@CellTypeMetadata(symbol="+")
@SpriteAnimated(spriteTag="FENCE", 
frames = { @SpriteFrame(frameCount = 1, frameVariation = 0, imageName = "/Fence.png") },
renderPass = ERenderPass.BOTTOM
	)
public class FenceTerrain extends BaseTerrainType {

	public FenceTerrain() {
		super(true, new FieldTerrain(), false, false);
	}
	

}
