package com.mele.tapHerder.residents;

import com.mele.games.animation.ERenderPass;
import com.mele.games.animation.SpriteAnimated;
import com.mele.games.animation.SpriteFrame;
import com.mele.games.hex.ui.HexCell;
import com.mele.games.hex.ui.ResidentMetadata;
import com.mele.games.mechanics.ScoreEvent;
import com.mele.tapHerder.types.BaseTerrainType;



/**
 * Defines the "Dog" resident.
 * 
 * @author Ayar
 *
 */
@ResidentMetadata(symbol="D")
@SpriteAnimated(spriteTag="DOG", 
frames = { 
		@SpriteFrame(frameCount = 5, frameVariation = 6, imageName = "/150px-Wolf_(Tamed)_0.png"),
		@SpriteFrame(frameCount = 1, frameVariation = 2, imageName = "/150px-Wolf_(Tamed)_1.png")
		},
renderPass = ERenderPass.MIDDLE
	)
public class Dog extends BaseResident implements IGoodResident {
	public static final String NAME = "D";
	
	public Dog() {
		setName(NAME);
	}
	
	/**
	 * @param neighbor
	 */
	protected void moveTo(HexCell neighbor) {
		BaseTerrainType type = (BaseTerrainType) neighbor.getType();
		goodResidentMove(neighbor, type);	
	}
	
	/* (non-Javadoc)
	 * @see com.mele.tapHerder.residents.BaseResident#kill()
	 */
	public void kill() {
		game.getScoreLog().addScore(ScoreEvent.SCORE_DEADRESIDENT);
		motion.setInMotion(false);
	}
	
}
