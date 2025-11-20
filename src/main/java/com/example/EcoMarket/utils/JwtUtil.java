package com.example.EcoMarket.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Esta clase es responsable de generar y validar tokens JWT.
 * Aquí definimos la clave secreta, la expiración y
 * los datos que queremos incluir dentro del token.
 */
@Component
public class JwtUtil {

    // La clave secreta se lee desde application.properties
    @Value("${jwt.secret}")
    private String secret;

    // Tiempo de expiración (también desde application.properties)
    @Value("${jwt.expiration}")
    private long expiration;

    /**
     * Genera un JWT a partir de la autenticación de Spring Security.
     * Incluimos email (username) y rol en el token.
     */
    public String generateToken(Authentication authentication) {

        String email = authentication.getName();

        // Extraemos el rol del usuario
        String roles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        // Convertimos la secret key en una Key válida
        Key key = Keys.hmacShaKeyFor(secret.getBytes());

        // Armamos el JWT
        return Jwts.builder()
                .setSubject(email)               // email del usuario
                .claim("roles", roles)           // guardamos roles
                .setIssuedAt(new Date())         // fecha de creación
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key, SignatureAlgorithm.HS256) // firmamos
                .compact();
    }

    /**
     * Obtenemos el email guardado en el "subject" del token.
     */
    public String getEmailFromToken(String token) {
        return parseToken(token).getBody().getSubject();
    }

    /**
     * Validamos firma, expiración y estructura del token.
     */
    public boolean validateToken(String token) {
        try {
            parseToken(token); // si falla, cae al catch
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("Token inválido: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método interno para parsear el token con la secret key.
     */
    private Jws<Claims> parseToken(String token) {
        Key key = Keys.hmacShaKeyFor(secret.getBytes());
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }
}
