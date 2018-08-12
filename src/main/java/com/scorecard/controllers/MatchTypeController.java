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

import com.scorecard.models.MatchType;
import com.scorecard.services.MatchTypeService;

@RestController
public class MatchTypeController {

	@Autowired
	MatchTypeService matchtypeService;

	@GetMapping("/matchtype/{id}")
	public MatchType findMatchType(@PathVariable("id") Integer id) {
		return matchtypeService.findOne(id);
	}

	@GetMapping("/matchtypes")
	public List<MatchType> findAll() {
		return matchtypeService.findAll();
	}

	//to create
	@PostMapping("/matchtype")
	public MatchType add(@RequestBody MatchType matchtype) {
		matchtypeService.save(matchtype);
		return matchtype;
	}

	//to update
	@PutMapping("/matchtype")
	public void update(@RequestBody MatchType matchtype) {
		matchtypeService.update(matchtype);
	}

	//to delete
	@DeleteMapping("/matchtype/{id}")
	public void delete(@PathVariable("id") Integer id) {
		matchtypeService.delete(id);
	}

}
