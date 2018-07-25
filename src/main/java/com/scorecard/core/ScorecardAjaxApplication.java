package com.scorecard.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.scorecard")
public class ScorecardAjaxApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScorecardAjaxApplication.class, args);
	}
}
