package com.mele.tapHerder.types;

import java.awt.Color;
import java.util.HashMap;

import com.mele.games.animation.ERenderPass;
import com.mele.games.animation.SpriteFactoryDescriptor;
import com.mele.games.hex.IHexResident;
import com.mele.games.hex.ui.HexCell;
import com.mele.games.hex.ui.ICellType;
import com.mele.games.hex.ui.IHexRenderable;
import com.mele.games.mechanics.ScoreEvent;
import com.mele.tapHerder.TapHerderGame;
import com.mele.tapHerder.residents.BaseResident;

public abstract class BaseTerrainType implements ICellType {
	protected Color backgroundColor;
	protected boolean obstacle = false;
	protected BaseTerrainType destructable = null;
	protected boolean hazard = false;
	protected boolean goal = false;
	
	HashMap<String, Object> properties = new HashMap<String, Object>();
	
	/**
	 * @param obstacle
	 * @param destructable
	 * @param hazard
	 * @param goal
	 */
	BaseTerrainType(boolean obstacle, BaseTerrainType destructable, boolean hazard, boolean goal) {
		this.obstacle = obstacle;
		this.destructable = destructable;
		this.hazard = hazard;
		this.goal = goal;
		
	}	

	public boolean isGoal() {
		return goal;
	}
	
	public boolean isObstacle() {
		return obstacle;
	}

	/**
	 * @return the hazard
	 */
	public boolean isHazard() {
		return hazard;
	}
	
	/**
	 * @return whether or not this terrain type is destructable
	 */
	public boolean isDestructable() {
		return destructable == null ? false : true;
	}
	
	public BaseTerrainType getDestructable() {
		return destructable;
	}
	
	@Override
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	@Override
	public void setBackgroundColor(Color arg0) {
		backgroundColor = arg0;

	}

	/**
	 * Determine how this cell will react when a "tap" occurs
	 * in <code>tappedCell</code>
	 * 
	 * @param tappedCell
	 */
	public void react(HexCell tappedCell, int tick) {
		if (isDestructable()) {
			BaseTerrainType newType = getDestructable();
			
			if (newType.isHazard()) {
				TapHerderGame.scoreLog.addScore(ScoreEvent.SCORE_TOHAZARD);
			} else if (!newType.isObstacle()) {
				TapHerderGame.scoreLog.addScore(ScoreEvent.SCORE_TOSAFE);
			}
			
			tappedCell.setType(newType);
			tappedCell.addProperty(IHexRenderable.PROPKEY_NEWTYPE, IHexRenderable.PROPVAL_TRUE);
		}	
		
		for (IHexResident res : tappedCell.getResidents()) {
			BaseResident resident = (BaseResident) res;
			resident.react(tappedCell, tick);
		}
	}

	@Override
	public Object getProperty(String propname) {
		return properties.get(propname);
	}

	@Override
	public void setProperty(String propname, Object propvalue) {
		properties.put(propname, propvalue);
	}	
	
}
