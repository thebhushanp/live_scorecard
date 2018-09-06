package com.scorecard.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scorecard.models.Score;
import com.scorecard.repositories.ScoreRepository;

@Service
public class ScoreService {

	@Autowired
	private ScoreRepository scoreRepo;

	public void updateScore(Integer incrRun, Integer matchId, Integer playerId) {
		scoreRepo.updateScore(incrRun, matchId, playerId);
		
	}

	public List<Score> pullScore(Integer matchId, Integer teamId) {
		return scoreRepo.pullScore(matchId, teamId);
	}

}
