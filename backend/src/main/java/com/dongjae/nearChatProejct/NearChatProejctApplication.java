package com.dongjae.nearChatProejct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NearChatProejctApplication {

	public static void main(String[] args) {
		SpringApplication.run(NearChatProejctApplication.class, args);
	}

}
