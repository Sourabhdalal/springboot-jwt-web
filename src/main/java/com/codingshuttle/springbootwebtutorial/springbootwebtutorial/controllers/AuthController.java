package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.controllers;


import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.advices.ApiResponse1;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.LoginDto;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.LoginResponseDto;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.SignupDto;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.UserDto;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.services.AuthService;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignupDto signUpDto)
    {
      return new ResponseEntity<>(userService.singUpUser(signUpDto), HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> logIn(@RequestBody LoginDto loginDto, HttpServletResponse response)
    {
        LoginResponseDto loginResponseDto = authService.login(loginDto);

        Cookie c = new Cookie("refreshToken" , loginResponseDto.getRefreshToken() );
        c.setHttpOnly(true);

        response.addCookie(c);
        return ResponseEntity.ok(loginResponseDto);

    }


    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refreshToken(HttpServletRequest request)
    {
        String refreshToken = Arrays.stream(request.getCookies()).
                filter(cookie -> "refreshToken".equals(cookie.getName())).
                findFirst().
                map(Cookie::getValue).
                orElseThrow(() -> new AuthenticationServiceException("Refresh token was not found with this cookie"));

        LoginResponseDto loginResponseDto = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(loginResponseDto);
    }
}
