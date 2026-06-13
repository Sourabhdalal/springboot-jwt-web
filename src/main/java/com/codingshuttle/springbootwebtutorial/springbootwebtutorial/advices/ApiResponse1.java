package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.advices;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ApiResponse1<T> {


    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    private LocalDateTime timestamp;
    private T data;
    private ApiError1 error;

    public ApiResponse1()
    {
        this.timestamp = LocalDateTime.now();
    }

    public ApiResponse1(T data){
        this();
        this.data = data;
    }

    public ApiResponse1(ApiError1 error)
    {
        this();
        this.error = error;
    }

}
