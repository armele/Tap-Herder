package com.mele.tapHerder.residents;

import com.mele.games.animation.ERenderPass;
import com.mele.games.animation.SpriteAnimated;
import com.mele.games.animation.SpriteFrame;
import com.mele.games.hex.ui.HexCell;
import com.mele.games.hex.ui.ResidentMetadata;
import com.mele.games.mechanics.ScoreEvent;
import com.mele.tapHerder.types.BaseTerrainType;

@ResidentMetadata(symbol="O")
@SpriteAnimated(spriteTag="OCELOT", 
frames = { 
		@SpriteFrame(frameCount = 2, frameVariation = 2, imageName = "/Ocelot_0.png"),
		@SpriteFrame(frameCount = 1, frameVariation = 2, imageName = "/Ocelot_1.png"),
		@SpriteFrame(frameCount = 2, frameVariation = 2, imageName = "/Ocelot_0.png"),
		@SpriteFrame(frameCount = 1, frameVariation = 2, imageName = "/Ocelot_2.png"),
		@SpriteFrame(frameCount = 1, frameVariation = 0, imageName = "/Ocelot_3.png"),
		@SpriteFrame(frameCount = 2, frameVariation = 2, imageName = "/Ocelot_4.png")
		},
renderPass = ERenderPass.MIDDLE
	)
public class Ocelot extends BaseResident {
	public static final String NAME = "O";
	
	public Ocelot() {
		setName(NAME);
		sensitivity = 2;
	}
	
	@Override
	public void kill() {
		game.getScoreLog().addScore(ScoreEvent.SCORE_DEADRESIDENT);
	}

	@Override
	protected void moveTo(HexCell neighbor) {
		BaseTerrainType type = (BaseTerrainType) neighbor.getType();
		goodResidentMove(neighbor, type);	
	}
}
