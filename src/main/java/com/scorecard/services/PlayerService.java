package com.scorecard.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scorecard.models.Player;
import com.scorecard.repositories.PlayerRepository;

@Service
public class PlayerService {

	@Autowired
	PlayerRepository playerRepo;

	public Player save(Player player) {
		return playerRepo.save(player);
	}

	public void update(Player player) {
		playerRepo.update(player);
	}

	public void delete(Integer id) {
		playerRepo.delete(id);
	}

	public Player findOne(Integer id) {
		return playerRepo.findOne(id);
	}

	public List<Player> findAll() {
		return playerRepo.findAll();
	}
}
