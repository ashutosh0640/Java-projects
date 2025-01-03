package com.shop.luxora.serviceImp;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.shop.luxora.entity.CustomUserDetails;
import com.shop.luxora.entity.User;
import com.shop.luxora.repository.UserRepository;


@Component
public class customUserDetailsService implements UserDetailsService{

	private final UserRepository userRepo;
	
	public customUserDetailsService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User getUser = userRepo.findUserByEmail(email);
		
		if(getUser == null) {
			throw new UsernameNotFoundException("User Not found.");
		}
		int len = getUser.getRoles().size();
		return new CustomUserDetails(getUser);
	}

}
