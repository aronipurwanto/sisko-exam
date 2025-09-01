package com.sisko.exam.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Map;


@Service("jwtServiceSecurity")
public class JwtService {
    private final Key key;
    private final int expMinutes;


    public JwtService(@Value("${app.jwt.secret}") String base64Secret,
                      @Value("${app.jwt.exp-minutes}") int expMinutes) {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(base64Secret));
        this.expMinutes = expMinutes;
    }


    public String generateToken(UserDetails user, Map<String, Object> claims) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(expMinutes * 60L)))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }


    public Claims parse(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }
}