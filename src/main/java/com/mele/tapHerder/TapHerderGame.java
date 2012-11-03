package com.mele.tapHerder;

import org.apache.log4j.Logger;

import com.mele.games.mechanics.EGameState;
import com.mele.games.mechanics.ICommand;
import com.mele.games.mechanics.IGame;
import com.mele.games.mechanics.IGameManager;
import com.mele.games.mechanics.ScoreLog;
import com.mele.games.utils.hexarray.Cell;
import com.mele.games.utils.hexarray.EHexVector;
import com.mele.games.utils.hexarray.HexArray;
import com.mele.tapHerder.residents.BaseResident;

/**
 * Main game logic for the Tap Herder game.
 * 
 * @author Ayar
 *
 */
public class TapHerderGame implements IGame {
	protected static Logger log = Logger.getLogger(TapHerderGame.class);
	protected EGameState state = EGameState.UNINITIALIZED;
	protected HexArray hexmap = new HexArray(15,21, TapHerderCell.class);
	protected TapHerderGameManager gameManager = null;
	protected boolean initialized = false;
	
	// Going with a more complicated scoring model here, anticipating potential
	// things like statistics, achievements, etc.
	protected ScoreLog scoreLog = new ScoreLog();
	
	@Override
	public void setState(EGameState state) {
		this.state = state;
	}

	@Override
	public EGameState getState() {
		return state;
	}
	
	/**
	 * @return the initialized
	 */
	public boolean isInitialized() {
		return initialized;
	}

	protected void init() {
		//TODO: Concept of multi-board games
		//TODO: Random board creation options
		MapReader mr = new MapReader();
		mr.setMapTerrain(this, hexmap, "com/mele/tapHerder/basic.map");
		
		initialized = true;
	}
	
	@Override
	public void mainGameLoop() {
		if (!initialized) {
			init();
			gameManager.setRenderUpToDate(false);
		}
		
		gameManager.getRenderer().display();
		
		if (gameManager.isRenderUpToDate()) {
			for (Cell cell : hexmap) {
				TapHerderCell hCell = (TapHerderCell) cell;
				if (hCell.isSelected()) {
					// TODO: respond to selection;
					hCell.setSelected(false);
				}
			}
		}
	}

	@Override
	public boolean processCommand(ICommand cmd) {
		if (cmd != null) {
			gameManager.setRenderUpToDate(false);
		
			if (cmd instanceof TapCommand) {
				TapHerderCell cell = (TapHerderCell) ((TapCommand) cmd).getTapLocation();
				cell.setSelected(true);
				log.info("Got a tap command: " + cmd);
				
				for(EHexVector vector : EHexVector.values()) {
					TapHerderCell neighbor = (TapHerderCell) cell.findAdjacentCell(vector);
					
					if (neighbor != null) {
						BaseResident resident = neighbor.getResident();
						if (resident != null) {
							resident.react(neighbor, vector);
						}
					}
				}
			}
		
		}
		return false;
	}

	public HexArray getHexmap() {
		return hexmap;
	}

	@Override
	public void setGameManager(IGameManager gameManager) {
		this.gameManager = (TapHerderGameManager) gameManager;
	}
	
	public Integer getScore() {
		return new Integer(scoreLog.scoreTotal());
	}
	
	public ScoreLog getScoreLog() {
		return scoreLog;
	}
}
