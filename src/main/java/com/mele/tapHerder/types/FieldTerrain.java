package com.mele.tapHerder.types;

import com.mele.games.animation.AnimationMethod;
import com.mele.games.animation.ERenderPass;
import com.mele.games.animation.SpriteAnimated;
import com.mele.games.animation.SpriteFrame;
import com.mele.games.hex.ui.CellTypeMetadata;

@CellTypeMetadata(symbol="-")
@SpriteAnimated(spriteTag="FIELD", 
frames = { 
		@SpriteFrame(frameCount = 1, frameVariation = 2, imageName = "/Field.png"),
		@SpriteFrame(frameCount = 1, frameVariation = 2, imageName = "/Field1.png"),
		@SpriteFrame(frameCount = 1, frameVariation = 2, imageName = "/Field2.png"),
		@SpriteFrame(frameCount = 1, frameVariation = 2, imageName = "/Field3.png")
		},
renderPass = ERenderPass.BOTTOM,
animationMethod = AnimationMethod.RANDOM
	)
public class FieldTerrain extends BaseTerrainType {

	public FieldTerrain() {
		super(false, null, false, false);
	}

}
