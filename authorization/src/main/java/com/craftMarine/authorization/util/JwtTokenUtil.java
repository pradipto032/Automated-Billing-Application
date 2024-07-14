package com.craftMarine.authorization.util;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.craftMarine.authorization.entities.MyUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class JwtTokenUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
	
	private static int a = 1000;

	private static final long EXPIRE_DURATION = 24 * 60 * 60 * (long) a;

	private String SECRET_KEY = "secret";

	public String generateAccessToken(MyUserDetails myUserDetails) {

		return Jwts.builder().setSubject(myUserDetails.getUsername()).claim("roles", myUserDetails.getRoles())
				.setIssuer("Code").setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
	}

	public boolean validateAccessToken(String token) {
		try {
			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
			return true;
		} catch (ExpiredJwtException ex) {
			LOGGER.error("JWT expired", ex);
		} catch (IllegalArgumentException ex) {
			LOGGER.error("Token is null or empty", ex);
		} catch (MalformedJwtException ex) {
			LOGGER.error("Invalid JWT", ex);
		} catch (UnsupportedJwtException ex) {
			LOGGER.error("JWT is not supported", ex);
		} catch (SignatureException ex) {
			LOGGER.error("Signature validation failed", ex);
		}
		return false;
	}
	
	public String getSubject(String token) {
		return parseClaims(token).getSubject();
	}
	
	public Claims parseClaims(String token) {
		return Jwts.parser()
				.setSigningKey(SECRET_KEY)
				.parseClaimsJws(token)
				.getBody();
	}
	
}
