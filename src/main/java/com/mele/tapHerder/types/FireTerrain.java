package com.mele.tapHerder.types;

import com.mele.games.animation.ERenderPass;
import com.mele.games.animation.SpriteAnimated;
import com.mele.games.animation.SpriteFrame;
import com.mele.games.hex.ui.CellTypeMetadata;


@CellTypeMetadata(symbol="^")
@SpriteAnimated(spriteTag="FIRE", 
frames = { 
		@SpriteFrame(frameCount = 1, frameVariation = 2, imageName = "/Fire_0.png"),
		@SpriteFrame(frameCount = 4, frameVariation = 2, imageName = "/Fire_1.png"),
		@SpriteFrame(frameCount = 1, frameVariation = 2, imageName = "/Fire_2.png"),
		@SpriteFrame(frameCount = 4, frameVariation = 2, imageName = "/Fire_1.png")
		},
renderPass = ERenderPass.BOTTOM
	)
public class FireTerrain extends BaseTerrainType {

	public FireTerrain() {
		super(false, null, true, false);
	}
	

}
