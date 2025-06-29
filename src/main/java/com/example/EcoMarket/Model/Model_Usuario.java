package com.example.EcoMarket.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Model_Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Cambiado a Integer y nombre estándar

    private String nombre;
    private String correo; // Cambiado a "correo" para ser coherente
    private String contrasena; // Cambiado a "contrasena" para ser coherente
    private String rol;
}
