package com.scorecard.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.scorecard.models.Score;

@Repository
public class ScoreRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void updateScore(Integer batsman, Integer incrRun, Integer matchId, Integer playerId) {
		String findScore = "select id from score where player_id=" + playerId + " and match_id=" + matchId + ";";
		Map<String, Object> score = jdbcTemplate.queryForMap(findScore);
		if (score.containsKey("id")) {
			Integer id = (Integer) score.get("id");
			Integer run = (Integer) score.get("runs");
			Integer balls = (Integer) score.get("balls") + 1;
			run = run + incrRun;
			String updateSql = "update score set runs=" + run + " balls=" + balls + " where id=" + id + ";";
			System.out.println(updateSql);
			jdbcTemplate.update(updateSql);
		} else {
			String sql = "INSERT INTO team (runs,player_id,match_id,is_out,balls) values (" + incrRun + "," + playerId
					+ "," + matchId + "," + false + "," + 1 + ")";
			System.out.println(sql);
			jdbcTemplate.update(sql);
		}
	}

	public List<Score> pullScore(Integer matchId, Integer teamId) {

		return null;
	}
}
