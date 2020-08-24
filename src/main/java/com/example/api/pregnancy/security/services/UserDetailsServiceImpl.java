package com.example.api.pregnancy.security.services;

import com.example.api.pregnancy.models.User;
import com.example.api.pregnancy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
		User user = userRepository.findByPhoneNumber(phoneNumber)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + phoneNumber));

		return UserDetailsImpl.build(user);
	}

}
