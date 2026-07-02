package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.services;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.User;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.exceptions.ResourceNotFound1;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));
    }

//    public User getUserByEmail(String email)
//    {
//        return userRepository.findByEmail(email)
//                .orElseThrow(() ->
//                        new UsernameNotFoundException("User not found"));
//    }

    public User getUserId(Long userId)
    {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFound1("User not found with Id " + userId));
    }

}
