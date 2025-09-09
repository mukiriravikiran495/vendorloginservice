package com.vendorloginservice.utils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.vendorloginservice.domain.TokenResponse;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;



@Component
public class JwtUtil {
	private final String SECRET = "your_secure_secret_key_should_be_long_enough";
    
    
	public TokenResponse generateToken(String mobile) {
	    Key key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

	    // correlation ID = JWT jti
	    String correlationId = UUID.randomUUID().toString();

	    // database token UUID (primary key in cust_tokens table)
	    String tokenUuid = UUID.randomUUID().toString();

	    Date issuedAt = new Date();
	    Date expiry = new Date(System.currentTimeMillis() + 1000 * 60 * 30); // 30 min

	    // Build JWT with correlationId as jti
	    String jwt = Jwts.builder()
	            .setId(correlationId)      // jti
	            .setSubject(mobile)        // user identifier
	            .setIssuedAt(issuedAt)
	            .setExpiration(expiry)
	            .signWith(key)
	            .compact();

	    // Convert Date -> Instant for DTO
	    LocalDateTime issuedAtInstant = LocalDateTime.now();
	    LocalDateTime expiryInstant = LocalDateTime.now();

	    // Save tokenUuid + correlationId + jwt into cust_tokens table here
	    // repository.save(...)

		return new TokenResponse(jwt, tokenUuid, correlationId, "Bearer", issuedAtInstant, expiryInstant);
	}

    public String generateTokenId() {
    	Key key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setSubject("default-user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) // 30 mins
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    

    public boolean validateToken(String token, String username) {
        return extractUsername(token).equals(username) && !isTokenExpired(token);
    }

    
    
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getExpiration();
        return expiration.before(new Date());
    }
}