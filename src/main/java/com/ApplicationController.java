package com;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.domain.Match;
import com.domain.Player;
import com.service.FoosBallRankingService;

/**
 * The controller for application
 * @author aditiphadke
 *
 */
@RestController
@RequestMapping("/foosballRanking/match")
public class ApplicationController {
	@Autowired
	private FoosBallRankingService foosBallRankingService;

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationController.class);

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addMatchResults(@RequestBody Match match) {
		LOGGER.info("addMatchResults {}",match.toString() );
		
		foosBallRankingService.addMatch(match);
	}

//
//	@RequestMapping(method = RequestMethod.GET, value="lossRatio")
//	public double getMatchResults(@RequestParam String player) {
//		return foosBallRankingService.getwonMatches(player);
//	}
	
	@RequestMapping(method = RequestMethod.GET, value="players", produces= MediaType.APPLICATION_JSON_VALUE)
	public List<Player> getAllPlayers() {
		//LOGGER.info("addMatchResults {}", match);
		return foosBallRankingService.getAllPlayers();
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/head2head/players")
	public List<Player> getHead2HeadForPlayers(@RequestParam String player1, @RequestParam String player2) {
		return foosBallRankingService.getHead2Head(player1,player2);
	}
}
