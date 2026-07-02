package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class LoggingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //1. Log incoming request
        System.out.println("Incoming Request:");
        System.out.println("Method: " + request.getMethod());
        System.out.println("URI: "+ request.getRequestURI());

        //2. Continue to the next filter/controller
        filterChain.doFilter(request,response);

        //3. Log outgoing response
        System.out.println("Outgoing response: ");
        System.out.println("Status: "+ response.getStatus());
    }
}
