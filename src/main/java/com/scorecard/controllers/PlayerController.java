package com.scorecard.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scorecard.models.Player;
import com.scorecard.services.PlayerService;

@RestController
public class PlayerController {

	@Autowired
	PlayerService playerService;

	@GetMapping("/api/player/{id}")
	public Player findPlayer(@PathVariable("id") Integer id) {
		return playerService.findOne(id);
	}

	@GetMapping("/api/players")
	public List<Player> findAll() {
		return playerService.findAll();
	}

	//to create
	@PostMapping("/api/player")
	public Player add(@RequestBody Player player) {
		playerService.save(player);
		return player;
	}

	//to update
	@PutMapping("/api/player")
	public void update(@RequestBody Player player) {
		playerService.update(player);
	}

	//to delete
	@DeleteMapping("/api/player/{id}")
	public void delete(@PathVariable("id") Integer id) {
		playerService.delete(id);
	}

}
