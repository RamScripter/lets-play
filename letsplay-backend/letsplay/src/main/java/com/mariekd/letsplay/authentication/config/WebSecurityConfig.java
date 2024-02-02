package com.mariekd.letsplay.authentication.config;

import com.mariekd.letsplay.authentication.services.UserService;
import com.mariekd.letsplay.authentication.config.PasswordEncoderConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {


    private final UserService userService;
    private final PasswordEncoderConfig passwordEncoderConfig;

    /*
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http.
                 authorizeRequests(auth ->
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
     */


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService); // set the custom user details service
        provider.setPasswordEncoder(passwordEncoderConfig.passwordEncoder()); // encodes password
        return provider;
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider);;
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/users/register").permitAll() // à modifier pour que seule la création soit accessible
                        .requestMatchers("/api/users/login").permitAll() // à modifier pour que seule la création soit accessible
                        .anyRequest().authenticated()

                )
                .csrf(AbstractHttpConfigurer::disable);
                //.oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));
        return http.build();
    }



   /*
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers()
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    */



}
