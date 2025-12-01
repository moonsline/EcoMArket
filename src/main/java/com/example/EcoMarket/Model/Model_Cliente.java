package com.example.EcoMarket.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data


@Entity
public class Model_Cliente  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCliente;
    private String nombre;
    private String email;
    private String password;
    private String rol;
    private String direccion;



    // Opcional: constructor sin idCliente
    public Model_Cliente(String nombre, String email, String password, String rol, String direccion) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.direccion = direccion;
    }

    public Model_Cliente(int idCliente, String nombre, String email, String password, String rol, String direccion) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.direccion = direccion;
    }

    public Model_Cliente() {
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}