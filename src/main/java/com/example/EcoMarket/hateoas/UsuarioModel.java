package com.example.EcoMarket.hateoas;

import com.example.EcoMarket.Model.Model_Usuario;
import org.springframework.hateoas.RepresentationModel;

public class UsuarioModel extends RepresentationModel<UsuarioModel> {
    private Integer id;
    private String nombre;
    private String email;
    private String rol;

    public UsuarioModel(Model_Usuario usuario) {
        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
        this.email = usuario.getEmail();
        this.rol = usuario.getRol();
    }

    public Integer getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getRol() { return rol; }
}