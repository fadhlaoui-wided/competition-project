package com.competition.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.competition.entities.Users;
import com.competition.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	@Lazy
	private PasswordEncoder passwordEncoder;

	@Override
	public Users loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Users> users = userRepository.findByEmail(username);
		if (users.isEmpty()) {
			return null;
		} else {
			return users.get(0);
		}

	}


	public String register(Users user) throws Exception {
		List<Users> users = userRepository.findByEmail(user.getEmail());
		if (!users.isEmpty()) {
			return ("Utilisateur exist déja");
		}
		String passwordEncoded = passwordEncoder.encode(user.getPassword());
		user.setPassword(passwordEncoded);
		userRepository.save(user);
		return "Inscription effectuée avec succès";
	}

}
