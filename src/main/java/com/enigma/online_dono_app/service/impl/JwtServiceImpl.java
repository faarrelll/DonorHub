package com.enigma.online_dono_app.service.impl;

import com.enigma.online_dono_app.entity.UserAccount;
import com.enigma.online_dono_app.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtServiceImpl implements JwtService {

    private final String SECRET_KEY = "asjhdgashdjkashdlkashjkdhe73yuqwidhjkasbdasjhbgdjhkas";
    private final String ISSUER = "enigma";
    @Override
    public String generateToken(UserAccount userAccount) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userAccount.getRole());
        return Jwts.builder()
                .subject(userAccount.getUsername())
                .claims(claims)
                .issuer(ISSUER)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10 ))
                .signWith(getKey())
                .compact();
    }


    @Override
    public String extractUsername(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }

    @Override
    public boolean validateToken(String jwtToken, UserDetails userAccount) {
        final String username = extractUsername(jwtToken);
        return (username.equals(userAccount.getUsername()) && !isTokenExpired(jwtToken));
    }



    private <T> T extractClaim(String jwtToken, Function<Claims,T> claimsResolver) {
        final Claims claims = extractALLClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    private Claims extractALLClaims(String jwtToken) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();

    }

    private boolean isTokenExpired(String jwtToken) {
        return extractExpiration(jwtToken).before(new Date());
    }

    private Date extractExpiration(String jwtToken) {
        return extractClaim(jwtToken, Claims::getExpiration);
    }

    private SecretKey getKey() {
        byte[] encodedKey =  Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(encodedKey);
    }
}
