package com.mele.tapHerder.residents;

import com.mele.games.mechanics.ScoreEvent;
import com.mele.games.utils.hexarray.EHexVector;
import com.mele.tapHerder.TapHerderCell;

/**
 * Defines the "Snail" resident.
 * 
 * @author Ayar
 *
 */
public class Snail extends BaseResident {

	public Snail() {
		setName("S");
	}
	
	/* (non-Javadoc)
	 * @see com.mele.tapHerder.residents.BaseResident#react(com.mele.games.utils.hexarray.EHexVector)
	 */
	public void react(TapHerderCell homeCell, EHexVector vector) {
		// Basic single-hex move.
		// TODO: handle multiple move sizes
		if (homeCell != null) {
			TapHerderCell neighbor = (TapHerderCell) homeCell.findAdjacentCell(vector);
			if (neighbor != null) {
				if (neighbor.getType().isHazard()) {
					// Got pushed into a hazard - I'm dead!
					homeCell.setResident(null);
					kill();
				} else if (neighbor.getType().isObstacle()) {
					// Got pushed into an obstacle - I'm a snail and it kills me.  Sad.
					homeCell.setResident(null);
					kill();
				} else if (neighbor.getType().isDestructable()) {
					// Got pushed into a destroyable obstacle - I'm a snail and it kills me.  Sad.
					homeCell.setResident(null);
					kill();
				} else {
					BaseResident destinationResident = neighbor.getResident();
					if (destinationResident != null) {
						if (destinationResident instanceof IBadResident) {
							// Next cell over is occupied by an antiresident - it has slain me!
							kill();
							homeCell.setResident(null);
						} else {
							// Next cell over is occupied by a resident - I killed the resident and took his spot!
							destinationResident.kill();
							neighbor.setResident(this);
							homeCell.setResident(null);
						}
					} else {
						// Next cell over is free - move me!
						homeCell.setResident(null);
						neighbor.setResident(this);
					}
				}
			} else {
				// Next cell over is off the map.  I'm dead!
				homeCell.setResident(null);
				kill();
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.mele.tapHerder.residents.BaseResident#kill()
	 */
	public void kill() {
		game.getScoreLog().addScore(ScoreEvent.SCORE_DEADRESIDENT);
	}

}
