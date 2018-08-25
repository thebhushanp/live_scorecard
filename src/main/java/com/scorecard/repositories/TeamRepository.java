package com.scorecard.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.scorecard.models.Player;
import com.scorecard.models.Team;
import com.scorecard.models.TeamType;

@Repository
public class TeamRepository implements RowMapper<Team> {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Team save(Team team) {
		String sql = "INSERT INTO team (teamtype,captain,wicketkepper,teamname) values (?, ?,?, ?)";
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, team.getTeamtype().getId());
				ps.setInt(2, team.getCaptain().getId());
				ps.setInt(3, team.getWicketkepper().getId());
				ps.setString(4, team.getTeamname());
				return ps;
			}
		}, holder);
		team.setId((Integer) holder.getKeys().get("id"));
		return team;
	}

	public void update(Team team) {
		String sql = "Update team set teamtype = '" + team.getTeamtype() + "', captain = '" + team.getCaptain() + "',wicketkepper = '" + team.getWicketkepper() + "'  where id = "
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
		String sql = "select b.id id, b.teamname teamname, d.id cap_id, d.name captain, e.id wk_id, e.name wk, f.id tt_id, f.name teamtype from team b, player d, player e, teamtype f where b.captain = d.id   and b.wicketkepper = e.id  and b.teamtype = f.id and b.id = ?;";
		System.out.println("executing SQL = " + sql);
		Team team = jdbcTemplate.queryForObject(sql, new Object[] { id }, this);
		return team;
	}

	public List<Team> findAll() {
		String sql = "select b.id id, b.teamname teamname, d.id cap_id, d.name captain, e.id wk_id, e.name wk, f.id tt_id, f.name teamtype from team b, player d, player e, teamtype f where b.captain = d.id   and b.wicketkepper = e.id  and b.teamtype = f.id;";
		List<Team> teams = jdbcTemplate.query(sql, this);
		return teams;
	}

	@Override
	public Team mapRow(ResultSet rs, int rowId) throws SQLException {
		Team team = new Team();
		team.setId(rs.getInt("id"));
		team.setTeamname(rs.getString("teamname"));
		TeamType tt = new TeamType();
		tt.setId(rs.getInt("tt_id"));
		tt.setName(rs.getString("teamtype"));
		team.setTeamtype(tt);
		Player cap = new Player();
		cap.setId(rs.getInt("cap_id"));
		cap.setName(rs.getString("captain"));
		Player wk = new Player();
		wk.setId(rs.getInt("wk_id"));
		wk.setName(rs.getString("wk"));
		team.setCaptain(cap);
		team.setWicketkepper(wk);
		return team;
	}
}
