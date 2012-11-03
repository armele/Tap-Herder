package com.mele.tapHerder.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.mele.games.utils.hexarray.Cell;
import com.mele.games.utils.hexarray.HexArray;
import com.mele.games.utils.hexarray.HexPoint;
import com.mele.games.utils.ui.RenderUtils;
import com.mele.games.utils.ui.Sprite;
import com.mele.games.utils.ui.SpriteFactory;
import com.mele.tapHerder.TapHerderCell;
import com.mele.tapHerder.residents.IResident;

/**
 * Responsible for drawing the contents of the hex array (the main game board).
 * 
 * For assistance on color selection, see: http://www.spelunkcomputing.com/colorexplorerapplet.html
 * 
 * @author Ayar
 *
 */
public class HexArrayRenderer {
	protected static Logger log = Logger.getLogger(HexArrayRenderer.class);
	
	protected Graphics offScreenGraphicsCtx = null;
	protected HexArray hexmap = null;
	protected Map<Polygon, Cell> visualMap = new ConcurrentHashMap<Polygon, Cell>();
	protected Map<IResident, Sprite> resSpriteMap = new HashMap<IResident, Sprite>();
	protected Map<TapHerderCell, Sprite> cellSpriteMap = new HashMap<TapHerderCell, Sprite>();
	protected int cellSize = 20;
	protected static final int HEX = 6;
	protected int referencePointX = 0;
	protected int referencePointY = 0;
	protected boolean initialized = false;
	
	public HexArrayRenderer(HexArray hexmap) {
		this.hexmap = hexmap;
	}
	
	public HexArrayRenderer() {
	}

	public void setHexmap(HexArray hexmap) {
		this.hexmap = hexmap;
	}
	
	/**
	 * @return the referencePointX
	 */
	public int getReferencePointX() {
		return referencePointX;
	}

	/**
	 * @param referencePointX the referencePointX to set
	 */
	public void setReferencePointX(int referencePointX) {
		this.referencePointX = referencePointX;
	}

	/**
	 * @return the referencePointY
	 */
	public int getReferencePointY() {
		return referencePointY;
	}



	/**
	 * @param referencePointY the referencePointY to set
	 */
	public void setReferencePointY(int referencePointY) {
		this.referencePointY = referencePointY;
	}



	/**
	 * Create the polygons necessary for illustrating the board.
	 */
	public void init() {
		
		for (Cell cell : hexmap) {
			Polygon p = new Polygon();
			HexPoint point =  cell.getPoint();
			visualMap.put(p, cell);
			
			double columnOffset = (point.getX() - 1) * (cellSize * Math.cos(2 * Math.PI / HEX) + cellSize);
			double rowOffset = (cellSize * Math.sin(2 * Math.PI / HEX)) * (2 * point.getCartesianY() - (point.getX() % 2));
				
		    for (int i = 0; i < HEX; i++) {
			      p.addPoint(
			    	(int) (referencePointX + columnOffset + (cellSize * Math.cos(i * 2 * Math.PI / HEX))),
			        (int) (referencePointY + rowOffset + (cellSize * Math.sin(i * 2 * Math.PI / HEX)))
			      );
		    }
		}
		
		initialized = true;
	}
	
	/**
	 * @param point
	 * @return
	 */
	public Cell getCellAtPoint(Point point) {
		Cell target = null;
		
		for (Polygon p : visualMap.keySet()) {
			if (p.contains(point)){
				target = visualMap.get(p);
				break;
			}
		}
		
		return target;
	}
	
	
	protected Color cellColor(TapHerderCell cell) {
		Color color = null;
		
		switch (cell.getType()) {
		case DRY_BRUSH:
			color = Color.cyan;
			break;
		case TREE:
			color = Color.green;
			break;					
		case FENCE:
			color = Color.getHSBColor((float).1, (float)1.0, (float).67);  // light brown
			break;					
		case STATUE:
			color = Color.lightGray;
			break;						
		case LONG_GRASS:
			color = Color.getHSBColor((float).25, (float)1.0, (float)1.0);  // neon green
			break;						
		case FIELD:
			color = Color.getHSBColor((float).33, (float).2, (float)1.0);  // light green
			break;
		case DEEP_WATER:
			color = Color.blue;
			break;
		case BOULDER:
			color = Color.gray;
			break;
		case FIRE:
			color = Color.red;
			break;
		case PATH:
			color = Color.getHSBColor((float).12, (float).58, (float).75);  // khaki(?)
			break;
		}
		
		return color;
	}
	
	/**
	 * @param resident
	 * @param p
	 * @param g
	 */
	public void drawSprite(IResident resident, Polygon p, Graphics g) {
		Sprite sprite = resSpriteMap.get(resident);
		if (sprite == null) {
			sprite = SpriteFactory.letThereBeSprite(resident);
			resSpriteMap.put(resident, sprite);
		}
		
		if (sprite != null) {
			sprite.render(p, g);
		} else {
			String name = resident.getName();
			RenderUtils.drawPolygonText(p, name, g);		
		}
	}
	
	/**
	 * @param cell
	 * @param p
	 * @param g
	 */
	public void drawSprite(TapHerderCell cell, Polygon p, Graphics g) {
		Sprite sprite = cellSpriteMap.get(cell);
		if (sprite == null) {
			sprite = SpriteFactory.letThereBeSprite(cell);
			cellSpriteMap.put(cell, sprite);
		}
		
		Color color = cellColor(cell);
		g.setColor(color);
		g.fillPolygon(p);	
		
		if (sprite != null) {
			sprite.render(p, g);	
		}
	}
	
	/**
	 * @param g
	 */
	public void update(Graphics g) {
		if (!initialized) {
			return;
		}
		
		Color backup = g.getColor();
	    
		for (Polygon p : visualMap.keySet()) {
			TapHerderCell cell = (TapHerderCell) visualMap.get(p);
			Color color = Color.white;
			
			if (cell.isSelected()) {
				color = Color.white;
				g.setColor(color);
				g.fillPolygon(p);
			}
			
			drawSprite(cell, p, g);
			
			IResident resident = cell.getResident();
			// TODO: Control paint such that all sprites are on top of all hex fills.
			if (resident != null) {
				drawSprite(resident, p, g);
			}
			
			g.setColor(Color.black);
			g.drawPolygon(p);
			
		}
		
		g.setColor(backup);
	}
	
}
