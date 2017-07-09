package com.mele.tapHerder;

import com.mele.games.mechanics.BaseGameManager;

/**
 * Specific Tap Herder implementation of the game manager which handles coordination between
 * the game, the input device and the renderer.  (With a Swing interface the renderer
 * and the input don't need their own threads.)
 * 
 * @author Ayar
 *
 */
public class TapHerderGameManager extends BaseGameManager {
	protected boolean renderUpToDate = true;

	@Override
	protected void initialize() {
		// NO-OP
	}

	@Override
	protected void cleanup() {
		// NO-OP
	}

	@Override
	public void run() {
		startGame();
	}

	/**
	 * @return the renderUpToDate
	 */
	public boolean isRenderUpToDate() {
		return renderUpToDate;
	}

	/**
	 * @param renderUpToDate the renderUpToDate to set
	 */
	public void setRenderUpToDate(boolean renderUpToDate) {
		this.renderUpToDate = renderUpToDate;
	}

	public boolean getRenderUpToDate() {
		return renderUpToDate;
	}
}
