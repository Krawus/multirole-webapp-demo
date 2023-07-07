package com.krawus.multirole_website.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public UserDetailsService usersSetup() {
        UserDetails user = User.builder()
            .username("user")
            .password("{noop}user")
            .roles("USER")
            .build();

        UserDetails moderator = User.builder()
            .username("moderator")
            .password("{noop}moderator")
            .roles("USER", "MOD")
            .build();

        UserDetails admin = User.builder()
            .username("admin")
            .password("{noop}admin")
            .roles("USER", "MOD", "ADMIN")
            .build();

        return new InMemoryUserDetailsManager(user, moderator, admin);
    }


    @Bean 
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
        .authorizeHttpRequests(authorize -> 
            authorize
                .anyRequest().authenticated()
        )
        .formLogin(form -> 
            form
                .loginPage("/login")
                .loginProcessingUrl("/authenticateUser")
                .permitAll()
        )
        .logout(logout ->
                    logout.permitAll()
        )
        .httpBasic(Customizer.withDefaults())  // for testing without frontend
        .csrf(csrf -> csrf.disable()) // for testing without frontend
        ;
        

        return http.build();
    }
}
