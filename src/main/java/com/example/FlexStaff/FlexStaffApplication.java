package com.example.FlexStaff;


import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlexStaffApplication implements CommandLineRunner {

	public static void main(String[] args)  {

		SpringApplication.run(FlexStaffApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
