package com.spring.security.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.ExpiredJwtException;

@Service
public class JWTService {

    private byte[] secret;
    public JWTService(){
        try{
            KeyGenerator key = KeyGenerator.getInstance("HmacSHA256");
            this.secret = key.generateKey().getEncoded();
        }
        catch(NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }

    public String generateToken(String username) {
        Map<String , Object> claims = new HashMap<>();
        return Jwts.builder()
                        .claims()
                        .add(claims)
                        .subject(username)
                        .issuedAt(new Date(System.currentTimeMillis()))
                        .expiration(new Date(System.currentTimeMillis() + 3600 * 10))
                        .and()
                        .signWith(this.getKey())
                        .compact();
    }

    private SecretKey getKey() {
       return Keys.hmacShaKeyFor(secret);
    }

    public String extractUserName(String token) {
        try{
            return extractClaim(token, Claims::getSubject);
        }
        catch(SignatureException e){
            return null;
        }
        catch(ExpiredJwtException e){
            return null;
        }
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(this.getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}
