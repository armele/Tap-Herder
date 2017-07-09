package com.mele.tapHerder.editor;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.HashMap;

import com.mele.games.animation.RenderUtils;

class CommandButton {
	CommandButton (Polygon p) {
		polygon = p;
	}
	Polygon polygon = null;
	boolean pressed = false;
}

public class EditCommandBar {
	protected int referencePointX = 0;
	protected int referencePointY = 0;
	protected boolean initialized = false;
	
	protected HashMap<CommandButton, String> controlMap = new HashMap<CommandButton, String>();
	
	
	
	// TODO: Terrain selection
	// TODO: Row/Cell Count
	// TODO: Creature placement
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
	 * @param referencePointX the referencePointX to set
	 */
	public void setReferencePointY(int referencePointY) {
		this.referencePointY = referencePointY;
	}

	/**
	 * 
	 */
	protected void init() {
		Polygon t1 = RenderUtils.drawPolygon(referencePointX, referencePointY, 3, 15, 30);
		controlMap.put(new CommandButton(t1), "COLUMNUP");
		
		Polygon t2 = RenderUtils.drawPolygon(referencePointX, referencePointY + 15, 3, 15, 210);
		controlMap.put(new CommandButton(t2), "COLUMNDOWN");
		
		initialized = true;
	}
	
	/**
	 * @param g
	 */
	public void update(Graphics g) {
		if (!initialized) {
			init();
		}
		
		for (CommandButton btn : controlMap.keySet()) {
			g.drawPolygon(btn.polygon);
		}
	}
	
	/**
	 * @param p
	 * @return
	 */
	public String commandAtPoint(Point p) {
		String command = null;
		
		for (CommandButton btn : controlMap.keySet()) {
			if (btn.polygon.contains(p)) {
				command = controlMap.get(btn);
				break;
			}
		}
		
		return command;
	}
}
