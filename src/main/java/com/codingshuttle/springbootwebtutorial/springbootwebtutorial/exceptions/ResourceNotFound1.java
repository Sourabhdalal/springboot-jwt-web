package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.exceptions;

import jakarta.annotation.Resource;

public class ResourceNotFound1 extends RuntimeException{

    public ResourceNotFound1(String message) {
        super(message);
    }
}
