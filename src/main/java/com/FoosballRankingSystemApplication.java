package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Start up the application
 * @author aditiphadke
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = "com")
public class FoosballRankingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoosballRankingSystemApplication.class, args);
	}
}
