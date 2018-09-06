package com.scorecard.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scorecard.models.Score;
import com.scorecard.services.ScoreService;

@RestController
public class ScoreController {

	@Autowired
	private ScoreService scoreService;

	@PutMapping("/api/score/addruns")
	public void updateScore(@RequestParam("run") Integer incrRun,
			@RequestParam("matchId") Integer matchId, @RequestParam("playerId") Integer playerId) {
		scoreService.updateScore(incrRun, matchId, playerId);
	}

	@GetMapping("/api/score/pull")
	public List<Score> getScoreBoard(@RequestParam("matchId") Integer matchId, @RequestParam("teamId") Integer teamId) {
		return scoreService.pullScore(matchId, teamId);
	}
}
