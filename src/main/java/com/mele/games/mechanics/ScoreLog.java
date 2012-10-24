package com.mele.games.mechanics;

import java.util.ArrayList;

public class ScoreLog {
	protected ArrayList<ScoreEvent> scoreList = new ArrayList<ScoreEvent>();
	
	public void addScore(ScoreEvent score) {
		scoreList.add(score);
	}
	
	public int scoreTotal() {
		int total = 0;
		
		for (ScoreEvent event : scoreList) {
			total = total + event.getScore();
		}
		
		return total;
	}
}
