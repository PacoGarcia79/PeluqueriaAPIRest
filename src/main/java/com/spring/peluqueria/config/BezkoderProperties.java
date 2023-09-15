package com.spring.peluqueria.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "bezkoder")
public class BezkoderProperties {
	
	private String jwtCookieName;
	private String jwtSecret;
	private String jwtExpirationMs;
	public String getJwtCookieName() {
		return jwtCookieName;
	}
	public void setJwtCookieName(String jwtCookieName) {
		this.jwtCookieName = jwtCookieName;
	}
	public String getJwtSecret() {
		return jwtSecret;
	}
	public void setJwtSecret(String jwtSecret) {
		this.jwtSecret = jwtSecret;
	}
	public String getJwtExpirationMs() {
		return jwtExpirationMs;
	}
	public void setJwtExpirationMs(String jwtExpirationMs) {
		this.jwtExpirationMs = jwtExpirationMs;
	}
	
	
}
