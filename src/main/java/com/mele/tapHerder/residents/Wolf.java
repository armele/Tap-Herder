package com.mele.tapHerder.residents;

import com.mele.games.mechanics.ScoreEvent;
import com.mele.games.utils.hexarray.EHexVector;
import com.mele.tapHerder.ETerrainType;
import com.mele.tapHerder.TapHerderCell;
import com.mele.tapHerder.TapHerderGame;

public class Wolf extends BaseResident implements IAntiresident {

	public Wolf(TapHerderGame game) {
		super(game);
		setName("W");
	}
	
	/* (non-Javadoc)
	 * @see com.mele.tapHerder.residents.BaseResident#react(com.mele.games.utils.hexarray.EHexVector)
	 */
	public void react(TapHerderCell homeCell, EHexVector vector) {
		// Basic move, but with a reversed vector (the wolf pursues the tap)
		// TODO: handle multiple move sizes
		
		if (homeCell != null) {
			TapHerderCell neighbor = (TapHerderCell) homeCell.findAdjacentCell(vector.reverse());
			if (neighbor != null) {
				if (neighbor.getType().equals(ETerrainType.HAZARD)) {
					// Got pushed into a hazard - I'm dead!
					homeCell.setResident(null);
					kill();
				} else if (neighbor.getType().equals(ETerrainType.OBSTACLE)) {
					// Got pushed into an obstacle - can't move
				} else if (neighbor.getType().equals(ETerrainType.DESTRUCTABLE)) {
					// Got pushed into a destroyable obstacle - can't move
				} else {
					BaseResident destinationResident = neighbor.getResident();
					if (destinationResident != null) {
						// Next cell over is occupied - I killed the resident and took his spot!
						destinationResident.kill();
						neighbor.setResident(this);
						homeCell.setResident(null);
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
		game.getScoreLog().addScore(ScoreEvent.SCORE_DEADANTIRES);
	}
}