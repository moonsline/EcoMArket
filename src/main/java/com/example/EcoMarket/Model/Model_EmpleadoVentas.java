package com.example.EcoMarket.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data


@Entity
public class Model_EmpleadoVentas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmpleadoVentas;
    private String nombre;
    private String email;
    private String password;
    private String rol;
    private String direccion;

    public Model_EmpleadoVentas(int idEmpleadoVentas, String nombre, String email, String password, String rol, String direccion) {
        this.idEmpleadoVentas = idEmpleadoVentas;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.direccion = direccion;
    }

    public Model_EmpleadoVentas() {
    }

    public int getIdEmpleadoVentas() {
        return idEmpleadoVentas;
    }

    public void setIdEmpleadoVentas(int idEmpleadoVentas) {
        this.idEmpleadoVentas = idEmpleadoVentas;
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