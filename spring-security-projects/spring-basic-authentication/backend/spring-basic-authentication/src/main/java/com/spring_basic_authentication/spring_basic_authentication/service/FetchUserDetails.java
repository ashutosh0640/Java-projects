package com.spring_basic_authentication.spring_basic_authentication.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class FetchUserDetails {
	
	public Map<String, List<GrantedAuthority>> userDetail() {
		Map<String, List<GrantedAuthority>> details = new HashMap<>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {
			String username = authentication.getName();
			Object principal = authentication.getPrincipal();
			
			if (principal instanceof UserDetails ) {
				UserDetails userDetails = (UserDetails) principal;
				Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
				List<GrantedAuthority> roles = new ArrayList<>();
				for (GrantedAuthority authority : authorities) {
					roles.add(authority);	
				}
				details.put(username, roles);
			}
			
		}
		
		return details;
		
	}

}
