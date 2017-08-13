package com.mele.tapHerder;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mele.games.hex.EHexVector;
import com.mele.games.hex.IHexEventListener;
import com.mele.games.hex.IHexResident;
import com.mele.games.hex.ui.CellRenderer;
import com.mele.games.hex.ui.HexArrayController;
import com.mele.games.hex.ui.HexArrayRenderer;
import com.mele.games.hex.ui.HexCell;
import com.mele.games.hex.ui.HexEventDetail;
import com.mele.games.mechanics.EGameState;
import com.mele.games.mechanics.ScoreEvent;
import com.mele.games.mechanics.ScoreLog;
import com.mele.tapHerder.residents.BaseResident;
import com.mele.tapHerder.residents.MotionTracker;
import com.mele.tapHerder.types.BaseTerrainType;

/**
 * Main game logic for the Tap Herder game.
 * 
 * @author Ayar
 *
 */
public class TapHerderGame extends Frame implements IHexEventListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected int currentTick = 0;
	
	protected static Logger log = LogManager.getLogger(TapHerderGame.class);
	protected EGameState gameState = EGameState.UNINITIALIZED;
	public HexArrayController hexControl = new HexArrayController(this);
	protected boolean initialized = false;
	protected boolean closed = false;
	protected int tapCount = 0;
	CellRenderer selectedCell = null;
	
	// Going with a more complicated scoring model here, anticipating potential
	// things like statistics, achievements, etc.
	public static ScoreLog scoreLog = new ScoreLog();
	
	/**
	 * @return the initialized
	 */
	public boolean isInitialized() {
		return initialized;
	}

	/**
	 * 
	 */
	protected void init() {
		
		setVisible(true);
		setTitle("Test Hex Frame");
		setSize(320, 320);

		//TODO: Concept of multi-board games
		//TODO: Random board creation options
		//TODO: Menuing option to pick board
		hexControl.loadFromMap("com/mele/tapHerder/large.map");
		hexControl.registerHexEventListener(this);
		
		HexArrayRenderer har = hexControl.getView();
		har.setSelectionColor(Color.green);
		har.setLineColor(new Color(125, 0, 125));

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				closed = true;
			}
		});
		
		hexControl.snap();
		
		har.setLabel(0, "Score");
		har.setLabel(2, "Moves");
		
		initialized = true;
	}
	

	/**
	 * @param tick
	 */
	public void mainGameLoop(int tick) {
		if (!initialized) {
			init();
		}
		
		currentTick = tick;
		
		if (selectedCell != null) {
			BaseTerrainType type = (BaseTerrainType)selectedCell.getCell().getType();
			if (type != null) {
				type.react(selectedCell.getCell(), selectedCell.getCell(), tick);
			} else {
				// log.debug("No type information found on cell " + checkCell);
			}
			
			// For all cells along each direction radiating out from the selected cell
			// determine how they react to the "tap". 
			for (EHexVector v : EHexVector.values()) {
				ArrayList<HexCell> cellList = selectedCell.getCell().pathFromCell(v);
				
				for (HexCell checkCell : cellList) {
					type = (BaseTerrainType)checkCell.getType();
					if (type != null) {
						type.react(selectedCell.getCell(), checkCell, tick);
					} else {
						// log.debug("No type information found on cell " + checkCell);
					}
					
					for (IHexResident res : checkCell.getResidents()) {
						BaseResident br = (BaseResident) res;
						br.react(selectedCell.getCell(), tick);
					}
				}
			}
			selectedCell.setSelected(false);
			selectedCell = null;
		} else {
			for (IHexResident res : hexControl.allResidents()) {
				BaseResident resident = (BaseResident) res;
				
				if (resident != null) {
					MotionTracker tracker = resident.getMotion();
					
					if (tracker != null && tracker.isInMotion()) {
						if (resident.getRunDistance() < 0 || tracker.getSteps() < resident.getRunDistance()) {
							if (tracker.isMoveTick(tick)) {
								resident.moveInDirection(tracker.getDirection());
							}
						} else {
							tracker.setInMotion(false);
						}
					}
				}
			}  // End resident loop
		}
		
		// TODO: Determine end game conditions (board completed / failed)
		if (closed) {
			this.dispose();
			gameState = EGameState.HALTED;
		}
	}


	/* (non-Javadoc)
	 * @see com.mele.games.hex.IHexEventListener#cellEvent(com.mele.games.hex.ui.HexEventDetail)
	 */
	public boolean cellEvent(HexEventDetail cmd) {
		boolean keepDefault = false;
		
		if (cmd != null) {
			switch (cmd.getEventType()) {
			case MOUSE_LEFTCLICK:
				
				if (cmd.getEventLocation() != null) {
					selectedCell = cmd.getEventLocation();
					selectedCell.setSelected(true);
					scoreLog.addScore(ScoreEvent.SCORE_TAP);
					tapCount++;
				}
			case MOUSE_CENTERCLICK:
				break;
			case MOUSE_ENTERED:
				keepDefault = true;
				break;
			case MOUSE_EXITED:
				keepDefault = true;
				break;
			case MOUSE_RELEASED:
				break;
			case MOUSE_RIGHTCLICK:
				break;
			case MOUSE_WINEXIT:
				keepDefault = true;
				break;
			default:
				break;				
			}

		
		}
		return keepDefault;
	}

	public Integer getScore() {
		return new Integer(scoreLog.scoreTotal());
	}
	
	public static ScoreLog getScoreLog() {
		return scoreLog;
	}
	
	public int getTapCount() {
		return tapCount;
	}
	

	/**
	 * 
	 */
	public void display() {
		if (!initialized) {
			init();
		}
		
		update(this.getGraphics());
	}	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Container#update(java.awt.Graphics)
	 */
	public void update(Graphics g) {
		Insets border = this.getInsets();
		HexArrayRenderer har = hexControl.getView();
		har.setOffsetX(border.left);
		har.setOffsetY(border.top);

		Image hexCanvas = har.getCanvas();

		// Draw the scene onto the screen
		if (hexCanvas != null && g != null) {
			Graphics2D offScreenGraphicsCtx = (Graphics2D) hexCanvas.getGraphics();
			har.update(offScreenGraphicsCtx);
			g.drawImage(hexCanvas, border.left, border.top, this);
			har.setLabel(1, scoreLog.scoreTotal());
			har.setLabel(3, scoreLog.moves());
		}
	}

	/**
	 * @return the gameState
	 */
	public EGameState getGameState() {
		return gameState;
	}

	/**
	 * @param gameState the gameState to set
	 */
	public void setGameState(EGameState gameState) {
		this.gameState = gameState;
	}	
	
	
}
