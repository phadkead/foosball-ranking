package com.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Match entity associated with MATCH table
 * @author aditiphadke
 *
 */
@Entity
@Table(name = "Match")
public class Match {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="match_id")
	private int matchId;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "match_id")
	private List<MatchParticipants> matchParticipants;
	
	public List<MatchParticipants> getMatchParticipants() {
		return matchParticipants;
	}
	
	public void setMatchParticipants(List<MatchParticipants> matchParticipants) {
		this.matchParticipants = matchParticipants;
	}

	public int getMatchId() {
		return matchId;
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}

	@Override
	public String toString() {
		return "Match [matchId=" + matchId + ", matchParticipants=" + matchParticipants + "]";
	}
}
