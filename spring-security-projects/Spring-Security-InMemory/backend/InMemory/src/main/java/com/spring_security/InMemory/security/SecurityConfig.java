package com.spring_security.InMemory.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
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
public class SecurityConfig {

	@Bean
	protected UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(
				User.withUsername("admin").password(passwordEncoder().encode("admin#1234")).roles("ADMIN").build());

		return manager;

	}

	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    // Disabling CORS and CSRF for simplicity
	    http.cors(req -> req.disable())
	        .csrf(req -> req.disable())
	        
	        // Authorizing HTTP requests
	        .authorizeHttpRequests(request -> request
	            .requestMatchers("/login", "/register").permitAll()  // Allowing access to /login and /register without authentication
	            .anyRequest().authenticated()  // All other requests require authentication
	        )
	        
	        // Configuring form login
	        .formLogin(form -> form
	            .loginPage("http://127.0.0.1:5500/inmemory/login.html")  // Custom login page URL
	            .loginProcessingUrl("/login")  // URL to submit the login credentials
	            .usernameParameter("username")  // Parameter name for the username in the login form
	            .passwordParameter("password")  // Parameter name for the password in the login form
	            .defaultSuccessUrl("http://127.0.0.1:5500/inmemory/home.html")  // Default URL after successful login
	            .permitAll()  // Allow everyone to see the login page
	        )
	        
	        // Configuring logout
	        .logout(logout -> logout
	            .logoutUrl("/logout")  // URL to trigger the logout process
	            .logoutSuccessUrl("http://127.0.0.1:5500/inmemory/login.html?logout=true")  // URL to redirect to after successful logout
	            .logoutSuccessHandler((request, response, authentication) -> {
	                // Custom logout success handler
	                response.sendRedirect("http://127.0.0.1:5500/inmemory/login.html?logout=true");
	            })
	            .invalidateHttpSession(true)  // Invalidate the session after logout
	            .deleteCookies("JSESSIONID")  // Delete the session cookie
	            .clearAuthentication(true)  // Clear the authentication information
	            .permitAll()  // Allow everyone to access the logout URL
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
