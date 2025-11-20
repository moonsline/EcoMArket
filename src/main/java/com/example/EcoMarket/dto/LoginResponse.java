package com.example.EcoMarket.dto;

/**
 * Este DTO es lo que devolvemos al frontend cuando el login es exitoso.
 * Incluimos el email, el token y el rol del usuario para que el frontend
 * pueda decidir qué vistas mostrar.
 */
public class LoginResponse {

    private String email;
    private String token;
    private String role;

    public LoginResponse() {}

    public LoginResponse(String email, String token, String role) {
        this.email = email;
        this.token = token;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    public String getRole() {
        return role;
    }
}
