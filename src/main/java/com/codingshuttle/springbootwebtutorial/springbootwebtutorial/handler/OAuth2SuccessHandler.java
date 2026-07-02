package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.handler;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.User;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.services.CustomUserDetailsService;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.services.JwtService;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

//    private final CustomUserDetailsService userDetailsService;
//    private final UserService userService;
//    private final JwtService jwtService;
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
//            throws IOException, ServletException
//    {
//
//        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
//        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) token.getPrincipal();
//
//        String email = oAuth2User.getAttribute("email");
//
//        User user = userDetailsService.getUserByEmail(email);
//
//        if(user == null)
//        {
//            User newUser = User.builder().email(email).name(oAuth2User.getAttribute("name")).build();
//            user = userService.saveUser(newUser);
//        }
//
//        String accessToken = jwtService.generateAccessToken(user);
//        String refreshToken = jwtService.generateRefreshToken(user);
//
//        Cookie cookie = new Cookie("refreshToken" , refreshToken);
//        cookie.setHttpOnly(true);
//        response.addCookie(cookie);
//
//        String frontendURL = "http://localhost:8080/home.html?token="+accessToken;
//
//        getRedirectStrategy().sendRedirect(request, response, frontendURL);
//    }
}
