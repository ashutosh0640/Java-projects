package com.shop.luxora.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
	
	private final UserDetailsService userDetailsService;
	
	public WebSecurityConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Bean
 	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
 		http
 			.csrf(csrf-> csrf.disable())
 			.authorizeHttpRequests((authorizeHttpRequests) ->
 				authorizeHttpRequests
 					.requestMatchers("/register").permitAll()
 					.requestMatchers("/user/**").authenticated()
 			)
 			.httpBasic(Customizer.withDefaults());
 		return http.build();
 	}

// 	@Bean
// 	public UserDetailsService userDetailsService() {
// 		UserDetails user = User.withUsername("user")
// 			.password(passwordEncoder().encode("password"))
// 			.roles("USER")
// 			.build();
// 		
// 		UserDetails admin = User.withUsername("admin")
// 			.password("{noop}password")
// 			.roles("ADMIN", "USER")
// 			.build();
// 		
// 		return new InMemoryUserDetailsManager(user, admin);
// 	}
 	
 	@Bean
 	public PasswordEncoder passwordEncoder() {
 		return new BCryptPasswordEncoder(14);
 	}
 	
 	
 	@Bean
 	public AuthenticationProvider authenticationProvider() {
 		DaoAuthenticationProvider daoAuthProvider = new DaoAuthenticationProvider();
 		daoAuthProvider.setUserDetailsService(userDetailsService);
 		daoAuthProvider.setPasswordEncoder(passwordEncoder());
 		return daoAuthProvider;
 	}
}
