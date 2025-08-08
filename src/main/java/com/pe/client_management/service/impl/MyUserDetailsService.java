package com.pe.client_management.service.impl;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {


	@Value("${spring.security.user.name}")
	private String configuredUsername;

	@Value("${spring.security.user.password}")
	private String configuredPassword;

	@Value("${spring.security.user.roles}")
	private String[] configuredRoles;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (!configuredUsername.equals(username)) {
			throw new UsernameNotFoundException("Usuario no encontrado: " + username);
		}

		return User.builder()
			.username(configuredUsername)
			.password("$2a$10$XOL9NkPQs8o7GPLRgWjwQ..KcoU7ugzT9bzHimUKNUIRElI47IK9e")
			.roles(configuredRoles)
			.build();
	}
}