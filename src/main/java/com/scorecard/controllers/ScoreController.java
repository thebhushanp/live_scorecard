package com.scorecard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scorecard.services.ScoreService;

@RestController
public class ScoreController {

	@Autowired
	private ScoreService scoreService;

	@GetMapping("/api/score/addruns")
	public void updateScore(@RequestParam("batsman") Integer batsman, @RequestParam("run") Integer incrRun
			, @RequestParam("matchId") Integer matchId, @RequestParam("playerId") Integer playerId) {
		scoreService.updateScore(batsman, incrRun, matchId, playerId);
	}
}
