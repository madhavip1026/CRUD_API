package com.Springboot.CRUD.Service;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

    private final String secret;

    public JWTService(@Value("${jwt.secret}") String secret) {
        this.secret = secret;
    }

    public String generateToken(String email, String role) {

        return Jwts.builder()
                .claim("email", email)
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000 * 60 * 60
                        )
                )
                .signWith(
                        Keys.hmacShaKeyFor(
                                secret.getBytes(
                                        StandardCharsets.UTF_8
                                )
                        )
                )
                .compact();
    }
    public String extractRole(String token) {
    return Jwts.parser()
            .verifyWith(
                    Keys.hmacShaKeyFor(
                            secret.getBytes(
                                    StandardCharsets.UTF_8
                            )
                    )
            )
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .get("role", String.class);
}

    public String extractEmail(String token) {

        return Jwts.parser()
                .verifyWith(
                        Keys.hmacShaKeyFor(
                                secret.getBytes(
                                        StandardCharsets.UTF_8
                                )
                        )
                )
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token) {

        try {

            Jwts.parser()
                    .verifyWith(
                            Keys.hmacShaKeyFor(
                                    secret.getBytes(
                                            StandardCharsets.UTF_8
                                    )
                            )
                    )
                    .build()
                    .parseSignedClaims(token);

            return true;

        } catch (Exception e) {

            return false;
        }
    }
}


