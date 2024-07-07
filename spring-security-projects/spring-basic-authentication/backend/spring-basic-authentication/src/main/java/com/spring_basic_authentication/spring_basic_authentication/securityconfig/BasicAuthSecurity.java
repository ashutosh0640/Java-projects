package com.spring_basic_authentication.spring_basic_authentication.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class BasicAuthSecurity {
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		return http
				.authorizeHttpRequests(auth -> auth
				//.requestMatchers("/api/names").permitAll()
				.requestMatchers("/api/products").permitAll()
				.requestMatchers("/api/orders").permitAll()
				.requestMatchers("/register").permitAll()
				.requestMatchers("/login").permitAll()
				.anyRequest().authenticated()
				)
				.formLogin(form -> form
						.defaultSuccessUrl("http://127.0.0.1:5500/src/home.html", true)
				)
				.logout(config -> config
						.logoutSuccessUrl("/")
				)
				.build();
	}
	
}
