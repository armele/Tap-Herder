package com.mele.tapHerder;

import com.mele.games.mechanics.BaseGameManager;
import com.mele.games.mechanics.IGame;
import com.mele.games.mechanics.IGameInput;
import com.mele.games.mechanics.IGameRenderer;
import com.mele.tapHerder.ui.TapHerderGameRenderer;
import com.mele.tapHerder.ui.TapHerderInput;

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
		renderer = new TapHerderGameRenderer();
		renderer.setGameManager(this);
		game = new TapHerderGame();
		game.setGameManager(this);
		input = new TapHerderInput();
		input.setGameManager(this);
	}

	@Override
	protected void cleanup() {
		// NO-OP
	}

	@Override
	public void run() {
		startGame();
	}

	@Override
	public IGameRenderer getRenderer() {
		return renderer;
	}

	@Override
	public IGame getGame() {
		return game;
	}

	@Override
	public IGameInput getGameInput() {
		return input;
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
