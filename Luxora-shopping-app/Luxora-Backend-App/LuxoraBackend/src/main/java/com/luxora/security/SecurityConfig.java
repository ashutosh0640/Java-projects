package com.luxora.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(req -> req.anyRequest().permitAll());
		return http.build();
	}

}
