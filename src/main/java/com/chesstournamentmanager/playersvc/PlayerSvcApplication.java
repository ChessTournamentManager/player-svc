package com.chesstournamentmanager.playersvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PlayerSvcApplication {

	@GetMapping("/message")
	public String getMessage() {
		return "Welcome to the player service.";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(PlayerSvcApplication.class, args);
	}

}
