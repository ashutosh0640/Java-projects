package com.spring_security.InMemory.security;

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

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	protected UserDetailsService userDetailSEervice() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(
				User.withUsername("admin").password(passwordEncoder().encode("admin#1234")).roles("ADMIN").build());

		return manager;

	}
	
	@Bean
    protected UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user1 = User
                .withUsername("ashu")
                .password(passwordEncoder.encode("ashu#1234"))
                .roles("USER")
                .build();
        UserDetails user2 = User
                .withUsername("hughes")
                .password(passwordEncoder.encode("hughes#1234"))
                .roles("USER")
                .build();      
         
        return new InMemoryUserDetailsManager(user1, user2);
    }
	

	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.cors(req->req.disable())
		.csrf(req->req.disable())
		.authorizeHttpRequests(request -> request
				.requestMatchers("/login", "/register").permitAll()
				.anyRequest().authenticated())
		.formLogin(form -> form
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.usernameParameter("username")
				.passwordParameter("password")
				.defaultSuccessUrl("/api/student")
				.permitAll()
				.disable())
		.httpBasic(req -> req.disable());
		
		return http.build();

	}
	
	
	
	
//	@Bean("customAuthenticationManager")
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//        return authenticationManagerBean();
//    }
//	
//	@Bean
//	public static DelegatingApplicationListener delegatingApplicationListener() {
//		return new DelegatingApplicationListener();
//	}

}
