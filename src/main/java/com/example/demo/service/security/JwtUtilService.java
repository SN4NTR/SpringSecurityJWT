package com.example.demo.service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;
import static java.lang.System.currentTimeMillis;

@Service
public class JwtUtilService {

    private final String SECRET_KEY = "secret";

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("my_claim", "my_value");

        Claims claims = new DefaultClaims(claimsMap);
        String username = userDetails.getUsername();

        return createToken(claims, username);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = userDetails.getUsername();
        String jwtSubject = extractUsername(token);

        return username.equals(jwtSubject) && !isTokenExpired(token);
    }

    public String extractUsername(String token) {
        Claims claims = extractClaims(token);

        return claims.getSubject();
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        Date expirationDate = extractExpirationDate(token);

        return expirationDate.before(new Date());
    }

    private Date extractExpirationDate(String token) {
        Claims claims = extractClaims(token);

        return claims.getExpiration();
    }

    private String createToken(Claims claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(currentTimeMillis()))
                .setExpiration(new Date(currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(HS256, SECRET_KEY)
                .compact();
    }
}
