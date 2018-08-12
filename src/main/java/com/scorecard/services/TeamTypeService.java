package com.scorecard.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scorecard.models.TeamType;
import com.scorecard.repositories.TeamTypeRepository;

@Service
public class TeamTypeService {

	@Autowired
	TeamTypeRepository ttRepository;

	public TeamType save(TeamType teamtype) {
		return ttRepository.save(teamtype);
	}

	public void update(TeamType teamtype) {
		ttRepository.update(teamtype);
	}

	public void delete(Integer id) {
		ttRepository.delete(id);
	}

	public TeamType findOne(Integer id) {
		return ttRepository.findOne(id);
	}

	public List<TeamType> findAll() {
		return ttRepository.findAll();
	}
}
