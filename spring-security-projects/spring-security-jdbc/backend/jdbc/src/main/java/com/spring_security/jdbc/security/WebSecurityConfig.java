package com.spring_security.jdbc.security;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
		prePostEnabled = true,
		securedEnabled = true,
		jsr250Enabled = true)
public class WebSecurityConfig {
	
	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	    http.csrf(csrf -> csrf.disable())
	        .cors(cors -> cors.disable())
	        .authorizeHttpRequests(req -> req
	            .requestMatchers("/test", "/login", "/register").permitAll()
	            .requestMatchers("/admin/**").hasRole("ADMIN")     
	            .anyRequest().authenticated()                     
	        )
	        .formLogin(withDefaults())
	        .httpBasic(withDefaults());
	        //.formLogin(form->form.loginPage("/login"))
	        //.logout(logout -> logout.permitAll());

	    return http.build();
	}
}
