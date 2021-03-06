package com.scorecard.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scorecard.models.Player;
import com.scorecard.models.Team;
import com.scorecard.services.TeamService;

@RestController
public class TeamController {

	@Autowired
	TeamService teamService;

	@GetMapping("/api/team/{id}")
	public Team findTeam(@PathVariable("id") Integer id) {
		return teamService.findOne(id);
	}

	@GetMapping("/api/teams")
	public List<Team> findAll() {
		return teamService.findAll();
	}

	// to create
	@PostMapping("/api/team")
	public Team add(@RequestBody Team team) {
		System.out.println(team.getTeamname());
		teamService.save(team);
		return team;
	}

	// to update
	@PutMapping("/api/team")
	public void update(@RequestBody Team team) {
		teamService.update(team);
	}

	// to delete
	@DeleteMapping("/api/team/{id}")
	public void delete(@PathVariable("id") Integer id) {
		teamService.delete(id);
	}

	@GetMapping("/api/team/addplayer")
	public void addPlayerToTeam(@RequestParam("playerid") Integer playerId, @RequestParam("teamid") Integer teamId) {
		teamService.addPlayerToTeam(playerId, teamId);
	}

	@GetMapping("/api/team/getplayers")
	public List<Player> getPlayers(@RequestParam("teamid") Integer teamId) {
		return teamService.getPlayers(teamId);
	}
}
