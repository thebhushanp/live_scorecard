package com.scorecard.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scorecard.models.MatchType;
import com.scorecard.repositories.MatchTypeRepository;

@Service
public class MatchTypeService {

	@Autowired
	MatchTypeRepository mtRepository;

	public MatchType save(MatchType matchtype) {
		return mtRepository.save(matchtype);
	}

	public void update(MatchType matchtype) {
		mtRepository.update(matchtype);
	}

	public void delete(Integer id) {
		mtRepository.delete(id);
	}

	public MatchType findOne(Integer id) {
		return mtRepository.findOne(id);
	}

	public List<MatchType> findAll() {
		return mtRepository.findAll();
	}
}
