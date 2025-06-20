package com.projetospring.chatonline.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projetospring.chatonline.model.User;
import com.projetospring.chatonline.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return createUserDetails(repository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username don't exist")));
	}

	private UserDetails createUserDetails(User user) {
		return new UserDetailsImpl(user);
	}

}
