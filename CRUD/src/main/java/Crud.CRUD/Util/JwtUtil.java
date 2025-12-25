package Crud.CRUD.Util;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRET = "This_is_very_long_during_winder_season_of_chapgpt";
    private final long EXPIRATION = 1000*60;
    private final Key secretkey = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    public String extractToken(String Token){
       return Jwts.parserBuilder()
                .setSigningKey(secretkey)
                .build()
                .parseClaimsJws(Token)
                .getBody()
                .getSubject();
    }
    public String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION))
                .signWith(secretkey, SignatureAlgorithm.HS256
                )
                .compact();
    }
    public Boolean validate(String Token){
        try{
            extractToken(Token);
            return true;
        } catch (JwtException e){
            return false;
        }
    }
}
  