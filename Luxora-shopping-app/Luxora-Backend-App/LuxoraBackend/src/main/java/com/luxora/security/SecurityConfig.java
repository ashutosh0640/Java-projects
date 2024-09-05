package com.luxora.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private EncodePassword encodePassword;
	
	@Bean
	public UserDetailsService userDetailsService() {
		return null;
		
	}
	
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(encodePassword.passwordEncoder());
		return authProvider;
	}
	
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authenticationProvider(authenticationProvider());
		http.authorizeHttpRequests(req -> req
				.requestMatchers("/user/**").hasAuthority("Admin")
				.anyRequest().authenticated())
		.formLogin(form -> form
				.loginPage("/login")
				.usernameParameter("email")
				.permitAll())
		.logout(logout -> logout.permitAll())
		.rememberMe(rem -> rem.key(null).tokenValiditySeconds(7 * 24 * 60 * 60));
		return http.build();
	}
	
	
	@Bean
	public WebSecurityCustomizer securityCustomizer() throws Exception{
		return (web) -> web.ignoring().requestMatchers("/css/**", "/js/**", "/images/**");
	}
	
}
