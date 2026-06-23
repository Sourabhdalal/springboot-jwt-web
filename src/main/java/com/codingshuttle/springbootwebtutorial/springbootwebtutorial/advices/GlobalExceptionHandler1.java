package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.advices;


import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.exceptions.ResourceNotFound1;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler1 {

    @ExceptionHandler(ResourceNotFound1.class)
    public ResponseEntity<ApiResponse1<?>> handleResourceNotFoundException(ResourceNotFound1 exception){
        ApiError1 apiError = ApiError1.builder().status(HttpStatus.NOT_FOUND).message(exception.getMessage()).build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse1<?>> handleAllException(Exception ex)
    {
        ApiError1 apiError = ApiError1.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message(ex.getMessage()).build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse1<?>> handleMethodArgumentNotFoundException(MethodArgumentNotValidException ex){
       List<String> errors = ex.getBindingResult().getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList());
       ApiError1 apiError = ApiError1.builder().status(HttpStatus.BAD_REQUEST).message("Invalidate inputs").subErrors(errors).build();
       return buildErrorResponseEntity(apiError);
    }

    public ResponseEntity<ApiResponse1<?>> buildErrorResponseEntity(ApiError1 apiError)
    {
        return new ResponseEntity<>(new ApiResponse1<>(apiError), apiError.getStatus());
    }


    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError1> authenticationException(AuthenticationException ex)
    {
        ApiError1 apiError1 = new ApiError1();
        apiError1.setMessage(ex.getLocalizedMessage());
        apiError1.setStatus(HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(apiError1, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiError1> jwtAuthenticationException(JwtException ex)
    {
        ApiError1 apiError1 = new ApiError1();
        apiError1.setMessage(ex.getLocalizedMessage());
        apiError1.setStatus(HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(apiError1, HttpStatus.UNAUTHORIZED);
    }
}
