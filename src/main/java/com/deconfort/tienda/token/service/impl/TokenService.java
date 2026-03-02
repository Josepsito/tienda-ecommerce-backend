package com.deconfort.tienda.token.service.impl;

import com.deconfort.tienda.token.model.dto.TokenPayload;
import com.deconfort.tienda.token.model.dto.TokenRequest;
import com.deconfort.tienda.token.properties.JwtProperties;
import com.deconfort.tienda.token.service.ITokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TokenService implements ITokenService {

    private final JwtProperties properties;
    private final SecretKey SECRET_KEY;

    public TokenService(JwtProperties properties) {
        this.properties = properties;
        this.SECRET_KEY= Keys.hmacShaKeyFor(properties.secret().getBytes());
    }

    @Override
    public String generate(TokenRequest request) {

        Date now = new Date();
        Date expiration = new Date(now.getTime() + properties.expiration());

        return Jwts.builder()
                .subject(request.subject())
                .claim("email",request.email())
                .claim("roles",request.roles())
                .issuedAt(now)
                .expiration(expiration)
                .signWith(SECRET_KEY)
                .compact();
    }

    @Override
    public TokenPayload validate(String token) {

        Claims claims = Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        List roles = Optional.ofNullable(claims.get("roles", List.class))
                .orElse(List.of())
                .stream()
                .map(Object::toString)
                .toList();

        return new TokenPayload(
                claims.getSubject(),
                claims.get("email", String.class),
                roles,
                claims.getIssuedAt(),
                claims.getExpiration()
        );
    }
}

