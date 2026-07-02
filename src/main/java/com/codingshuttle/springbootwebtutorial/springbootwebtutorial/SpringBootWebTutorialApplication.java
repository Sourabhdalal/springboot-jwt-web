package com.codingshuttle.springbootwebtutorial.springbootwebtutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootWebTutorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebTutorialApplication.class, args);
	}

}
