package com.codingshuttle.springbootwebtutorial.springbootwebtutorial;


import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.User;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtTest {

    @Autowired
    private JwtService jwtService;

//    @Test
//    void contextLoads()
//    {
//        User user = new User(4L, "Amit@gmail.com", "1234");
//
//        String token = jwtService.generateToken(user);
//
//        System.out.println(token);
//
//        Long id = jwtService.getUserIdByToken(token);
//
//        System.out.println(id);
//    }
}
