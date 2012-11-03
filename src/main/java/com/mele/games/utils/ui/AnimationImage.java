package com.mele.games.utils.ui;

import java.awt.Image;

/**
 * Represents a specific picture to be used in a sequence of animations
 * for a sprite.
 * 
 * @author Ayar
 *
 */
public class AnimationImage {
	/**
	 * Name of the image resource to be displayed.
	 */
	protected String imageName = null;
	
	/**
	 * The loaded image corresponding to the named resource. 
	 */
	protected Image image = null;
	
	/**
	 * The current frame being rendered. 
	 */
	protected int currentFrame = 0;
	
	/**
	 * The maximum number of frames this image will be rendered. 
	 */
	protected int frames = 1;
	
	/**
	 * @param fileName
	 * @param frames
	 */
	public void initialize(String fileName, int frames) {
		imageName = fileName;
		this.frames = frames;
	}
	
	/**
	 * @return the imageName
	 */
	public String getImageName() {
		return imageName;
	}
	/**
	 * @param imageName the imageName to set
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(Image image) {
		this.image = image;
	}
	
	public boolean isLoaded() {
		return image == null ? false : true;
	}
	/**
	 * @return the currentFrame
	 */
	public int getCurrentFrame() {
		return currentFrame;
	}
	
	public void setCurrentFrame(int frame) {
		currentFrame = 0;
	}
	
	/**
	 * @param currentFrame the currentFrame to set
	 */
	public int advanceFrame() {
		currentFrame++;
		return currentFrame;
	}
	/**
	 * @return the frames
	 */
	public int getFrames() {
		return frames;
	}
	/**
	 * @param frames the frames to set
	 */
	public void setFrames(int frames) {
		this.frames = frames;
	}
	
	
}
