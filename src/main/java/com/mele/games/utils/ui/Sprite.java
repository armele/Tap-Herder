package com.mele.games.utils.ui;

import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;

/**
 * Used to render an animation sequence described by
 * the descriptor.
 * 
 * @author Ayar
 *
 */
public class Sprite {
	protected SpriteFactoryDescriptor descriptor = null;
	
	/**
	 * An index indicating the current animation image to render.
	 */
	protected int currentState = 0;
	
	/**
	 * @return the descriptor
	 */
	public SpriteFactoryDescriptor getDescriptor() {
		return descriptor;
	}

	/**
	 * @param descriptor the descriptor to set
	 */
	public void setDescriptor(SpriteFactoryDescriptor descriptor) {
		this.descriptor = descriptor;
	}
	
	/**
	 * @param p
	 * @param g
	 */
	public void render(Polygon p, Graphics g) {
		ArrayList<AnimationImage> imgList = descriptor.getImageList();
		boolean canRender = false;
		
		if (imgList != null) {
			if (currentState >= imgList.size()) {
				currentState = 0;
			}
			AnimationImage ai = imgList.get(currentState);
			if (ai != null && ai.getImage() != null) {
				canRender = g.drawImage(ai.getImage(), (int)p.getBounds().getMinX(),(int) p.getBounds().getMinY(), (int)p.getBounds().getWidth(), (int)p.getBounds().getHeight(), null);
				
				if (canRender) {
					if (ai.advanceFrame() > ai.getFrames()) {
						ai.setCurrentFrame(0);
						currentState++;
					}
				}
			}
		}

		if (!canRender) {
			RenderUtils.drawPolygonText(p, "!", g);
		}
	}
	
}
