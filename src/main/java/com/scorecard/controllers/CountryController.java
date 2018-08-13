package com.scorecard.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scorecard.models.Country;
import com.scorecard.services.CountryService;

@RestController
public class CountryController {

	@Autowired
	CountryService countryService;

	@GetMapping("/api/country/{id}")
	public Country findCountry(@PathVariable("id") Integer id) {
		return countryService.findOne(id);
	}

	@GetMapping("/api/countries")
	public List<Country> findAll() {
		return countryService.findAll();
	}

	//to create
	@PostMapping("/api/country")
	public Country add(@RequestBody Country country) {
		countryService.save(country);
		return country;
	}

	//to update
	@PutMapping("/api/country")
	public void update(@RequestBody Country country) {
		countryService.update(country);
	}

	//to delete
	@DeleteMapping("/api/country/{id}")
	public void delete(@PathVariable("id") Integer id) {
		countryService.delete(id);
	}

}
