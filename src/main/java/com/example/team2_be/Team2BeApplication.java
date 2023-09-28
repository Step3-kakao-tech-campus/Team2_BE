package com.example.team2_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Team2BeApplication {

	public static void main(String[] args) {
		SpringApplication.run(Team2BeApplication.class, args);
	}

}
