package com.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.domain.Match;
import com.domain.Player;

/**
 * Handles all SQL queries
 * @author aditiphadke
 *
 */
@Repository
@Transactional
public class FoosBallRankingRepository {
	@Autowired
	private SessionFactory _sessionFactory;

	private Session getSession() {
		return _sessionFactory.getCurrentSession();
	}

	public void save(Match match) {
		getSession().save(match);
	}

	@SuppressWarnings({ "unchecked", "unused" })
	private List<Match> getResult() {
		Criteria criteria = getSession().createCriteria(Match.class);
		criteria.createAlias("matchParticipants", "mp");
		Criterion cn = Restrictions.in("mp.player", Arrays.asList("A", "B"));
		criteria.add(cn).setResultTransformer(new AliasToBeanResultTransformer(Match.class));
		return (List<Match>) criteria.list();
	}

	/**
	 * Get all players data along with total matches, won matches 
	 * @return player data
	 */
	public List<Player> getAllPlayers() {
		Query query = getSession()
				.createSQLQuery(
						"select player as name , " + "sum(case when isWinner=true  then 1 else 0 end) as wonMatches,"
								+ "count(*) as totalMatches " + "from matchparticipants group by player ")
				.addScalar("name").addScalar("totalMatches", StandardBasicTypes.INTEGER)
				.addScalar("wonMatches", StandardBasicTypes.INTEGER);

		query.setResultTransformer(Transformers.aliasToBean(Player.class));
		@SuppressWarnings("unchecked")
		List<Player> result = query.list();
		return result;
	}

	/**
	 * Get head 2 head records for two players
	 * @param player1
	 * @param player2
	 * @return head2head record
	 */
	public List<Player> getHead2Head(String player1, String player2) {
		Query query = getSession()
				.createSQLQuery("select  count(*) as totalMatches, "
						+ "sum(case when a.isWinner=true  then 1 else 0 end) as WinsForPlayer1 "
						+ "from matchparticipants a, matchparticipants b "
						+ "where a.match_id = b.match_id and a.player <> b.player "
						+ "and (a.player=? and b.player=?) and a.isWinner <> b.isWinner")
				.setString(0, player1).setString(1, player2);

		@SuppressWarnings("unchecked")
		List<Object[]> result = query.list();
		if (result != null) {
			Object[] row = result.get(0);
			Integer totalMatches = Integer.parseInt(row[0].toString());
			if (totalMatches > 0) {
				Integer winsForPlayer1 = Integer.parseInt(row[1].toString());
				Integer winsForPlayer2 = totalMatches - winsForPlayer1;

				List<Player> players = Arrays.asList(new Player(player1, totalMatches, winsForPlayer1),
						new Player(player2, totalMatches, winsForPlayer2));
				return players;
			}
		}
		return new ArrayList<>();
	}

}
