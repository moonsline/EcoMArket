package com.example.EcoMarket.hateoas;

import com.example.EcoMarket.Model.Model_Usuario;
import org.springframework.hateoas.RepresentationModel;

public class UsuarioModel extends RepresentationModel<UsuarioModel> {
    private Integer id;
    private String nombre;
    private String email;
    private String rol;
    private String rut;
    private Integer activo;

    public UsuarioModel(Model_Usuario usuario) {
        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
        this.email = usuario.getEmail();
        this.rol = usuario.getRol();
        this.rut = usuario.getRut();
        this.activo = usuario.getActivo();
    }

    public UsuarioModel() {}

    public Integer getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getRol() { return rol; }
    public String getRut() { return rut; }
    public Integer getActivo() { return activo; }

    public void setId(Integer id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEmail(String email) { this.email = email; }
    public void setRol(String rol) { this.rol = rol; }
    public void setRut(String rut) { this.rut = rut; }
    public void setActivo(Integer activo) { this.activo = activo; }
}