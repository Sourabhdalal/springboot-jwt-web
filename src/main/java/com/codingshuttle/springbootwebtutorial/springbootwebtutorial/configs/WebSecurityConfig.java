package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.configs;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity.csrf(AbstractHttpConfigurer::disable).
                authorizeHttpRequests(auth -> auth
                        .requestMatchers("/employees", "/auth/**").permitAll()
                        .requestMatchers("/employees/**").hasRole("ADMIN")
                        .anyRequest().authenticated());
              //  .formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }


    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

//    @Bean
//    UserDetailsService createUsersForAuthority()
//    {
//        UserDetails normalUser = User.withUsername("Aman").password(passwordEncode().encode("Aman@123")).roles("USER").build();
//
//        UserDetails adminUser = User.withUsername("Amisha").password(passwordEncode().encode("Amisha@123")).roles("ADMIN").build();
//
//        return new InMemoryUserDetailsManager(normalUser,adminUser);
//
//    }



    @Bean
    PasswordEncoder passwordEncode()
    {
        return new BCryptPasswordEncoder();
    }
}
