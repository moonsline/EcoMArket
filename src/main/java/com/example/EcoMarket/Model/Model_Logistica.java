package com.example.EcoMarket.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data


@Entity
public class Model_Logistica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLogistica;
    private String nombre;
    private String email;
    private String password;
    private String rol;

    public Model_Logistica(int idLogistica, String nombre, String email, String password, String rol) {
        this.idLogistica = idLogistica;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    public Model_Logistica() {
    }

    public int getIdLogistica() {
        return idLogistica;
    }

    public void setIdLogistica(int idLogistica) {
        this.idLogistica = idLogistica;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
