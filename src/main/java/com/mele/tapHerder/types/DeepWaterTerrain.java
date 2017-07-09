package com.mele.tapHerder.types;

import com.mele.games.animation.ERenderPass;
import com.mele.games.animation.SpriteAnimated;
import com.mele.games.animation.SpriteFrame;
import com.mele.games.hex.ui.CellTypeMetadata;

@CellTypeMetadata(symbol="~")
@SpriteAnimated(spriteTag="DEEP_WATER", 
frames = { @SpriteFrame(frameCount = 1, frameVariation = 0, imageName = "/Grid_Water.png") },
renderPass = ERenderPass.BOTTOM
	)
public class DeepWaterTerrain extends BaseTerrainType {

	public DeepWaterTerrain() {
		super(true, null, false, false);
	}
	

}
