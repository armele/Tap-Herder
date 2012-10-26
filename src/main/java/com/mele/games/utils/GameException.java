package com.mele.games.utils;

import java.io.PrintStream;

/**
 * Base exception logic.
 * 
 * @author Ayar
 *
 */
public class GameException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GameException (String message) {
		super(message);
	}
	
	public GameException (String message, Throwable source) {
		super(message, source);
	}
	
	/**
	 * @param t
	 * @return
	 */
	static public String fullExceptionInfo(Throwable t) {
		StringBuffer buf = new StringBuffer();
		
		if (t != null) {
			buf.append(t.getLocalizedMessage());
			buf.append("\nCaused by:");
			buf.append(fullExceptionInfo(t.getCause()));
			buf.append("\nStack:");
			
			PrintStream stack = new PrintStream(System.out);
			
			t.printStackTrace(stack);
			buf.append(stack.toString());
		} else {
			buf.append("(null)");
		}
		
		return buf.toString();
	}
}
