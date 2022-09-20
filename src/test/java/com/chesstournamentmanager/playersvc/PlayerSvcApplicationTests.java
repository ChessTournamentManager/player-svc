package com.chesstournamentmanager.playersvc;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PlayerSvcApplicationTests {

	@Autowired
	private PlayerSvcApplication playerSvcApplication;

	@Test
	void contextLoads() {
	}

	@Test
	void sendsWelcomeMessage() {
		assertThat(playerSvcApplication.getMessage()).isEqualTo("Welcome to the player service.");
	}
}
