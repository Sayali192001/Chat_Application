package com.chat.chatapp.config;

import com.chat.chatapp.service.UserDetailsImpl;
import com.chat.chatapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
       http
               .csrf(csrf -> csrf.disable())
               .authorizeRequests(authz -> authz
                       .requestMatchers("/auth/register", "/auth/login").permitAll()
                       .anyRequest().authenticated()
               )
               .formLogin(form -> form
                       .loginPage("/auth/login")
                       .permitAll()
               )
               .logout(logout -> logout
                       .permitAll()
               );
        return http.build();
    }

    @Bean
    public UserDetailsImpl userDetailsService(){
        return (UserDetailsImpl) userService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
