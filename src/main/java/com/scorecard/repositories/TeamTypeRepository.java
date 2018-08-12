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

import com.scorecard.models.TeamType;

@Repository
public class TeamTypeRepository implements RowMapper<TeamType> {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public TeamType save(TeamType teamtype) {
		String sql = "INSERT INTO teamtype (name) values (?)";
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, teamtype.getName());
				return ps;
			}
		}, holder);
		teamtype.setId((Integer) holder.getKeys().get("id"));
		return teamtype;
	}

	public void update(TeamType teamtype) {
		String sql = "Update teamtype set  name = '" + teamtype.getName() + "' where id = "
				+ teamtype.getId();
		System.out.println("executing SQL = " + sql);
		jdbcTemplate.update(sql);
	}

	public void delete(Integer id) {
		String sql = "delete from teamtype  where id = " + id;
		System.out.println("executing SQL = " + sql);
		jdbcTemplate.update(sql);
	}

	public TeamType findOne(Integer id) {
		String sql = "select * from teamtype  where id = ?";
		System.out.println("executing SQL = " + sql);
		TeamType teamtype = jdbcTemplate.queryForObject(sql, new Object[] { id }, this);
		return teamtype;
	}

	public List<TeamType> findAll() {
		String sql = "select * from teamtype";
		List<TeamType> teamtypes = jdbcTemplate.query(sql, new BeanPropertyRowMapper<TeamType>(TeamType.class));
		return teamtypes;
	}

	@Override
	public TeamType mapRow(ResultSet rs, int rowId) throws SQLException {
		TeamType teamtype = new TeamType();
		teamtype.setId(rs.getInt("id"));
		teamtype.setName(rs.getString("name"));
		return teamtype;
	}
}
