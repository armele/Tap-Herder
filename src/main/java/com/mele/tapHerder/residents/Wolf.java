package com.mele.tapHerder.residents;

import com.mele.games.animation.ERenderPass;
import com.mele.games.animation.SpriteAnimated;
import com.mele.games.animation.SpriteFrame;
import com.mele.games.hex.ui.HexCell;
import com.mele.games.hex.ui.ResidentMetadata;
import com.mele.games.mechanics.ScoreEvent;
import com.mele.tapHerder.TapHerderGame;
import com.mele.tapHerder.types.BaseTerrainType;

/**
 * Defines the "Wolf" anti-resident.
 * 
 * @author Ayar
 *
 */
@ResidentMetadata(symbol="W")
@SpriteAnimated(spriteTag="WOLF", 
frames = { 
		@SpriteFrame(frameCount = 2, frameVariation = 3, imageName = "/150px-Wolf_(Aggressive)_0.png"),
		@SpriteFrame(frameCount = 2, frameVariation = 4, imageName = "/150px-Wolf_(Aggressive)_1.png"),
		@SpriteFrame(frameCount = 2, frameVariation = 3, imageName = "/150px-Wolf_(Aggressive)_2.png"),
		@SpriteFrame(frameCount = 2, frameVariation = 4, imageName = "/150px-Wolf_(Aggressive)_1.png")
		},
renderPass = ERenderPass.MIDDLE
	)
public class Wolf extends BaseResident implements IBadResident {
	public static final String NAME = "W";
	
	public Wolf() {
		setName(NAME);
	}
	
	/* (non-Javadoc)
	 * @see com.mele.tapHerder.residents.BaseResident#moveTo(com.mele.tapHerder.Cell)
	 */
	protected void moveTo(HexCell neighbor) {
		BaseTerrainType type = (BaseTerrainType) neighbor.getType();
		badResidentMove(neighbor, type);
	}
	
	/* (non-Javadoc)
	 * @see com.mele.tapHerder.residents.BaseResident#kill()
	 */
	public void kill() {
		TapHerderGame.getScoreLog().addScore(ScoreEvent.SCORE_DEADANTIRES);
	}
}
