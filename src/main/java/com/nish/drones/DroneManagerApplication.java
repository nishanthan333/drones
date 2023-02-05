package com.nish.drones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DroneManagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(DroneManagerApplication.class, args);
	}
}
