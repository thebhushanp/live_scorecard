package com.scorecard.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scorecard.models.Country;
import com.scorecard.repositories.CountryRepository;

@Service
public class CountryService {

	@Autowired
	CountryRepository cRepository;

	public Country save(Country country) {
		return cRepository.save(country);
	}

	public void update(Country country) {
		cRepository.update(country);
	}

	public void delete(Integer id) {
		cRepository.delete(id);
	}

	public Country findOne(Integer id) {
		return cRepository.findOne(id);
	}

	public List<Country> findAll() {
		return cRepository.findAll();
	}
}
