package com.mele.tapHerder.types;

import com.mele.games.animation.ERenderPass;
import com.mele.games.animation.SpriteAnimated;
import com.mele.games.animation.SpriteFrame;
import com.mele.games.hex.ui.CellTypeMetadata;

@CellTypeMetadata(symbol="8")
@SpriteAnimated(spriteTag="BOULDER", 
frames = { @SpriteFrame(frameCount = 1, frameVariation = 0, imageName = "/Boulder.png") },
renderPass = ERenderPass.BOTTOM
	)
public class BoulderTerrain extends BaseTerrainType {

	public BoulderTerrain() {
		super(true, null, false, false);
	}
	

}
