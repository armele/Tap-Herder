package com.mele.games.utils.ui;

import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;
import java.net.URL;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.mele.games.utils.GameException;
import com.mele.tapHerder.TapHerder;

/**
 * Inner class to assist with transparency load.
 * 
 * @author Al
 *
 */
class TransparentFilter extends RGBImageFilter {
	protected int transparentColor;
	
	TransparentFilter(int transparency) {
		transparentColor = transparency;
		canFilterIndexColorModel = true;
	}

    public int filterRGB(int x, int y, int rgb) {
		 // the RGB code of the color to turn into "transparent"
		
       if (rgb==transparentColor) {
           return 0x000000ff;
       } else {
           return rgb;
       }
    }
}

public class RenderUtils {
	public static final int DEFAULT_TRANSPARENCY = -65281;
	protected static HashMap<String, Image> imageByResourceName = new HashMap<String, Image>();
	protected static Logger log = Logger.getLogger(RenderUtils.class);
	
	/**
	 * @param component
	 * @param imageResource
	 * @return
	 */
	public static Image loadImage(Component component, String imageResource) {
		return loadImage(component, imageResource, DEFAULT_TRANSPARENCY);
	}
	
	/**
	 * @param component
	 * @param imageResource
	 * @param transparency
	 * @return
	 */
	public static Image loadImage(Component component, String imageResource, int transparency) {
		Image image = imageByResourceName.get(imageResource);
		
		// Skip the image loading if we've already cached it by resource name;
		if (image != null) {
			return image;
		}
		
		if (imageResource != null && imageResource.length() > 0) {
			URL url = TapHerder.class.getResource(imageResource);  // TODO: make this main class independent
			
			if (url != null) {
				log.info("Loading image from: " + url.getFile());
				image = transparentLoad(component, url, transparency);
			} else {
				// TODO: Make this fatal, or load a default image.
				throw new GameException("No image found for: " + imageResource);
			}
		}
		
		//While not likely, it may be possible that the size isn't
		// known yet.  Do the following just in case.
		//Wait until size is known
		int waitcount = 0;
		while (image.getWidth(component) == -1 || image.getHeight(component) == -1) {
			if (waitcount % 1000 == 0) {
				log.info("Waiting for image");
			}
			if (waitcount > 2000) {
				break;
			}
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				break;
			}
			waitcount++;
		}//end while loop	
		
		// Cache this image for later use.
		imageByResourceName.put(imageResource, image);
		
		return image;
	}
	
	/**
	 * Creates a transparent image out of the image file 
	 * provided.
	 * 
	 * @param imageFile
	 * @return
	 */
	protected static Image transparentLoad(Component c, URL imageFile, int transparentColor) {
		// the original image
		Image original = Toolkit.getDefaultToolkit().getImage(imageFile);

		// the resulting image
		Image transparent
		   = c.createImage(
		        new FilteredImageSource(original.getSource(),  new TransparentFilter(transparentColor))
		     );
		
		return transparent;
	}
}
