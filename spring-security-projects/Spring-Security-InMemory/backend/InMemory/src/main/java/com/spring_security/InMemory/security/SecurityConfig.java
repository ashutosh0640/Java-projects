package com.spring_security.InMemory.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	protected UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(
				User.withUsername("admin").password(passwordEncoder().encode("admin#1234")).roles("ADMIN").build());
				User.withUsername("user").password(passwordEncoder().encode("user#1234")).roles("USER").build();
		return manager;

	}

	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    // Disabling CORS and CSRF for simplicity
	    http
	        .csrf(req -> req.disable())
	        
	        // Authorizing HTTP requests
	        .authorizeHttpRequests(request -> request
	            .requestMatchers("/login", "/logout").permitAll()  // Allowing access to /login and /register without authentication
	            .anyRequest().authenticated()  // All other requests require authentication
	        )
	        
	        // Configuring form login
	        .formLogin(form -> form
	        		.loginPage("http://localhost:8081/login") // The custom login page
	        		.permitAll()
	        )
	        
	        // Configuring logout
	        .logout(logout -> logout
	        		//.logoutUrl("/logout") // The URL to trigger logout
	                //.logoutSuccessUrl("http://localhost:8081/login?logout=true") // The URL to redirect to after successful logout
	                //.invalidateHttpSession(true)
	                //.deleteCookies("JSESSIONID")
	                .permitAll()
	        )
	        
	        // Disabling HTTP Basic authentication
	        .httpBasic(req -> req.disable());

	    return http.build();
	}


	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configure) throws Exception {
		return configure.getAuthenticationManager();
	}
	
	

}
