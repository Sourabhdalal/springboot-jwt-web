package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.services;


import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.LoginDto;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.LoginResponseDto;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomUserDetailsService userService;

    public LoginResponseDto login(LoginDto loginDto) {
        Authentication authentication = authenticationManager
                .authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );

        User user = (User) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        return  new LoginResponseDto(user.getId(), accessToken, refreshToken);
    }

    public LoginResponseDto refreshToken(String refreshToken) {

        Long userId = jwtService.getUserIdByToken(refreshToken);
        User user = userService.getUserId(userId);

        String accessToken = jwtService.generateAccessToken(user);
        return new LoginResponseDto(userId, accessToken, refreshToken);
    }
}

