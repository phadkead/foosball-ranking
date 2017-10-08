package com.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * MatchParticipants entity associated with MatchParticipants table
 * 
 * @author aditiphadke
 *
 */
@Entity
@Table(name = "MatchParticipants")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchParticipants {
	@Override
	public String toString() {
		return "MatchParticipants [id=" + id + ", player=" + player + ", match=" + ", isWinner=" + isWinner + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	private int id;

	private String player;

	@ManyToOne
	@JoinColumn(name = "match_id")
	private Match match;

	@JsonProperty(required = false)
	private boolean isWinner;

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public boolean isWinner() {
		return isWinner;
	}

	public void setWinner(boolean isWinner) {
		this.isWinner = isWinner;
	}

}
