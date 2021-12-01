package com.competition.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.competition.config.JwtTokenUtil;
import com.competition.entities.Users;
import com.competition.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private UserService usersService;

	@PostMapping("/login")
	public String createAuthenticationToken(@RequestBody Users user) throws Exception {

		Authentication auth = authenticate(user.getEmail(), user.getPassword());

		final String token = jwtTokenUtil.generateToken(auth.getName(), auth.getAuthorities());
		return token;
	}

	private Authentication authenticate(String username, String password) throws Exception {
		try {
			Authentication auth = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			return auth;
		} catch (InternalAuthenticationServiceException e) {
			throw new InternalAuthenticationServiceException("INVALID_USERNAME", e);
		}

		catch (DisabledException e) {
			throw new DisabledException("USER_DISABLED", e);
		}

		catch (BadCredentialsException e) {
			throw new BadCredentialsException("INVALID_CREDENTIALS", e);
		}
	}

	@PostMapping("/register")
	public String register(@RequestBody Users user) throws Exception {
		return usersService.register(user);

	}
}
