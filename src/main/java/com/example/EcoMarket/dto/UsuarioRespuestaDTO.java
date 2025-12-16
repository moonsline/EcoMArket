package com.example.EcoMarket.dto;

// DTO para enviar datos de usuario al frontend. No incluye la contraseña para mayor seguridad.
// Así me aseguro de no exponer datos sensibles en las respuestas de la API.
public class UsuarioRespuestaDTO {
    private Integer id;
    private String nombre;
    private String email;
    private String rut;
    private String rol;
    private Integer activo;
    // ...getters y setters...
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getRut() { return rut; }
    public void setRut(String rut) { this.rut = rut; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
    public Integer getActivo() { return activo; }
    public void setActivo(Integer activo) { this.activo = activo; }
}

