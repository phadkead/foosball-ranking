package com.domain;

/**
 * Player data to return from API
 * @author aditiphadke
 *
 */
public class Player {
	private String name;
	private int totalMatches;
	private int wonMatches;

	public Player() {
	}

	public Player(String name, int totalMatches, int wonMatches) {
		this.name = name;
		this.totalMatches = totalMatches;
		this.wonMatches = wonMatches;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalMatches() {
		return totalMatches;
	}

	public void setTotalMatches(int totalMatches) {
		this.totalMatches = totalMatches;
	}

	public int getWonMatches() {
		return wonMatches;
	}

	public void setWonMatches(int wonMatches) {
		this.wonMatches = wonMatches;
	}
}
