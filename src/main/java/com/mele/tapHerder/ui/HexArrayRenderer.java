package com.mele.tapHerder.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.mele.games.utils.hexarray.Cell;
import com.mele.games.utils.hexarray.HexArray;
import com.mele.games.utils.hexarray.HexPoint;
import com.mele.tapHerder.TapHerderCell;

/**
 * Responsible for drawing the contents of the hex array (the main game board).
 * 
 * @author Ayar
 *
 */
public class HexArrayRenderer {
	protected static Logger log = Logger.getLogger(HexArrayRenderer.class);
	
	protected Graphics offScreenGraphicsCtx = null;
	protected HexArray hexmap = null;
	protected Map<Polygon, Cell> visualMap = new ConcurrentHashMap<Polygon, Cell>();
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
			} else {
				switch (cell.getType()) {
				case DRY_BRUSH:
					color = Color.cyan;
					break;
				case TREE:
					color = Color.green;
					break;					
				case FENCE:
					color = Color.getHSBColor(32, (float).71, (float).91);
					break;					
				case STATUE:
					color = Color.lightGray;
					break;						
				case LONG_GRASS:
					color = Color.getHSBColor(120, (float).99, (float).46);
					break;						
				case FIELD:
					color = Color.yellow;
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
					color = Color.getHSBColor(32, (float).71, (float).56);
					break;
				}
			}
			g.setColor(color);
			g.fillPolygon(p);
			
			if (cell.getResident() != null) {
				g.setColor(Color.blue);
				String name = cell.getResident().getName();
				int x = (int)p.getBounds().getMinX() + ((int)p.getBounds().getWidth() / 2) - (g.getFontMetrics().stringWidth(name) / 2);
				int y = (int)p.getBounds().getMinY() + ((int)p.getBounds().getHeight() / 2) + (g.getFontMetrics().getHeight() / 2);
				//log.info("Draw resident tag: (" + x + "," + y + ")");
				//g.drawString(cell.getResident().getName(), x, y);
				//g.drawRect((int)p.getBounds().getMinX(), (int)p.getBounds().getMinY(), (int)p.getBounds().getWidth(), (int)p.getBounds().getHeight());
				g.drawString(name, x, y);
				//g.drawString(cell.getResident().getName(), 50, 50);
			}
			
			g.setColor(Color.black);
			g.drawPolygon(p);
			
		}
		
		g.setColor(backup);
	}
	
}
