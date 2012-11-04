package com.mele.games.mechanics;

/**
 * Defines constant scoring event objects.  This overly-complicated
 * scoring model is built with the idea that achievements or statistics 
 * might require tracking of what kinds of events generated scores.
 * 
 * @author Ayar
 *
 */
public class ScoreEvent {
	public static final ScoreEvent SCORE_DEADRESIDENT = new ScoreEvent("SC_DEADRES", -50);
	public static final ScoreEvent SCORE_DEADANTIRES = new ScoreEvent("SC_DEADANTI", 50);
	public static final ScoreEvent SCORE_RESGOAL = new ScoreEvent("SC_RESGOAL", 100);
	public static final ScoreEvent SCORE_TOHAZARD = new ScoreEvent("SC_TOHAZARD", 25);
	public static final ScoreEvent SCORE_TOSAFE = new ScoreEvent("SC_TOSAFE", -25);
	public static final ScoreEvent SCORE_TAP = new ScoreEvent("SC_TAP", -1);
	
	protected String event;
	protected int score;
	
	public ScoreEvent(String event, int score) {
		this.event = event;
		this.score = score;
	}
	
	/**
	 * @return the event
	 */
	public String getEvent() {
		return event;
	}
	/**
	 * @param event the event to set
	 */
	public void setEvent(String event) {
		this.event = event;
	}
	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	
}
