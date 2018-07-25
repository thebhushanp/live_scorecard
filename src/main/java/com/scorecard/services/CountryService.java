package com.scorecard.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scorecard.models.Country;
import com.scorecard.repositories.CountryRepository;

@Service
public class CountryService {

	@Autowired
	CountryRepository cRepository;

	public void save(Country country) {
		cRepository.save(country);
	}
}
