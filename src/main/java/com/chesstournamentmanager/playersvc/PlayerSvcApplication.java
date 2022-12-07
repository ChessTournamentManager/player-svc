package com.chesstournamentmanager.playersvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PlayerSvcApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PlayerSvcApplication.class, args);
	}

}
