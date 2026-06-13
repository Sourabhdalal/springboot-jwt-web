package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class ApiError1 {
    public HttpStatus status;
    public String message;
    public List<String> subErrors;
}
