package com.mele.tapHerder.residents;

import com.mele.games.animation.ERenderPass;
import com.mele.games.animation.SpriteAnimated;
import com.mele.games.animation.SpriteFactoryDescriptor;
import com.mele.games.animation.SpriteFrame;
import com.mele.games.hex.ui.HexCell;
import com.mele.games.hex.ui.ResidentMetadata;
import com.mele.games.mechanics.ScoreEvent;
import com.mele.tapHerder.types.BaseTerrainType;

/**
 * Defines the "Sheep" resident.
 * 
 * @author Ayar
 *
 */
@ResidentMetadata(symbol="S")
@SpriteAnimated(spriteTag="SHEEP", 
frames = { 
		@SpriteFrame(frameCount = 1, frameVariation = 0, imageName = "/Sheep.png")
		},
renderPass = ERenderPass.MIDDLE
	)
public class Sheep extends BaseResident implements IGoodResident {
	public static final String NAME = "S";
	
	public Sheep() {
		setName(NAME);
		this.runDistance = 2;
	}
	

	@Override
	protected void moveTo(HexCell neighbor) {
		BaseTerrainType type = (BaseTerrainType) neighbor.getType();
		goodResidentMove(neighbor, type);	
	}
	
	/* (non-Javadoc)
	 * @see com.mele.tapHerder.residents.BaseResident#kill()
	 */
	public void kill() {
		game.getScoreLog().addScore(ScoreEvent.SCORE_DEADRESIDENT);
	}
	
	/**
	 * @return
	 */
	public static SpriteFactoryDescriptor descriptor() {
		SpriteFactoryDescriptor sd = new SpriteFactoryDescriptor();
		sd.addImageFrames("/Sheep.png", 1, 0);
		sd.setRenderPass(ERenderPass.TOP);	
		
		return sd;
	}	

}
