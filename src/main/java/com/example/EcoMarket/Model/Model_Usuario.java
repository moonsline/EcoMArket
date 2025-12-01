package com.example.EcoMarket.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data


@Entity
@Table(name = "MODEL_USUARIO")
public class Model_Usuario {

    @Id
    // La columna ID_USUARIO ya es identidad en Oracle
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Integer id;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ROL")
    private String rol;

    @Column(name = "RUT")
    private String rut;

    @Column(name = "ACTIVO")
    private Integer activo;

    public Model_Usuario(Integer id, String email, String nombre, String password, String rol, String rut, Integer activo) {
        this.id = id;
        this.email = email;
        this.nombre = nombre;
        this.password = password;
        this.rol = rol;
        this.rut = rut;
        this.activo = activo;
    }

    public Model_Usuario() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }
}
