package com.rohanrele.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.rohanrele.dao.UserRepository;
import com.rohanrele.entity.Role;

@Component
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// find user by username from db
		Optional<com.rohanrele.entity.User> userOptional = userRepository.findByUsername(username);

		// check if user exists
		if (userOptional.isPresent()) {
			// user from db
			com.rohanrele.entity.User user = userOptional.get();

			// populate granted authorities
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			GrantedAuthority authority = null;
			for (Role role : user.getRoles()) {
				authority = new SimpleGrantedAuthority(role.getName());
				authorities.add(authority);
			}

			// return user
			return User.withUsername(user.getUsername()).password(user.getPassword()).authorities(authorities).build();

		} else {// user doesnt exist
			throw new UsernameNotFoundException(username + " doesnt exist.");
		}
	}
}
