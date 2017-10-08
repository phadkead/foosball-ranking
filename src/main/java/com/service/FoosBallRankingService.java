package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.Match;
import com.domain.Player;
import com.repository.FoosBallRankingRepository;

/**
 * Foosball service
 * @author aditiphadke
 *
 */
@Service
public class FoosBallRankingService {

	@Autowired
	FoosBallRankingRepository repo;

	public void addMatch(Match match) {
		repo.save(match);
	}

	public List<Player> getAllPlayers() {
		return repo.getAllPlayers();
	}

	public List<Player> getHead2Head(String player1, String player2) {
		return repo.getHead2Head(player1, player2);
	}
}
