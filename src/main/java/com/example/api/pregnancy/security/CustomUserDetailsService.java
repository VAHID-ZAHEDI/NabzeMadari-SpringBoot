package com.example.api.pregnancy.security;

import com.example.api.pregnancy.models.User;
import com.example.api.pregnancy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> dbUser = this.userRepository.findAllByPhoneNumber(username);

		if (dbUser != null) {
			Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
			
//			for (String role : dbUser.get().getRoles()) {
//				GrantedAuthority authority = new SimpleGrantedAuthority(role);
//				grantedAuthorities.add(authority);
//			}
					
			org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(
					dbUser.get().getUsername(), dbUser.get().getPassword(), grantedAuthorities);
			return user;
		} else {
			throw new UsernameNotFoundException(String.format("User '%s' not found", username));
		}
	}

}
