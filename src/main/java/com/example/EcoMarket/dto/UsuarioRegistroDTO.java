package com.example.EcoMarket.dto;

// DTO para registro de usuario. Solo contiene los datos necesarios para crear el usuario.
// Así evito que el frontend envíe datos que no corresponden, como el id o el estado activo.
public class UsuarioRegistroDTO {
    private String nombre;
    private String email;
    private String password;
    private String rut;
    private String rol;
    // Constructor vacío para compatibilidad con frameworks y deserialización
    public UsuarioRegistroDTO() {}

    // Getters y setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRut() { return rut; }
    public void setRut(String rut) { this.rut = rut; }
    public  String getRol() { return rol; }
}
