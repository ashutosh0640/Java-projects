package com.spring_basic_authentication.spring_basic_authentication.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				(requests) -> requests.requestMatchers("/public/**").permitAll()
				.requestMatchers("/private/read/**").hasAnyRole("USER", "ADMIN", "MONITOR")
				.requestMatchers("/private/write/**").hasAnyRole("USER", "ADMIN")
				.requestMatchers("/private/**").hasRole("ADMIN").anyRequest().authenticated())
				.httpBasic(basicAuthCustomizer -> {
					basicAuthCustomizer.realmName("Private Store");
					BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
					entryPoint.setRealmName("Private Store");
					basicAuthCustomizer.authenticationEntryPoint(entryPoint);
				});

//			.formLogin((form) -> form
//				.loginPage("/login")
//				.permitAll()
//			)
//			.logout((logout) -> logout.permitAll());

		return http.build();
	}

	@Bean
	protected UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(
				User.withUsername("admin").password(passwordEncoder().encode("admin123")).roles("ADMIN").build());
		manager.createUser(
				User.withUsername("user").password(passwordEncoder().encode("user123")).roles("USER").build());
		manager.createUser(
				User.withUsername("monitor").password(passwordEncoder().encode("monitor123")).roles("MONITOR").build());
		return manager;
	}

	
	
// depricated
//	@Bean
//	public UserDetailsService userDetailsService() {
//		// Define users with roles
//		UserDetails user = User.withDefaultPasswordEncoder().username("user").password("user123").roles("USER").build();
//
//		UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("admin123").roles("ADMIN")
//				.build();
//
//		UserDetails manager = User.withDefaultPasswordEncoder().username("manager").password("manager123")
//				.roles("MANAGER").build();
//
//		// Use InMemoryUserDetailsManager to handle user details
//		return new InMemoryUserDetailsManager(user, admin, manager);
//	}

	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
