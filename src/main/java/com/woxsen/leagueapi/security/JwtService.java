package com.woxsen.leagueapi.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JwtService {
    @Value("${secret.key}")
    private String SECRET_KEY;
    @Value("${secret.validityInHours}")
    private int validityInHours;
    public JwtService() {
    }

    public String generateToken(UserPrinciple userPrinciple){
        return generateToken(new HashMap<>(),userPrinciple);
    }
    public String generateToken(Map<String, Object> extraClaims, UserPrinciple userPrinciple) {

        log.info(userPrinciple.getUsername());
        extraClaims.put("roles",userPrinciple.getAuthorities());
        extraClaims.put("user_id",userPrinciple.getUserId());
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userPrinciple.getUsername().toString())
                .setIssuedAt(new Date(System.currentTimeMillis())) .setExpiration(new Date((new Date()).getTime() + validityInHours*3600000))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();

    }
    public boolean isTokenValid(String token,UserDetails userDetails){
        final String username=extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractUserName(String jwt) {
        return extractClaim(jwt,Claims::getSubject); //or
//        return extractClaim(jwt,(c)->c.getSubject());

    }

    private <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    public Key getSigningKey() {
        byte[] keyBytes= Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
