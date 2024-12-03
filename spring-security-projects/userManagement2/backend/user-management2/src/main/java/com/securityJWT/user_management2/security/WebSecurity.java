package com.securityJWT.user_management2.security;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.securityJWT.user_management2.service.MyUserDetailsService;

//import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurity {
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	
	@Bean
	protected SecurityFilterChain securityChain(HttpSecurity http) throws Exception {
		http.cors(cors->cors.disable());
		http.csrf(csrf->csrf.disable());
		http.authorizeHttpRequests(req -> {
			//req.requestMatchers("user/view/**").permitAll();
			//req.requestMatchers("/user/**").hasAnyRole("ADMIN", "USER");
			//req.requestMatchers("role/**").hasAnyRole("ADMIN", "USER");
			//req.requestMatchers("/admin/**").hasRole("ADMIN");
			//req.requestMatchers("/test/**").permitAll();
			req.anyRequest().authenticated();
		});
		http.httpBasic(Customizer.withDefaults());
		//http.formLogin(login->login.loginPage("/login").permitAll());
		//http.logout(logout->logout.permitAll());
		return http.build();
	}
	

	
//	@Bean 
//	protected UserDetailsService userDetailsService() { 
//	    UserDetails user1 = User 
//	            .withUsername("admin") 
//	            .password(passwordEncoder().encode("admin123")) 
//	            //.roles("ADMIN") // Assign a role to the user
//	            .build();
//
//	    return new InMemoryUserDetailsManager(user1); 
//	}

	
	@Bean
	protected UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(
				User.withUsername("aman").password(passwordEncoder().encode("aman123")).build());
		return manager;
	}
	
	protected AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(myUserDetailsService);
		
		return provider;
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
