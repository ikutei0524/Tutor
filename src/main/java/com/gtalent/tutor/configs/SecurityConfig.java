package com.gtalent.tutor.configs;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//向spring boot 標示該類別為設定黨,spring將會在啟動時讀取
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private JwtAuthFilter jwtAuthFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(authz -> authz

                //method1
                        .requestMatchers("/jwt/**").permitAll()
                        .requestMatchers("/session/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/products/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/v2/users/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/v2/users/**").hasRole("ADMIN")
                        .anyRequest().authenticated()

                //method2
                /*.requestMatchers(HttpMethod.POST, "/products/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/products/**").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/products/**").permitAll()

                .requestMatchers(HttpMethod.POST, "/v2/users/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/v2/users/**").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/v2/users/**").permitAll()

                .anyRequest().authenticated()*/


        )
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);//stateless jwt用不上csrf
//restful 核心

        return httpSecurity.build();
    }
}
