package com.mele.tapHerder.types;

import java.awt.Color;

import com.mele.games.animation.ERenderPass;
import com.mele.games.animation.SpriteAnimated;
import com.mele.games.animation.SpriteFrame;
import com.mele.games.hex.ui.CellTypeMetadata;


@CellTypeMetadata(symbol="G")
@SpriteAnimated(spriteTag="GOAL", 
frames = { 
		@SpriteFrame(frameCount = 1, frameVariation = 0, imageName = "/Goal1.png"),
		@SpriteFrame(frameCount = 1, frameVariation = 0, imageName = "/Goal2.png"),
		@SpriteFrame(frameCount = 1, frameVariation = 0, imageName = "/Goal3.png"),
		@SpriteFrame(frameCount = 1, frameVariation = 0, imageName = "/Goal4.png"),
		@SpriteFrame(frameCount = 1, frameVariation = 0, imageName = "/Goal5.png"),
		@SpriteFrame(frameCount = 2, frameVariation = 0, imageName = "/Goal6.png"),
		@SpriteFrame(frameCount = 1, frameVariation = 0, imageName = "/Goal5.png"),	
		@SpriteFrame(frameCount = 1, frameVariation = 0, imageName = "/Goal4.png"),
		@SpriteFrame(frameCount = 1, frameVariation = 0, imageName = "/Goal3.png"),
		@SpriteFrame(frameCount = 1, frameVariation = 0, imageName = "/Goal2.png"),	
		@SpriteFrame(frameCount = 1, frameVariation = 0, imageName = "/Goal1.png"),		
		},
renderPass = ERenderPass.BOTTOM
	)
public class GoalTerrain extends BaseTerrainType {

	public GoalTerrain() {
		super(false, null, false, true);
		backgroundColor = new Color(200, 0, 200, 125);
	}
	
}
