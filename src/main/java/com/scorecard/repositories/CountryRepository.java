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

import com.scorecard.models.Country;

@Repository
public class CountryRepository implements RowMapper<Country> {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Country save(Country country) {
		String sql = "INSERT INTO country (code, name) values (?, ?)";
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, country.getCode());
				ps.setString(2, country.getName());
				return ps;
			}
		}, holder);
		country.setId((Integer) holder.getKeys().get("id"));
		return country;
	}

	public void update(Country country) {
		String sql = "Update country set code = '" + country.getCode() + "', name = '" + country.getName() + "' where id = "
				+ country.getId();
		System.out.println("executing SQL = " + sql);
		jdbcTemplate.update(sql);
	}

	public void delete(Integer id) {
		String sql = "delete from country  where id = " + id;
		System.out.println("executing SQL = " + sql);
		jdbcTemplate.update(sql);
	}

	public Country findOne(Integer id) {
		String sql = "select * from country  where id = ?";
		System.out.println("executing SQL = " + sql);
		Country country = jdbcTemplate.queryForObject(sql, new Object[] { id }, this);
		return country;
	}

	public List<Country> findAll() {
		String sql = "select * from country";
		List<Country> customers = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Country>(Country.class));
		return customers;
	}

	@Override
	public Country mapRow(ResultSet rs, int rowId) throws SQLException {
		Country country = new Country();
		country.setCode(rs.getString("code"));
		country.setId(rs.getInt("id"));
		country.setName(rs.getString("name"));
		return country;
	}
}
