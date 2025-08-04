package com.projetospring.chatonline.infrastructure.tokens;

import com.projetospring.chatonline.core.dtos.JwtTokenDto;
import com.projetospring.chatonline.core.security.token.TokenEntity;
import com.projetospring.chatonline.core.security.token.TokenRepository;
import com.projetospring.chatonline.core.security.token.enums.TypeToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

@Service
public class JwtService {

	@Value("${jwt.secret-key}")
	private String SECRET_KEY;

	@Value("${jwt.access-token.expiration}")
	private long accessTokenExpiration;

	@Value("${jwt.refresh-token.expiration}") // 7 dias default
	private long refreshTokenExpiration;

	private final TokenRepository tokenRepository;

	public JwtService(TokenRepository tokenRepository) {
		this.tokenRepository = tokenRepository;
	}

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public String extractTokenId(String token) {
		return extractClaim(token, claims -> claims.get("jti", String.class));
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		try {
			return Jwts.parserBuilder()
					.setSigningKey(getSigningKey())
					.build()
					.parseClaimsJws(token)
					.getBody();
		} catch (ExpiredJwtException e) {
			throw new JwtException("Token expirado", e);
		} catch (Exception e) {
			throw new JwtException("Token inválido", e);
		}
	}

	private Key getSigningKey() {
		byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public boolean isTokenValid(String token, UserDetails userDetails) {
		try {
			final String username = extractUsername(token);
			if (!username.equals(userDetails.getUsername())) {
				return false;
			}

			if (isTokenExpired(token)) {
				return false;
			}

			return isTokenValidInDatabase(token);

		} catch (JwtException e) {
			return false;
		}
	}

	public boolean isTokenStructurallyValid(String token, UserDetails userDetails) {
		try {
			final String username = extractUsername(token);
			return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
		} catch (JwtException e) {
			return false;
		}
	}
	public boolean isTokenValidInDatabase(String token) {
		String tokenHash = hashToken(token);
		return tokenRepository.existsValidToken(tokenHash, LocalDateTime.now());
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public JwtTokenDto generateAccessToken(UserDetails userDetails, UUID userId) {
		return generateToken(userDetails, userId, TypeToken.ACCESS_TOKEN, accessTokenExpiration);
	}


	public JwtTokenDto generateRefreshToken(UserDetails userDetails, UUID userId) {
		return generateToken(userDetails, userId, TypeToken.REFRESH_TOKEN, refreshTokenExpiration);
	}

	private JwtTokenDto generateToken(UserDetails userDetails, UUID userId, TypeToken tokenType, long expiration) {
		Date now = new Date();
		Date expirationDate = new Date(now.getTime() + expiration);

		String tokenId = java.util.UUID.randomUUID().toString();

		String jwtToken = Jwts.builder()
				.setSubject(userDetails.getUsername())
				.setId(tokenId)
				.claim("authorities", userDetails.getAuthorities())
				.claim("userId", userId)
				.claim("tokenType", tokenType.name())
				.setIssuedAt(now)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS256, getSigningKey())
				.compact();

		saveTokenToDatabase(jwtToken, userId, tokenType, now, expirationDate);

		return new JwtTokenDto(jwtToken, expirationDate);
	}

	private void saveTokenToDatabase(String token, UUID userId, TypeToken tokenType, Date issuedAt, Date expirationDate) {
		String tokenHash = hashToken(token);

		LocalDateTime createdAt = issuedAt.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDateTime expiresAt = expirationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

		TokenEntity tokenEntity = new TokenEntity(
				tokenHash,
				createdAt,
				expiresAt,
				tokenType,
				false
		);

		tokenRepository.save(tokenEntity);
	}

	public boolean revokeToken(String token) {
		try {
			String tokenHash = hashToken(token);
			int updated = tokenRepository.revokeToken(tokenHash);
			return updated > 0;
		} catch (Exception e) {
			return false;
		}
	}

	public void revokeAllUserTokens(UUID userId) {
		tokenRepository.revokeAllUserTokens(userId);
	}


	public void revokeUserAccessTokens(UUID userId) {
		tokenRepository.revokeUserTokensByType(userId, TypeToken.ACCESS_TOKEN);
	}



	private String hashToken(String token) {
		return DigestUtils.md5DigestAsHex(token.getBytes(StandardCharsets.UTF_8));
	}


	public Long extractUserId(String token) {
		return extractClaim(token, claims -> claims.get("userId", Long.class));
	}

	public TypeToken extractTokenType(String token) {
		String typeString = extractClaim(token, claims -> claims.get("tokenType", String.class));
		return TypeToken.valueOf(typeString);
	}

	public boolean isRefreshToken(String token) {
		try {
			return TypeToken.REFRESH_TOKEN.equals(extractTokenType(token));
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isAccessToken(String token) {
		try {
			return TypeToken.ACCESS_TOKEN.equals(extractTokenType(token));
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isRefreshTokenValid(String token) {
		try {
			return isRefreshToken(token) && isTokenValidInDatabase(token) && !isTokenExpired(token);
		} catch (Exception e) {
			return false;
		}
	}
}