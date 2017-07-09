package com.mele.tapHerder.types;

import com.mele.games.animation.ERenderPass;
import com.mele.games.animation.SpriteAnimated;
import com.mele.games.animation.SpriteFrame;
import com.mele.games.hex.ui.CellTypeMetadata;


@CellTypeMetadata(symbol="%")
@SpriteAnimated(spriteTag="BRY_BRUSH", 
frames = { @SpriteFrame(frameCount = 1, frameVariation = 0, imageName = "/DryBrush.png") },
renderPass = ERenderPass.BOTTOM
	)
public class DryBrushTerrain extends BaseTerrainType {

	public DryBrushTerrain() {
		super(true, new FireTerrain(), false, false);
	}
	

}
