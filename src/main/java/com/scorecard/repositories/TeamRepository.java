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

import com.scorecard.models.Team;

@Repository
public class TeamRepository implements RowMapper<Team> {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Team save(Team team) {
		String sql = "INSERT INTO team (teamtype,captainname,wicketkepper) values (?, ?,?)";
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, team.getTeamType());
				ps.setInt(2, team.getCaptain());
				ps.setInt(3, team.getWicketKepper());
				return ps;
			}
		}, holder);
		team.setId((Integer) holder.getKeys().get("id"));
		return team;
	}

	public void update(Team team) {
		String sql = "Update team set teamtype = '" + team.getTeamType() + "', captainname = '" + team.getCaptain() + "',wicketkepper = '" + team.getWicketKepper() + "'  where id = "
				+ team.getId();
		System.out.println("executing SQL = " + sql);
		jdbcTemplate.update(sql);
	}

	public void delete(Integer id) {
		String sql = "delete from team  where id = " + id;
		System.out.println("executing SQL = " + sql);
		jdbcTemplate.update(sql);
	}

	public Team findOne(Integer id) {
		String sql = "select * from team  where id = ?";
		System.out.println("executing SQL = " + sql);
		Team team = jdbcTemplate.queryForObject(sql, new Object[] { id }, this);
		return team;
	}

	public List<Team> findAll() {
		String sql = "select * from team";
		List<Team> teams = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Team>(Team.class));
		return teams;
	}

	@Override
	public Team mapRow(ResultSet rs, int rowId) throws SQLException {
		Team team = new Team();
		team.setTeamType(rs.getInt("teamtype"));
		team.setCaptain(rs.getInt("captain"));
		team.setWicketKepper(rs.getInt("wicketkepper"));
		return team;
	}
}
