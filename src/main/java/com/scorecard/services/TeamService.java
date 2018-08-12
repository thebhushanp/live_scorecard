package com.scorecard.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scorecard.models.Team;
import com.scorecard.repositories.TeamRepository;

@Service
public class TeamService {

	@Autowired
	TeamRepository tRepository;

	public Team save(Team team) {
		return tRepository.save(team);
	}

	public void update(Team team) {
		tRepository.update(team);
	}

	public void delete(Integer id) {
		tRepository.delete(id);
	}

	public Team findOne(Integer id) {
		return tRepository.findOne(id);
	}

	public List<Team> findAll() {
		return tRepository.findAll();
	}
}
