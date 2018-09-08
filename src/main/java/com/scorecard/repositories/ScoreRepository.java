package com.scorecard.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.scorecard.models.Player;
import com.scorecard.models.Score;

@Repository
public class ScoreRepository implements RowMapper<Score> {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void updateScore(Integer incrRun, Integer matchId, Integer playerId) {
		String findScore = "select id,balls, runs from score where player_id=" + playerId + " and match_id=" + matchId + ";";
		try {
			Map<String, Object> score = jdbcTemplate.queryForMap(findScore);
			if (score.containsKey("id")) {
				Integer id = (Integer) score.get("id");
				Integer run = (Integer) score.get("runs");
				if(run == null) {
					run = 0;
				}
				Integer balls = (Integer) score.get("balls");
				if (balls == null) {
					balls = 0;
				} 
				balls++;
				run = run + incrRun;
				String updateSql = "update score set runs=" + run + ", balls=" + balls + " where id=" + id + ";";
				System.out.println(updateSql);
				jdbcTemplate.update(updateSql);

			} else {
				String sql = "INSERT INTO score (runs,player_id,match_id,is_out,balls) values (" + incrRun + ","
						+ playerId + "," + matchId + "," + false + "," + 1 + ")";
				System.out.println(sql);
				jdbcTemplate.update(sql);
			}
		} catch (EmptyResultDataAccessException e) {
			String sql = "INSERT INTO score (runs,player_id,match_id,is_out,balls) values (" + incrRun + "," + playerId
					+ "," + matchId + "," + false + "," + 1 + ")";
			System.out.println(sql);
			jdbcTemplate.update(sql);
		}
	}

	public List<Score> pullScore(Integer matchId, Integer teamId) {
		String getScoreWithPlayer = "select s.id id, s.balls balls, s.runs runs, s.is_out, p.name, p.id pid"
				+ "  from score s, player p where s.player_id=p.id and s.match_id=" + matchId + ";";
		/*
		 * String getScoreByTeam = "select * from score where player_id in " +
		 * "(select player_id from team_player_mapping  where team_id  = " + teamId +
		 * ") and match_id=" + matchId + ";";
		 */
		return jdbcTemplate.query(getScoreWithPlayer, this);
	}

	@Override
	public Score mapRow(ResultSet rs, int rowId) throws SQLException {
		Score score = new Score();
		score.setId(rs.getInt("id"));
		score.setBalls(rs.getInt("balls"));
		score.setRuns(rs.getInt("runs"));
		score.setIsOut(rs.getBoolean("is_out"));
		Player player = new Player();
		player.setName(rs.getString("name"));
		player.setId(rs.getInt("pid"));
		score.setPlayer(player);
		return score;
	}
}
