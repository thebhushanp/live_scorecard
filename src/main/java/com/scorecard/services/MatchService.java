package com.scorecard.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scorecard.models.Match;
import com.scorecard.repositories.MatchRepository;

@Service
public class MatchService {

	@Autowired
	MatchRepository mRepository;

	public Match save(Match match) {
		return mRepository.save(match);
	}

	public void update(Match match) {
		mRepository.update(match);
	}

	public void delete(Integer id) {
		mRepository.delete(id);
	}

	public Match findOne(Integer id) {
		return mRepository.findOne(id);
	}

	public List<Match> findAll() {
		return mRepository.findAll();
	}
}
