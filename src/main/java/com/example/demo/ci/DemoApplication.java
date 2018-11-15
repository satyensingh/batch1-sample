package com.example.demo.ci;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		System.out.println("Small Change.....");
		SpringApplication.run(DemoApplication.class, args);
	}
}
