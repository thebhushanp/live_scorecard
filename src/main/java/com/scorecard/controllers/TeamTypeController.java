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

import com.scorecard.models.TeamType;
import com.scorecard.services.TeamTypeService;

@RestController
public class TeamTypeController {

	@Autowired
	TeamTypeService teamtypeService;

	@GetMapping("/teamtype/{id}")
	public TeamType findTeamType(@PathVariable("id") Integer id) {
		return teamtypeService.findOne(id);
	}

	@GetMapping("/teamtypes")
	public List<TeamType> findAll() {
		return teamtypeService.findAll();
	}

	//to create
	@PostMapping("/teamtype")
	public TeamType add(@RequestBody TeamType teamtype) {
		teamtypeService.save(teamtype);
		return teamtype;
	}

	//to update
	@PutMapping("/teamtype")
	public void update(@RequestBody TeamType teamtype) {
		teamtypeService.update(teamtype);
	}

	//to delete
	@DeleteMapping("/teamtype/{id}")
	public void delete(@PathVariable("id") Integer id) {
		teamtypeService.delete(id);
	}

}
