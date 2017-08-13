package com.mele.games.mechanics;

import java.util.ArrayList;

/**
 * Collects a list of ScoreEvent objects for the purpose of tracking the score.
 * 
 * @author Ayar
 *
 */
public class ScoreLog {
	protected ArrayList<ScoreEvent> scoreList = new ArrayList<ScoreEvent>();
	
	public void addScore(ScoreEvent score) {
		scoreList.add(score);
	}
	
	/**
	 * @return
	 */
	public int scoreTotal() {
		int total = 0;
		
		for (ScoreEvent event : scoreList) {
			total = total + event.getScore();
		}
		
		return total;
	}
	
	/**
	 * @return
	 */
	public int moves() {
		int moves = 0;
		
		for (ScoreEvent event : scoreList) {
			if (ScoreEvent.SCORE_TAP.equals(event)) {
				moves++;
			}
		}	
		
		return moves;
	}
}
