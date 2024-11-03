package com.ssvs.seguro_salud_vida_sana.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;
import java.util.Map;
import java.util.HashMap;

@Component
public class JwtUtil {

    // Genera una clave segura de al menos 256 bits
    private final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                   .setSigningKey(SECRET_KEY)
                   .build()
                   .parseClaimsJws(token)
                   .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        
        // Agregar el rol como un "claim" al token
        String role = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .findFirst()
                    .orElse(null);  // Si el usuario tiene un rol, se obtiene el primero

        claims.put("role", role);  // Añadir el rol al token

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername()) // El subject es el nombre del usuario (o email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SECRET_KEY) // Reemplaza "secretKey" con tu llave secreta
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String correo = extractUsername(token);
        return (correo.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
