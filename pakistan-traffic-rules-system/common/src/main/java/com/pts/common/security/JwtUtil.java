package com.pts.common.security;

import com.pts.common.dto.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;


public class JwtUtil {

    public static final long EXPIRATION_TIME = 1000L * 60 * 60; // FOR  1 days

    private static final String SECRET_KEY =  "TaK+HaV&ucCHEPsEVfypW#7g9^k*Z9$V";

    // generate secret key for user
    public SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }


    // create token
    public String createToken(UserDTO user, String loginMethod){

        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getUserRole().name())
                .claim("userId", user.getId())
                .claim("loginType", loginMethod)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();

    }


    // method to extract all claims
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claimsResolver.apply(claims);
    }

    // Extract expiration
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // method to extract username
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    // Check expiration
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Validate token against user
    public boolean isTokenValid(String token) {
        try {

             return  !isTokenExpired(token);

        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }


}
