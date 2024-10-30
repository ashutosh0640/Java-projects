package com.securityJWT.user_management2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurity {
	
	
	@Bean
	protected SecurityFilterChain securityChain(HttpSecurity http) throws Exception {
		http.cors(cors->cors.disable());
		http.csrf(csrf->csrf.disable());
		http.authorizeHttpRequests(req -> {
			req.requestMatchers("user/view/**").permitAll();
			req.requestMatchers("/user/**").hasAnyRole("ADMIN", "USER");
			req.requestMatchers("role/**").hasAnyRole("ADMIN", "USER");
			req.requestMatchers("/admin/**").hasRole("ADMIN");
			req.requestMatchers("/test/**").permitAll();
			req.anyRequest().authenticated();
		});
		http.httpBasic(withDefaults());
		//http.formLogin(login->login.loginPage("/login").permitAll());
		//http.logout(logout->logout.permitAll());
		
		return http.build();
		
	}

}
