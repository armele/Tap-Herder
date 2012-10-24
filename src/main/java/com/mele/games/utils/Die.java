package com.mele.games.utils;

import java.util.Date;
import java.util.Random;

import org.apache.log4j.Logger;

/**
 * @author Al
 *
 */
public class Die {
	protected static Logger log = Logger.getLogger(Die.class);
	
	private Integer sides = null;
	private int value;
	static private int rollCounter = 0;
	
	public Die(int diceSides) {
		if (diceSides < 2) {
			throw new RuntimeException("Illegal sides for die: " + diceSides);
		}
		
		rollCounter++;
		sides = new Integer(diceSides);
		
		try {
			value = roll();
		} catch (GameException ex) {
			log.error(ex.getLocalizedMessage());
		}
	}
	
	/**
	 * Roll the die.  Returns null if this die has no sides!
	 * @return
	 */
	public int roll() throws GameException {
		Integer result = null;
		
		if (sides != null) {
			Date seed = new Date();
			Random generator = new Random(seed.getTime() + (rollCounter * 120128));
			int roll = generator.nextInt(sides.intValue()) + 1;
			result = new Integer(roll);
		}
		
		if (result == null) {
			throw new GameException("No die sides set!");
		} else {
			value = result.intValue();
		}
		
		return result.intValue();
	}
	
	/**
	 * @return Returns the sides.
	 */
	public Integer getSides() {
		return sides;
	}
	/**
	 * @param sides The sides to set.
	 */
	public void setSides(Integer sides) {
		this.sides = sides;
	}
	/**
	 * @return Returns the value.
	 */
	public int getValue() {
		return value;
	}
}
