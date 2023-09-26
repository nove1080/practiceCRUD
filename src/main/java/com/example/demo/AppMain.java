package com.example.demo;

import com.example.demo.repository.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class AppMain {

	public static void main(String[] args) {
		SpringApplication.run(AppMain.class, args);
	}

}
