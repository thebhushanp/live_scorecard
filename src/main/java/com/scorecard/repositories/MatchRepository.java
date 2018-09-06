package com.scorecard.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.scorecard.models.Match;

@Repository
public class MatchRepository implements RowMapper<Match> {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Match save(Match match) {
		String sql = "INSERT INTO match (matchtype,vengue,team1,team2) values (?,?,?,?)";
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, match.getMatchtype());
				ps.setString(2, match.getVengue());
				ps.setInt(3, match.getTeam1());
				ps.setInt(4, match.getTeam2());
				return ps;
			}
		}, holder);
		match.setId((Integer) holder.getKeys().get("id"));
		return match;
	}

	public void update(Match match) {
		String sql = "Update match set matchtype = '" + match.getMatchtype() + "', vengue = '" + match.getVengue()
		+ "', team1 = '" + match.getTeam1() + "',team2 = '" + match.getTeam2() + "' where id =  " + match.getId();
		System.out.println("executing SQL = " + sql);
		jdbcTemplate.update(sql);
	}

	public void delete(Integer id) {
		String sql = "delete from match  where id = " + id;
		System.out.println("executing SQL = " + sql);
		jdbcTemplate.update(sql);
	}

	public Match findOne(Integer id) {
		String sql = "select * from match  where id = ?";
		System.out.println("executing SQL = " + sql);
		Match match = jdbcTemplate.queryForObject(sql, new Object[] { id }, this);
		return match;
	}

	public List<Match> findAll() {
		String sql = "select * from match";
		List<Match> matchs = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Match>(Match.class));
		return matchs;
	}

	@Override
	public Match mapRow(ResultSet rs, int rowId) throws SQLException {
		Match match = new Match();
		match.setId(rs.getInt("id"));
		match.setMatchtype(rs.getInt("matchtype"));
		match.setVengue(rs.getString("vengue"));
		match.setTeam1(rs.getInt("team1"));
		match.setTeam2(rs.getInt("team2"));
		return match;
	}
}
