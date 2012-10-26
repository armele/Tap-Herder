package com.mele.tapHerder.ui;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;

import com.mele.games.mechanics.ICommand;
import com.mele.games.mechanics.IGameInput;
import com.mele.games.mechanics.IGameManager;
import com.mele.games.utils.hexarray.Cell;
import com.mele.tapHerder.TapCommand;
import com.mele.tapHerder.TapHerderGameManager;

/**
 * Listens for mouse clicks on the display window and queues them
 * as commands to the game.
 * 
 * @author Ayar
 *
 */
public class TapHerderInput implements IGameInput, MouseListener {
	protected static Logger log = Logger.getLogger(TapHerderInput.class);
	
	protected TapHerderGameManager gameManager = null;
	protected boolean kill = false;
	protected boolean initialized = false;
	protected ConcurrentLinkedQueue<ICommand> commandList = new ConcurrentLinkedQueue<ICommand>();
	
	/*
	@Override
	public void run() {
		if (!initialized) {
			init();
		}
		
		while (!kill) {
			
			try {
				Thread.sleep(41);
			} catch (InterruptedException e) {
				log.error(GameException.fullExceptionInfo(e));
			}
		}
		
	}
	*/
	
	protected void init() {
		// TODO
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		Point p = arg0.getPoint();
		Cell target = ((TapHerderGameRenderer)gameManager.getRenderer()).getCellAtPoint(p);
		
		if (target != null) {
			log.info("Le mouse: " + target.getPoint());
			
			TapCommand cmd = new TapCommand();
			cmd.setTapLocation(target);
			commandList.add(cmd);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public ICommand readCommand() {
		ICommand cmd = null;
		
		if (commandList.size() > 0) {
			cmd = commandList.remove(); 
		}
		
		return cmd;
	}

	@Override
	public void kill() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setGameManager(IGameManager game) {
		gameManager = (TapHerderGameManager) game;
	}

}
