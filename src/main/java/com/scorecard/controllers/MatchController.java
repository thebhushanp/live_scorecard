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

import com.scorecard.models.Match;
import com.scorecard.services.MatchService;

@RestController
public class MatchController {

	@Autowired
	MatchService matchService;

	@GetMapping("/api/match/{id}")
	public Match findOne(@PathVariable("id") Integer id) {
		return matchService.findOne(id);
	}

	@GetMapping("/api/matches")
	public List<Match> findAll() {
		return matchService.findAll();
	}

	//to create
	@PostMapping("/api/match")
	public Match add(@RequestBody Match match) {
		matchService.save(match);
		return match;
	}

	//to update
	@PutMapping("/api/match")
	public void update(@RequestBody Match match) {
		matchService.update(match);
	}

	//to delete
	@DeleteMapping("/api/match/{id}")
	public void delete(@PathVariable("id") Integer id) {
		matchService.delete(id);
	}

}
