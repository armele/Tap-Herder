package com.mele.tapHerder;

/**
 * Enumeration of terrain types.
 * 
 * @author Ayar
 *
 */
public enum ETerrainType {
	FIELD		 (false, null, false, false),
	PATH		 (false, null, false, false),
	FIRE		 (false, null, true, false),
	DRY_BRUSH    (true, FIRE, false, false),
	TREE         (true, FIELD, false, false),
	FENCE        (true, FIELD, false, false),
	DEEP_WATER	 (true, null, false, false),
	BOULDER		 (true, null, false, false),
	STATUE		 (true, null, false, false),
	LONG_GRASS	 (false, null, false, true)
	;
	
	protected boolean obstacle = false;
	protected ETerrainType destructable = null;
	protected boolean hazard = false;
	protected boolean goal = false;
	
	ETerrainType(boolean obstacle, ETerrainType destructable, boolean hazard, boolean goal) {
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
		return destructable == null ? false : true;
	}
	
	public ETerrainType getDestructable() {
		return destructable;
	}

	/**
	 * @return the hazard
	 */
	public boolean isHazard() {
		return hazard;
	}
	
	
}
