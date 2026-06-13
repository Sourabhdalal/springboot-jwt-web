package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.services;


import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.LoginDto;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.SignupDto;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.UserDto;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.User;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserDto singUpUser(SignupDto signUpDto) {

        Optional<User> user = userRepo.findByEmail(signUpDto.getEmail());

        if(user.isPresent())
        {
            throw new BadCredentialsException("User with email already exits!");
        }

        User tocreated =  modelMapper.map(signUpDto, User.class);
        tocreated.setPassword(passwordEncoder.encode(tocreated.getPassword()));
        User savedUser = userRepo.save(tocreated);
        return modelMapper.map(savedUser, UserDto.class);
    }

    public String login(LoginDto loginDto) {
     Authentication authentication = authenticationManager.authenticate(
             new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
     );

     User user = (User) authentication.getPrincipal();
     String token = jwtService.generateToken(user);
     return  token;
    }
}
