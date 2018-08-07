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

import com.scorecard.models.Player;

@Repository
public class PlayerRepository implements RowMapper<Player> {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Player save(Player player) {
		String sql = "INSERT INTO player (age, name) values (?, ?)";
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, player.getAge());
				ps.setString(2, player.getName());
				return ps;
			}
		}, holder);
		player.setId((Integer) holder.getKeys().get("id"));
		return player;
	}

	public void update(Player player) {
		String sql = "Update player set age = '" + player.getAge() + "', name = '" + player.getName() + "' where id = "
				+ player.getId();
		System.out.println("executing SQL = " + sql);
		jdbcTemplate.update(sql);
	}

	public void delete(Integer id) {
		String sql = "delete from player where id = " + id;
		System.out.println("executing SQL = " + sql);
		jdbcTemplate.update(sql);
	}

	public Player findOne(Integer id) {
		String sql = "select * from player where id = ?";
		System.out.println("executing SQL = " + sql);
		Player player = jdbcTemplate.queryForObject(sql, new Object[] { id }, this);
		return player;
	}

	public List<Player> findAll() {
		String sql = "select * from player";
		List<Player> players = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Player>(Player.class));
		return players;
	}

	@Override
	public Player mapRow(ResultSet rs, int rowId) throws SQLException {
		Player player = new Player();
		player.setAge(rs.getInt("age"));
		player.setId(rs.getInt("id"));
		player.setName(rs.getString("name"));
		return player;
	}
}
