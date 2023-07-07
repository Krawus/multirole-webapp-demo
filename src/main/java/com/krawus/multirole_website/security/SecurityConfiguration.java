package com.krawus.multirole_website.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private DataSource dataSource;

    @Autowired
    public SecurityConfiguration(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication()
        .dataSource(dataSource);
}

    @Bean
    public RoleHierarchyImpl roleHierarchy(){
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_MOD \n" 
        + "ROLE_ADMIN > ROLE_USER\n" + "ROLE_MOD > ROLE_USER");
        return roleHierarchy;
    }

    @Bean 
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
        .authorizeHttpRequests(authorize -> 
            authorize
                .requestMatchers("/management/").hasAnyRole("ADMIN", "MOD")
                .requestMatchers("/management/admin/**").hasRole("ADMIN")
                .requestMatchers("/management/mod/**").hasRole("MOD")
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
        .exceptionHandling(configurer -> 
                                    configurer.accessDeniedPage("/access-denied"))

        .httpBasic(Customizer.withDefaults())  // for testing without frontend
        .csrf(csrf -> csrf.disable()) // for testing without frontend
        ;
        

        return http.build();
    }
}
