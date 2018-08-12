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

import com.scorecard.models.MatchType;

@Repository
public class MatchTypeRepository implements RowMapper<MatchType> {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public MatchType save(MatchType matchtype) {
		String sql = "INSERT INTO matchtype (name) values (?)";
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, matchtype.getName());
				return ps;
			}
		}, holder);
		matchtype.setId((Integer) holder.getKeys().get("id"));
		return matchtype;
	}

	public void update(MatchType matchtype) {
		String sql = "Update matchtype set name = '" + matchtype.getName() + "' where id = "
				+ matchtype.getId();
		System.out.println("executing SQL = " + sql);
		jdbcTemplate.update(sql);
	}

	public void delete(Integer id) {
		String sql = "delete from matchtype  where id = " + id;
		System.out.println("executing SQL = " + sql);
		jdbcTemplate.update(sql);
	}

	public MatchType findOne(Integer id) {
		String sql = "select * from matchtype  where id = ?";
		System.out.println("executing SQL = " + sql);
		MatchType matchtype = jdbcTemplate.queryForObject(sql, new Object[] { id }, this);
		return matchtype;
	}

	public List<MatchType> findAll() {
		String sql = "select * from matchtype";
		List<MatchType> matchtypes = jdbcTemplate.query(sql, new BeanPropertyRowMapper<MatchType>(MatchType.class));
		return matchtypes;
	}

	@Override
	public MatchType mapRow(ResultSet rs, int rowId) throws SQLException {
		MatchType matchtype = new MatchType();
		matchtype.setId(rs.getInt("id"));
		matchtype.setName(rs.getString("name"));
		return matchtype;
	}
}
