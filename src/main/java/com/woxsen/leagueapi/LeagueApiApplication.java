package com.woxsen.leagueapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.woxsen.leagueapi")
public class LeagueApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeagueApiApplication.class, args);
	}

}
