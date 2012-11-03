package com.mele.games.utils.ui;

import java.awt.Image;

import org.apache.log4j.Logger;

import com.mele.tapHerder.ETerrainType;
import com.mele.tapHerder.TapHerderCell;
import com.mele.tapHerder.residents.IResident;


/**
 *  This class will be used to load and initialize images for sprites.
 *  Sprite classes have descriptors (SpriteFactoryDescriptor) which
 *  describe how they are animated.  They also include a list of
 *  AnimationImages, each one of which represents a specific image in
 *  an animation sequence.
 *  
 * @author Al
 *
 */
public class SpriteFactory {
	protected static Logger log = Logger.getLogger(SpriteFactory.class);
	
	protected static SpriteFactoryDescriptor spriteDescriptionForType(ETerrainType type) {
		SpriteFactoryDescriptor sd = null;
		
		switch(type) {
		case FENCE:
			sd = new SpriteFactoryDescriptor();
			sd.addImageFrames("Fence.png", 1, 0);
			break;
		}
		
		
		return sd;
	}
	
	protected static SpriteFactoryDescriptor spriteDescriptorForResident(String name) {
		SpriteFactoryDescriptor sd = null;
		
		if ("D".equals(name)) {
			sd = new SpriteFactoryDescriptor();
			sd.addImageFrames("150px-Wolf_(Tamed)_0.png", 5, 6);
			sd.addImageFrames("150px-Wolf_(Tamed)_1.png", 1, 2);
		}
		
		if ("W".equals(name)) {
			sd = new SpriteFactoryDescriptor();
			sd.addImageFrames("150px-Wolf_(Aggressive)_0.png", 2, 3);
			sd.addImageFrames("150px-Wolf_(Aggressive)_1.png", 2, 4);
			sd.addImageFrames("150px-Wolf_(Aggressive)_2.png", 2, 3);
			sd.addImageFrames("150px-Wolf_(Aggressive)_1.png", 2, 4);		}
		
		return sd;
	}
	
	/**
	 * @param component
	 * @param piece
	 * @return
	 */
	public static Sprite letThereBeSprite(IResident res) {
		SpriteFactoryDescriptor descriptor = spriteDescriptorForResident(res.getName());
		Sprite sprite = null;
		
		if (descriptor != null) {
			sprite = new Sprite();
			sprite.setDescriptor(descriptor);
			
			for (AnimationImage ai : descriptor.getImageList()) {
				Image image = RenderUtils.loadImage(ai.getImageName(), descriptor.getTransparency());
				
				if (!ai.isLoaded()) {
					ai.setImage(image);
				}
			}
		}

		return sprite;
	}
	
	/**
	 * @param cell
	 * @return
	 */
	public static Sprite letThereBeSprite(TapHerderCell cell) {
		SpriteFactoryDescriptor descriptor = spriteDescriptionForType(cell.getType());
		Sprite sprite = null;
		
		if (descriptor != null) {
			sprite = new Sprite();
			sprite.setDescriptor(descriptor);
			
			for (AnimationImage ai : descriptor.getImageList()) {
				Image image = RenderUtils.loadImage(ai.getImageName(), descriptor.getTransparency());
				
				if (!ai.isLoaded()) {
					ai.setImage(image);
				}
			}
		}

		return sprite;
	}
}
