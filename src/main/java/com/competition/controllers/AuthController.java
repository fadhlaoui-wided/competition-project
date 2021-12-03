package com.competition.controllers;


import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;


import com.competition.config.JwtTokenUtil;
import com.competition.entities.Users;
import com.competition.services.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private UserService usersService;

	@PostMapping(path ="/login" )
	public Map<String,Object>  createAuthenticationToken(@RequestBody Users user) throws Exception {

		Authentication auth = authenticate(user.getEmail(), user.getPassword());

		final String token = jwtTokenUtil.generateToken(auth.getName(), auth.getAuthorities());


		Map<String,Object> map = new HashMap<>(3);
		map.put("accessToken", token);
		map.put("role", 1);
		map.put("user", usersService.loadUserByUsername(user.getEmail()));
		return  map ;
	}
	@PostMapping(path ="/loginToken" )
	public Map<String,Object>  logintoken  (@RequestBody Users user) throws Exception {
		Authentication auth = authenticate(user.getEmail(), "wided1234");

		final String token = jwtTokenUtil.generateToken(auth.getName(), auth.getAuthorities());

		Users  us  =  new Users();
		us = usersService.loadUserByUsername(user.getEmail());
		if (  us != null) {
			Map<String,Object> map = new HashMap<>(3);
			map.put("accessToken", token);
			map.put("role", 1);
			map.put("user", usersService.loadUserByUsername(user.getEmail()));
			return  map ;
		}else
			return null ;
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
