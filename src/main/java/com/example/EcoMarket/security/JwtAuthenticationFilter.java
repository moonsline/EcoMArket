package com.example.EcoMarket.security;

import com.example.EcoMarket.utils.JwtUtil;
import com.example.EcoMarket.Service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Este filtro se ejecuta antes de cada request.
 * Revisa si llega un token JWT en la cabecera "Authorization".
 * Si está presente y es válido, autentico al usuario en el contexto de Spring.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // Obtengo el token de la cabecera Authorization
        String header = request.getHeader("Authorization");
        String token = null;
        String email = null;

        // Ejemplo del header esperado:
        // Authorization: Bearer eyJhbGciOi...
        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7); // saco el "Bearer "
            email = jwtUtil.getEmailFromToken(token); // obtengo el email guardado en el token
        }

        // Si tengo email y aún no hay un contexto de autenticación
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Cargo los detalles del usuario desde la BD
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            // Verifico el token
            if (jwtUtil.validateToken(token)) {

                // Creo la autenticación basada en el usuario cargado
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities()
                        );

                authentication.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // Finalmente: asigno la autenticación al contexto de Spring
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // Continuar con el resto del flujo
        filterChain.doFilter(request, response);
    }
}
