package com.scorecard.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import com.scorecard.models.Score;
import com.scorecard.repositories.ScoreRepository;

@Service
public class ScoreService {

	@Autowired
	private ScoreRepository scoreRepo;
	
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

	public void updateScore(Integer incrRun, Integer matchId, Integer playerId) {
		scoreRepo.updateScore(incrRun, matchId, playerId);
		messagingTemplate.convertAndSend("/topic/public", pullScore(matchId, 2));
	}

	public List<Score> pullScore(Integer matchId, Integer teamId) {
		return scoreRepo.pullScore(matchId, teamId);
	}

}
