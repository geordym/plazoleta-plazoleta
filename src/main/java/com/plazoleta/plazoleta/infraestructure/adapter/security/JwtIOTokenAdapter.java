package com.plazoleta.plazoleta.infraestructure.adapter.security;

import com.plazoleta.plazoleta.domain.spi.security.ITokenProviderPort;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

import static com.plazoleta.plazoleta.infraestructure.configuration.security.JwtSecurityConstants.*;


public class JwtIOTokenAdapter implements ITokenProviderPort {

    @Value("${jwt.secure.key}")
    private String SECRET_KEY;


    @Override
    public String generateAccessToken(LocalDateTime issuedAt, String subject, LocalDateTime expirationAt, Map<String, Object> claims) {
        return createAccessToken(claims, subject, issuedAt, expirationAt);
    }

    @Override
    public String generateRefreshToken(LocalDateTime issuedAt, String subject, LocalDateTime expirationAt) {
        return createRefreshToken(subject, issuedAt, expirationAt);
    }

    @Override
    public Boolean validateToken(String token, String username) {
        final String tokenUsername = (String) extractClaim(token, CLAIM_SUBJECT_KEY);
        return (username.equals(tokenUsername) && !isTokenExpired(token));
    }


    @Override
    public Boolean isTokenExpired(String token) {
        Date expirationDate = extractExpiration(token);
        boolean expired = expirationDate.before(new Date());
        return expired;
    }

    @Override
    public Long extractUserId(String token) {
        final Map<String, Object> claims = extractAllClaims(token);
        String userId = (String) claims.get(CLAIM_KEY_USERID);
        return Long.valueOf(userId);
    }

    @Override
    public String extractRole(String token) {
        final Map<String, Object> claims = extractAllClaims(token);
        return (String) claims.get(CLAIM_KEY_ROLE);
    }

    @Override
    public Object extractClaim(String token, String claimKey) {
        Map<String, Object> claims = extractAllClaims(token);
        return claims.get(claimKey);
    }

    @Override
    public String extractUsername(String token) {
        return (String) extractClaim(token, CLAIM_KEY_USERNAME);
    }

    @Override
    public String extractSubject(String token) {
        return (String) extractClaim(token, CLAIM_SUBJECT_KEY);
    }

    @Override
    public Map<String, Object> extractAllClaims(String token) {
        byte[] secret = SECRET_KEY.getBytes();

        Claims claims =  Jwts.parser()
                .setSigningKey(secret)
                .build().parseSignedClaims(token).getPayload();
        return claims;
    }

    @Override
    public Date extractExpiration(String token) {
        final int SECONDS_TO_MILLISECONDS_MULTIPLIER = 1000;
        Long expirationTimeSeconds = (Long) extractClaim(token, CLAIM_EXPIRATION_KEY);

        if (expirationTimeSeconds != null) {
            long expirationTimeMillis = expirationTimeSeconds * SECONDS_TO_MILLISECONDS_MULTIPLIER;
            return new Date(expirationTimeMillis);
        }

        return null;
    }
    private String createAccessToken(Map<String, Object> claims, String subject, LocalDateTime issuedAt, LocalDateTime expirationAt) {
        Date issuedAtDate = convertToDate(issuedAt);
        Date expirationDate = convertToDate(expirationAt);

        return Jwts.builder().claims(claims).subject(String.valueOf(subject)).issuedAt(issuedAtDate).expiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes())
                .compact();
    }

    private String createRefreshToken(String subject, LocalDateTime issuedAt, LocalDateTime expirationAt) {
        Date issuedAtDate = issuedAt != null ? convertToDate(issuedAt) : new Date();
        Date expirationDate = expirationAt != null ? convertToDate(expirationAt) : new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 30); // 30 días por defecto

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(issuedAtDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes())
                .compact();
    }

    private Date convertToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

}
