package com.craftMarine.authorization.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.craftMarine.authorization.entities.MyUserDetails;
import com.craftMarine.authorization.util.JwtTokenUtil;

import io.jsonwebtoken.Claims;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (!hasAuthorizationHeader(request)) {
			filterChain.doFilter(request, response);
			return;
		}

		String accessToken = getAccessToken(request);

		if (!jwtTokenUtil.validateAccessToken(accessToken)) {
			filterChain.doFilter(request, response);
			return;
		}

		setAuthenticationContext(accessToken, request);
		filterChain.doFilter(request, response);

	}

	private boolean hasAuthorizationHeader(HttpServletRequest request) {

		String header = request.getHeader("Authorization");

		System.out.println("Authorization header: " + header);

		if (ObjectUtils.isEmpty(header) || !header.startsWith("Bearer")) {
			return false;
		}

		return true;
	}

	private String getAccessToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		String token = header.split(" ")[1].trim();

		System.out.println("Access token: " + token);

		return token;

	}

	private UserDetails getUserDetails(String accesstoken) {
		MyUserDetails user = new MyUserDetails();
		Claims claims = jwtTokenUtil.parseClaims(accesstoken);
		String claimRoles = (String) claims.get("roles");
		System.out.println(claimRoles);
		user.setRoles(claimRoles);
		String claimSubject = (String) claims.get(Claims.SUBJECT);
		user.setUserName(claimSubject);

		return user;
	}

	private void setAuthenticationContext(String accessToken, HttpServletRequest request) {
		UserDetails userDetails = getUserDetails(accessToken);

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
				userDetails.getAuthorities());

		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
