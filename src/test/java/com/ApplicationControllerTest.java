package com;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.domain.Match;
import com.domain.MatchParticipants;
import com.domain.Player;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.FoosBallRankingService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FoosballRankingSystemApplication.class)
@WebAppConfiguration
public class ApplicationControllerTest {

	private MockMvc mockMvc;

	// private MediaType contentType = new MediaType("application", "hal+json",
	// Charset.forName("UTF-8"));

	@Mock
	private FoosBallRankingService foosBallRankingService;

	@InjectMocks
	private ApplicationController userController;

	private List<Player> players = new ArrayList<>();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	public void shouldAddResults() throws Exception {
		Match match = new Match();
		MatchParticipants par1 = new MatchParticipants();
		par1.setPlayer("A");
		par1.setWinner(false);

		MatchParticipants par2 = new MatchParticipants();
		par2.setPlayer("B");
		par2.setWinner(false);

		MatchParticipants par3 = new MatchParticipants();
		par3.setPlayer("C");
		par3.setWinner(true);

		MatchParticipants par4 = new MatchParticipants();
		par4.setPlayer("D");
		par4.setWinner(true);

		match.setMatchParticipants(Arrays.asList(par1, par2, par3, par4));

		System.out.println(converToJson(match));
		mockMvc.perform(
				post("/foosballRanking/match").contentType(MediaType.APPLICATION_JSON).content(converToJson(match)))
				.andExpect((status().isOk()));

	}

	@Test
	public void shouldGetPlayers() throws Exception {
		Player player1 = new Player("A", 12, 6);
		Player player2 = new Player("B", 12, 6);

		players.add(player1);
		players.add(player2);

		Mockito.when(foosBallRankingService.getAllPlayers()).thenReturn(players);

		mockMvc.perform(get("/foosballRanking/match/players").
				accept(MediaType.APPLICATION_JSON_UTF8).contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].name", is(this.players.get(0).getName())));
	}

	public String converToJson(Match match) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(match);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
