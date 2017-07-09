package com.mele.tapHerder.residents;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import com.mele.games.hex.EHexVector;
import com.mele.games.hex.IHexResident;
import com.mele.games.hex.ui.HexCell;
import com.mele.games.hex.ui.IHexRenderable;
import com.mele.games.mechanics.ScoreEvent;
import com.mele.tapHerder.TapHerderGame;
import com.mele.tapHerder.types.BaseTerrainType;

/**
 * Defines attributes which all resident types will share.
 * 
 * @author Ayar
 *
 */
public abstract class BaseResident implements IHexResident, IHexRenderable {
	protected Map<String, Object> properties = new HashMap<String, Object>();
	protected TapHerderGame game = null;
	protected HexCell currentLocation = null;
	protected Color bkColor = null;
	
	/**
	 * How far away will this animal detect a "tap"? 
	 */
	protected int sensitivity = 1;
	
	/**
	 * How far will the creature travel, once it detects a tap?
	 * A negative value means no limit.
	 */
	protected int runDistance = -1;
	
	/**
	 * Information about a resident's motion.
	 */
	protected MotionTracker motion = new MotionTracker();
	
	protected String name = null;
	
	public BaseResident() {
		
	}
	
	/**
	 * @return
	 */
	public int getSensitivity() {
		return sensitivity;
	}
	
	/**
	 * @return
	 */
	public HexCell getCurrentLocation() {
		return currentLocation;
	}
	
	/**
	 * @param home
	 */
	public void setCurrentLocation(HexCell home) {
		currentLocation = home;
	}
	
	/**
	 * @return the runDistance
	 */
	public int getRunDistance() {
		return runDistance;
	}

	/**
	 * @param runDistance the runDistance to set
	 */
	public void setRunDistance(int runDistance) {
		this.runDistance = runDistance;
	}

	/* (non-Javadoc)
	 * @see com.mele.games.hex.ui.IHexRenderable#getProperty(java.lang.String)
	 */
	public Object getProperty(String key) {
		return properties.get(key);
	}
	
	/**
	 * @param game
	 */
	public void setGame(TapHerderGame game) {
		this.game = game;
	}
	
	/* (non-Javadoc)
	 * @see com.mele.games.hex.ui.IHexRenderable#setProperty(java.lang.String, java.lang.Object)
	 */
	public void setProperty(String key, Object value) {
		properties.put(key, value);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the motion
	 */
	public MotionTracker getMotion() {
		return motion;
	}

	/**
	 * @param motion the motion to set
	 */
	public void setMotion(MotionTracker motion) {
		this.motion = motion;
	}

	/**
	 * 
	 */
	public abstract void kill();
	
	protected abstract void moveTo(HexCell neighbor);
	
	/**
	 * Given a direction, move this resident in that direction.
	 * 
	 * @param direction
	 */
	public void moveInDirection(EHexVector direction) {
		HexCell neighbor = (HexCell) currentLocation.adjacent(direction);

		if (neighbor != null) {
			motion.setInMotion(true);
			motion.addStep();
			moveTo(neighbor);
		} else {
			// Next cell over is off the map.  I'm dead!
			// TODO: Sound effects (event log or repurpose the score log?)
			currentLocation.removeResident(this);
			kill();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.mele.tapHerder.residents.IResident#react(com.mele.tapHerder.Cell)
	 */
	public void react(HexCell tappedCell, int tick) {
		if (currentLocation != null) {
			int tapDistance = currentLocation.distance(tappedCell);
			EHexVector tapDirection = tappedCell.onVector(currentLocation);
			
			if (tapDistance <= getSensitivity() && tapDirection != null) {
				
				// Basic single-hex move.
				// TODO: handle multiple move sizes
				motion.setSourceLocation(tappedCell);
				motion.setDirection(tapDirection);
				motion.setSourceTick(tick);
				
				moveInDirection(tapDirection);
			}
		}
	}
	
	/**
	 * @param neighbor
	 * @param type
	 */
	protected void goodResidentMove(HexCell neighbor, BaseTerrainType type) {
		if (type.isGoal()){
			// Reached a goal space!
			game.getScoreLog().addScore(ScoreEvent.SCORE_RESGOAL);
			currentLocation.removeResident(this);
			
			// TODO: Find a good spot for these constants...
			// TODO: Special animations for reaching a goal...
			// TODO: Propagate this to other resident classes...
			neighbor.addProperty("GOALREACH", new Boolean(true)); 
		} else if (type.isHazard()) {
			// Got pushed into a hazard - I'm dead!
			currentLocation.removeResident(this);
			kill();
		} else if (type.isObstacle()) {
			// Got pushed into an obstacle - can't move
			motion.setInMotion(false);
		} else if (type.isDestructable()) {
			// Got pushed into a destroyable obstacle - can't move
			motion.setInMotion(false);
		} else {
			boolean killMe = false;
			for (IHexResident res : neighbor.getResidents() ) {
				BaseResident destinationResident = (BaseResident)res;
				if (destinationResident != null) {
					if (destinationResident instanceof IBadResident) {
						// Next cell over is occupied by an antiresident - it has slain me!
						killMe = true;
					} else {
						// Next cell over is occupied by a resident - I killed the resident and will take his spot (if I survive).
						neighbor.removeResident(destinationResident);
						destinationResident.kill();
					}
				} else {
					// Next cell over is free - move me!
					currentLocation.removeResident(this);
					neighbor.addResident(this);
				}
			}
			
			if (killMe) {
				kill();
				currentLocation.removeResident(this);
			} else {
				currentLocation.removeResident(this);
				neighbor.addResident(this);				
			}
		}			
	}
	
	/**
	 * @param neighbor
	 * @param type
	 */
	protected void badResidentMove(HexCell neighbor, BaseTerrainType type) {
		if (type.isHazard()) {
			// Got pushed into a hazard - I'm dead!
			currentLocation.removeResident(this);
			kill();
		} else if (type.isObstacle()) {
			// Got pushed into an obstacle - can't move
		} else if (type.isDestructable()) {
			// Got pushed into a destroyable obstacle - can't move
		} else {
			boolean moveMe = false;
			for (IHexResident res : neighbor.getResidents() ) {
				BaseResident destinationResident = (BaseResident)res;
				
				if (destinationResident != null) {
					// Next cell over is occupied - I killed the resident and took his spot!
					destinationResident.kill();
					neighbor.removeResident(destinationResident);
					moveMe = true;
				} else {
					moveMe = true;
				}
			}
			
			if (moveMe) {
				// Next cell over is free - move me!
				currentLocation.removeResident(this);
				neighbor.addResident(this);				
			}
		}			
	}	
	
	@Override
	public Color getBackgroundColor() {
		return bkColor;
	}

	@Override
	public void setBackgroundColor(Color backgroundColor) {
		bkColor = backgroundColor;
	}	
}
