package com.plazoleta.plazoleta.domain.spi.security;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

public interface ITokenProviderPort {

    String generateAccessToken(LocalDateTime issuedAt, String subject, LocalDateTime expirationAt, Map<String, Object> claims);
    String generateRefreshToken(LocalDateTime issuedAt, String subject, LocalDateTime expirationAt);
    Object extractClaim(String token, String claimKey);
    String extractUsername(String token);
    String extractSubject(String token);

    Map<String, Object> extractAllClaims(String token);
    Date extractExpiration(String token);
    Boolean validateToken(String token, String subject);
    Boolean isTokenExpired(String token);

    Long extractUserId(String token);

    String extractRole(String token);
}
