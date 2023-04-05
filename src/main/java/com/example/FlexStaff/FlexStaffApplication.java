package com.example.FlexStaff;

import com.example.FlexStaff.config.JwtService;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlexStaffApplication implements CommandLineRunner {
	@Autowired
	private JwtService jwtService;
	public static void main(String[] args)  {
		Dotenv dotenv = Dotenv.configure().load();
		SpringApplication.run(FlexStaffApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(jwtService.extractUsername("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJndHRAZ21haWwuY29tIn0.lloUqGOlAmiokU4kDoDEQif8TnxNA2OWims-Oh3Utcw"));
	}
}
