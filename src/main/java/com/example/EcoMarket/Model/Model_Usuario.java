package com.example.EcoMarket.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    // Campo 'CORREO' eliminado del modelo; la BD será normalizada a solo EMAIL
}
