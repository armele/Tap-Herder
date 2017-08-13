package com.mele.tapHerder.audio;

import java.io.File;
import java.util.HashMap;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import com.mele.games.utils.GameException;

/**
 * @author Al Mele
 *
 */
public class SoundFx {
	protected static HashMap<String, Clip> clipMap = new HashMap<String, Clip>();

	/**
	 * @param filename
	 */
	public static void play(String filename) {
		
		Thread fxThread = new Thread() {

			public void run() {
				Clip clip = clipMap.get(filename);
				
				try {
					if (clip == null) {
						clip = AudioSystem.getClip();
						clip.open(AudioSystem.getAudioInputStream(new File(filename)));
						clipMap.put(filename, clip);
					}
					clip.start();
					while (clip.isRunning()) {
						Thread.sleep(86);
					}
				} catch (Exception exc) {
					throw new GameException("Error playing audio file " + filename, exc);
				} finally {
					clip.close();
				}
			};
		};
		
		fxThread.start();
	}
}
