package com.example.EcoMarket.hateoas;

import com.example.EcoMarket.Model.Model_Usuario;
import org.springframework.hateoas.RepresentationModel;

public class UsuarioModel extends RepresentationModel<UsuarioModel> {
    private Integer id;
    private String nombre;
    private String correo;
    private String contrasena;

    public UsuarioModel(Model_Usuario usuario) {
        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
        this.correo = usuario.getCorreo();
        this.contrasena = usuario.getContrasena();
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasena() {
        return contrasena;
    }
}