package com.codingshuttle.springbootwebtutorial.springbootwebtutorial;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.User;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringBootWebTutorialApplicationTests {

	@Autowired
	private UserRepository userRepo;

	@Test
	void contextLoads() {

		List<User> u = userRepo.findAll();

		System.out.println(u);
	}


	@Test
	void createUser()
	{
		User u = new User(4L,"Alok@gmail.com", "feffbef", "Alok");
		User user = userRepo.save(u);
		System.out.println("VDhvf " +  user);
	}


}
