package org.subhankar.user.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expirationMs}")
    private long expirationMs;

    public List<String> extractRoles(String token) {
        return (List<String>) extractClaim(token, claims -> claims.get("roles", Collection.class));
    }
    public String getIdFromToken(String token) {
        return extractClaim(token, claims -> claims.get("id", String.class));
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean hasRole(String token, String role) {
        List<String> roles = extractRoles(token);
        boolean flag = false;
        for (String r : roles) {
            if (r.equals(role)) {
                flag = true;
                break;
            }
        }
        return flag;
    }


}
