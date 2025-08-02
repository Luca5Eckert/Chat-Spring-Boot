package com.projetospring.chatonline.infrastructure.tolkens;

import com.projetospring.chatonline.core.dtos.JwtTolkenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

	private static final String SECRET_KEY = "password";

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).parseClaimsJws(token).getBody();
	}

	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		return extractClaim(token, Claims::getExpiration).before(new Date());
	}

	public JwtTolkenDto generateToken(UserDetails userDetails) {
		String jwtTolken = Jwts.builder().setSubject(userDetails.getUsername()).claim("authorities", userDetails.getAuthorities())
				.setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10
																													// horas
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes()).compact();
		return new JwtTolkenDto(jwtTolken);
	}
}
