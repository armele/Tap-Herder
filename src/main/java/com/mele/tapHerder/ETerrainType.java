package com.mele.tapHerder;

/**
 * Enumeration of terrain types.
 * 
 * @author Ayar
 *
 */
public enum ETerrainType {
	FIELD		 (false, false, false, false),
	PATH		 (false, false, false, false),
	FIRE		 (false, false, true, false),
	DRY_BRUSH    (true, true, false, false),
	TREE         (true, true, false, false),
	FENCE        (true, true, false, false),
	DEEP_WATER	 (true, false, false, false),
	BOULDER		 (true, false, false, false),
	STATUE		 (true, false, false, false),
	LONG_GRASS	 (false, false, false, true)
	;
	
	protected boolean obstacle = false;
	protected boolean destructable = false;
	protected boolean hazard = false;
	protected boolean goal = false;
	
	ETerrainType(boolean obstacle, boolean destructable, boolean hazard, boolean goal) {
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
	 * @return the destructable
	 */
	public boolean isDestructable() {
		return destructable;
	}

	/**
	 * @return the hazard
	 */
	public boolean isHazard() {
		return hazard;
	}
	
	
}
