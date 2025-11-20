package com.example.EcoMarket.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.example.EcoMarket.dto.LoginRequest;
import com.example.EcoMarket.dto.LoginResponse;
import com.example.EcoMarket.utils.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Endpoint para iniciar sesión.
     * Recibe un email y contraseña, verifica que sean correctos
     * y devuelve un token JWT que el frontend usará en cada request.
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        // 1) Creamos un objeto con las credenciales recibidas
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        // 2) Le pedimos a Spring Security que valide estas credenciales
        Authentication authentication = authenticationManager.authenticate(authToken);

        // 3) Guardamos la autenticación en el contexto de Spring (no obligatorio, pero buena práctica)
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 4) Generamos un token JWT usando el email del usuario autenticado
        String jwt = jwtUtil.generateToken(authentication);

        // 5) Creamos la respuesta que enviaremos al frontend
        LoginResponse response = new LoginResponse(
                request.getEmail(),
                jwt,
                authentication.getAuthorities()
                        .stream()
                        .findFirst()
                        .map(a -> a.getAuthority())
                        .orElse("ROLE_USER")
        );

        return ResponseEntity.ok(response);
    }
}
