package com.example.demoadhar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demoadhar.entity.User;

@SpringBootApplication
public class AdhardemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdhardemoApplication.class, args);
		System.out.println("Working");
	}

}
