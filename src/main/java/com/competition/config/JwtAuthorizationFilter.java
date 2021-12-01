package com.competition.config;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class JwtAuthorizationFilter extends OncePerRequestFilter {


	@Value("${jwt.secret}")
	private String signinKey;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String requestHeader = request.getHeader("Authorization");

		
		if (requestHeader == null || !requestHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response); 
			return;
		}
	
		String token = requestHeader.replace("Bearer ", "");

		try { 

		

			String username = jwtTokenUtil.getUsernameFromToken(token);
			if (username != null) {
				

			
				List<GrantedAuthority> authorities = jwtTokenUtil.getAuthorityFromToken(token);

				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null,
						authorities);

			
				SecurityContextHolder.getContext().setAuthentication(auth);
			}

		} catch (Exception e) {
			
			e.printStackTrace();
			SecurityContextHolder.clearContext();
		}

		filterChain.doFilter(request, response);

	}

}
