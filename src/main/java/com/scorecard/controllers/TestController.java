
package com.scorecard.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping("/addition")
	public Integer add(@RequestParam("num1") Integer number1, @RequestParam("num2") Integer number2) {
		return number1 + number2;
	}

	@GetMapping("/subtraction")
	public Integer sub(@RequestParam("num1") Integer number1, @RequestParam("num2") Integer number2) {
		return number1 - number2;
	}

	@GetMapping("/multiplication")
	public Integer mul(@RequestParam("num1") Integer number1, @RequestParam("num2") Integer number2) {
		return number1 * number2;
	}

}