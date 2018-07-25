package com.scorecard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scorecard.models.Country;
import com.scorecard.services.CountryService;

@RestController
public class CountryController {

	@Autowired
	CountryService countryService;

	@PostMapping("/country")
	public Country add(@RequestParam("code") String code, @RequestParam("name") String name) {
		Country country = new Country();
		country.setCode(code);
		country.setName(name);
		countryService.save(country);
		return country;
	}

}
