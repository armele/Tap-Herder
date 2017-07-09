package com.mele.tapHerder.types;

import com.mele.games.animation.ERenderPass;
import com.mele.games.animation.SpriteAnimated;
import com.mele.games.animation.SpriteFrame;
import com.mele.games.hex.ui.CellTypeMetadata;

@CellTypeMetadata(symbol="&")
@SpriteAnimated(spriteTag="STATUE", 
frames = { @SpriteFrame(frameCount = 1, frameVariation = 0, imageName = "/Statue.png") },
renderPass = ERenderPass.BOTTOM
	)
public class StatueTerrain extends BaseTerrainType {

	public StatueTerrain() {
		super(true, null, false, false);
	}

}
