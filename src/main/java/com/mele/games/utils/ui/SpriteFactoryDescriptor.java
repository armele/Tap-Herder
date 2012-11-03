package com.mele.games.utils.ui;

import java.util.ArrayList;
import java.util.Properties;

import com.mele.games.utils.Die;

/**
 * @author Al
 * 
 */
public class SpriteFactoryDescriptor {
	protected String spriteClass;
	protected ArrayList<AnimationImage> imageList = new ArrayList<AnimationImage>();
	protected Properties props = new Properties();
	protected int transparency = -65281; // Default to magenta

	public String getSpriteClass() {
		return spriteClass;
	}

	public void setSpriteClass(String spriteClass) {
		this.spriteClass = spriteClass;
	}

	public void addStateImage(AnimationImage animation) {
		imageList.add(animation);
	}
	
	public void addImageFrames(String imageName, int frameCount, int frameVariation) {
		AnimationImage ai = new AnimationImage();
		
		if (frameVariation > 0) {
			Die d = new Die(frameVariation);
			frameCount = frameCount + d.roll();
		}
		 
		ai.initialize(imageName, frameCount);
		imageList.add(ai);
	}
	
	public ArrayList<AnimationImage> getImageList() {
		return imageList;
	}
	
	/**
	 * @return the transparency
	 */
	public int getTransparency() {
		return transparency;
	}

	/**
	 * Allows transparencies to be different for
	 * different sprites.  Default is magenta (255, 0, 255)
	 * 
	 * @param transparency
	 *            the transparency to set
	 */
	public void setTransparency(int transparency) {
		this.transparency = transparency;
	}

	public Properties getProperties() {
		return props;
	}

	public void setProperties(Properties props) {
		this.props = props;
	}

	
}
