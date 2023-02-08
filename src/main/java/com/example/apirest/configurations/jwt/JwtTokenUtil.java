package com.example.apirest.configurations.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public final class JwtTokenUtil implements Serializable {

    // Token valid for 10 minutes after generation
    public static final long JWT_TOKEN_VALIDITY = 10 * 60;

    @Value("${jwt.secret}")
    private String jwtSecret;

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Generate token for user. While creating the token:
     *  1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID. We can also put our custom claims. This can
     *  be any value we want which might include user role, user authorities and so on.
     *  2. Sign the JWT using the HS512 algorithm and secret key
     *  3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
     *  compaction of the JWT to a URL-safe string
     * @param userDetails
     * @return
     */
    public String generateJwtToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(getSigningKey()).compact();
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String userEmail = getUserEmailFromToken(token);
        Boolean isTokenExpired = getClaimFromToken(token, Claims::getExpiration).before(new Date());
        return (userEmail.equals(userDetails.getUsername()) && !isTokenExpired);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //for retrieving any information from token we need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
    }

    //retrieve username from jwt token
    public String getUserEmailFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }
}
