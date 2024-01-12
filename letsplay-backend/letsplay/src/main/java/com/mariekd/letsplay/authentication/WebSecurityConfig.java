package com.mariekd.letsplay.authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http.authorizeRequests(auth ->
                        auth
                                .requestMatchers("/api/users").permitAll()
                                .requestMatchers("/api/musician_types").permitAll()
                                .requestMatchers("/api/roles").permitAll()
                                .requestMatchers("/admin").hasRole("ADMIN"))

                                .csrf(AbstractHttpConfigurer::disable);

                // toutes les autres routes sont soumises à autorisation :
                // .anyRequest().authenticated()


         return http.build();
    }
}
