package com.mele.tapHerder.residents;

import com.mele.games.hex.EHexVector;
import com.mele.games.hex.ui.HexCell;

/**
 * Track information about motion.
 * 
 * @author Ayar
 *
 */
public class MotionTracker {
	
	/**
	 * How many steps have been taken in this movement? 
	 */
	protected int steps = 0;
	
	/**
	 * What was the initiation point of this movement?
	 */
	protected HexCell sourceLocation = null;
	
	/**
	 * Tick upon which motion started.
	 */
	protected int sourceTick = 0;
	
	/**
	 * What direction is this movement in?
	 */
	protected EHexVector direction = null;
	
	/**
	 * Is this in motion?
	 */
	protected boolean inMotion = false;

	
	/**
	 * How fast is this movement? 
	 * Velocity represents the percentage of ticks on which a movement takes place.  
	 * 100 = every tick, 50 = every other tick, etc.
	 * Default is every 5 ticks (20%)
	 * Movement only happens on tick integers (no decimal fractions), so anything between 51 and 99
	 * will result in an every-tick movement (same as 100).
	 */
	protected int velocity = 20;
	
	
	/**
	 * @return the steps
	 */
	public int getSteps() {
		return steps;
	}

	/**
	 * @param steps the steps to set
	 */
	public void setSteps(int steps) {
		this.steps = steps;
	}

	/**
	 * @return the sourceLocation
	 */
	public HexCell getSourceLocation() {
		return sourceLocation;
	}

	/**
	 * @param sourceLocation the sourceLocation to set
	 */
	public void setSourceLocation(HexCell sourceLocation) {
		this.sourceLocation = sourceLocation;
	}

	/**
	 * @return the direction
	 */
	public EHexVector getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(EHexVector direction) {
		this.direction = direction;
	}

	/**
	 * @return the inMotion
	 */
	public boolean isInMotion() {
		return inMotion;
	}

	/**
	 * @param inMotion the inMotion to set
	 */
	public void setInMotion(boolean inMotion) {
		this.inMotion = inMotion;
		
		if (!inMotion) {
			steps = 0;
			direction = null;
			sourceLocation = null;
		}
	}
	
	/**
	 * @return the sourceTick
	 */
	public int getSourceTick() {
		return sourceTick;
	}

	/**
	 * @param sourceTick the sourceTick to set
	 */
	public void setSourceTick(int sourceTick) {
		this.sourceTick = sourceTick;
	}

	/**
	 * @return the velocity
	 */
	public int getVelocity() {
		return velocity;
	}

	/**
	 * @param velocity the velocity to set
	 */
	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}
	
	/**
	 * Determine if (based on this motion information), a move
	 * is expected on this tick.
	 * 
	 * @param thisTick
	 * @return
	 */
	public boolean isMoveTick(int thisTick) {
		boolean moveTick = false;
		int ticks = thisTick - sourceTick;
		int moveInterval = 100 / velocity;
		
		if (ticks > 0 && ticks % moveInterval == 0) {
			moveTick = true;
		}
		
		return moveTick;
	}

	public int addStep() {
		steps = steps + 1;
		return steps;
	}
	
}
