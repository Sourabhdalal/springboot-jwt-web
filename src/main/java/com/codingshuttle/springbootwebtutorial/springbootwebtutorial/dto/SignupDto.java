package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SignupDto {

    private String email;
    private String password;
    private String name;
    private Set<Role> roles;

}
