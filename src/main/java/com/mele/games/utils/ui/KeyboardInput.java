package com.mele.games.utils.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.mele.games.mechanics.ICommand;
import com.mele.games.mechanics.IGameInput;
import com.mele.games.mechanics.IGameManager;

/**
 * Input class designed to read input from the console.
 * Used in test only.
 * 
 * @author Ayar
 *
 */
public class KeyboardInput implements IGameInput {
	protected static Logger log = Logger.getLogger(KeyboardInput.class);
	
	Scanner input= new Scanner(System.in); 
    boolean kill = false;
    List<ICommand> commandQueue = new ArrayList<ICommand>();
    IGameManager gm = null;
    
	public void run() {
		while (!kill) {
			queueCommand();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				log.error(e.getLocalizedMessage());
				kill = true;
			}
		}
	}

	@Override
	public ICommand readCommand() {
		ICommand command = null;
		
		if ( commandQueue.size() > 0) {
			command = commandQueue.remove(0);
		}
		
		return command;
	}

	@Override
	public void kill() {
		kill = true;
	}

	protected void queueCommand() {
		String command = input.next(); // Get what the user types.
		BaseKeyboardCommand com = new BaseKeyboardCommand(command);
		
		commandQueue.add(com);
	}

	@Override
	public void setGameManager(IGameManager gameManager) {
		gm = gameManager;
	}
}
