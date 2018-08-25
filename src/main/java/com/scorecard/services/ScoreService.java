package com.scorecard.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scorecard.repositories.ScoreRepository;

@Service
public class ScoreService {

	@Autowired
	private ScoreRepository scoreRepo;
	
	public void updateScore(Integer batsman, Integer incrRun, Integer matchId, Integer playerId) {
		scoreRepo.updateScore(batsman, incrRun, matchId, playerId);
	}

}
