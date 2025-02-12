package com.example.spring_boot_crud_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.spring_boot_crud_api.repository")
public class SpringBootCrudApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCrudApiApplication.class, args);
	}

}
