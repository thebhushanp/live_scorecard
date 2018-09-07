package com.scorecard.controllers;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scorecard.models.Player;
import com.scorecard.models.Score;

@RestController
public class WSPushController {

	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

	@GetMapping("/api/push")
	public void pushMsg() {
		Player player = new Player();
		player.setAge(22);
		player.setName("Okay");
		Score s1 = new Score();
		s1.setBalls(2);
		s1.setIsOut(false);
		s1.setPlayer(player);
		s1.setRuns(22);
		Score s2 = new Score();
		s2.setBalls(2);
		s2.setIsOut(false);
		s2.setPlayer(new Player());
		s2.setRuns(122);
		messagingTemplate.convertAndSend("/topic/public", Arrays.asList(s1, s2));
	}

}
