package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.advices;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApiError1 {
    public HttpStatus status;
    public String message;
    public List<String> subErrors;


}
