package com.spring.peluqueria.security.jwt;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import com.spring.peluqueria.config.BezkoderProperties;
import com.spring.peluqueria.services.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	private String jwtSecret;

	private int jwtExpirationMs;

	private String jwtCookie;
	
	@Autowired
	private BezkoderProperties bezkoderProperties;

	public String getJwtFromCookies(HttpServletRequest request) {
		getJwtCookie();
		Cookie cookie = WebUtils.getCookie(request, jwtCookie);
		if (cookie != null) {
			return cookie.getValue();
		} else {
			return null;
		}
	}

	public ResponseCookie generateJwtCookie(UserDetailsImpl userPrincipal) {
		String jwt = generateTokenFromUsername(userPrincipal.getUsername());
		getJwtCookie();
		ResponseCookie cookie = ResponseCookie.from(jwtCookie, jwt).path("/api").maxAge(24 * 60 * 60).httpOnly(true)
				.build();
		return cookie;
	}

	public ResponseCookie getCleanJwtCookie() {
		getJwtCookie();
		ResponseCookie cookie = ResponseCookie.from(jwtCookie, null).path("/api").build();
		return cookie;
	}

	public String getUserNameFromJwtToken(String token) {
		getJwtSecret();
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		getJwtSecret();
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}

	public String generateTokenFromUsername(String username) {
		getJwtSecret();
		getJwtExpirationMs();
		return Jwts.builder().setSubject(username).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}
	
	private void getJwtExpirationMs() {
		jwtExpirationMs = Integer.parseInt(bezkoderProperties.getJwtExpirationMs());
	}	

	private void getJwtSecret() {
		jwtSecret = bezkoderProperties.getJwtSecret();
	}
	
	private void getJwtCookie() {
		jwtCookie = bezkoderProperties.getJwtCookieName();
	}
	
//	private void getJwtExpirationMs() {
//		jwtExpirationMs = Integer.parseInt("86400000");
//	}	
//
//	private void getJwtSecret() {
//		jwtSecret = "bezKoderSecretKey";
//	}
//	
//	private void getJwtCookie() {
//		jwtCookie = "bezkoder";
//	}
}