package com.security.user_management.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.security.user_management.service.UserServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurity {

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserServiceImpl();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(userDetailsService());
		return provider;
	}

	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.cors(cors -> cors.disable())
	        .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(request -> {
	            request.requestMatchers(HttpMethod.POST, "/register").permitAll();
	            request.requestMatchers(HttpMethod.POST, "/test-post").permitAll();
	            request.requestMatchers(HttpMethod.GET, "/test-get").permitAll();
	            request.requestMatchers("/user/**").hasAnyRole("ADMIN", "USER");
	            request.requestMatchers("/admin/**").hasRole("ADMIN");
	        })
	        .formLogin(login -> login.loginPage("/login").permitAll())
	        .logout(logout -> logout.permitAll());

	    return http.build();
	}

}
