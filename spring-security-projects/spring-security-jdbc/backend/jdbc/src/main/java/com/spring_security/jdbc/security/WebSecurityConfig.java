package com.spring_security.jdbc.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	
	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf((csrf)->csrf.disable())
		.cors((cors)->cors.disable())
		.authorizeHttpRequests(req->req.requestMatchers("/public/**").permitAll()
				.requestMatchers("/private/**").hasRole("ADMIN"))
		.formLogin((form)->form.loginPage("/login").permitAll())
		.logout((logout)->logout.permitAll());
		
		return http.build();
		
	}
	
	@Bean
	protected UserDetailsService userDetailsService(DataSource dataSource) {
		JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
		
		// Override the default queries
	    manager.setUsersByUsernameQuery("SELECT username, password, enable FROM users WHERE username = ?");
	    manager.setAuthoritiesByUsernameQuery("SELECT u.username, a.authority FROM authorities a, users u WHERE u.user_id = a.username AND u.username = ?");

		if(!manager.userExists("admin")) {
			manager.createUser(
					User.withUsername("admin")
					.password("{noop}admin#123")
					.disabled(false)
					.accountLocked(false)
					.roles("ADMIN").build()
					);
		}
		return manager;
		
	}

}
