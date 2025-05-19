package com.example.EcoMarket.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Model_Logistica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLogistica;
    private String nombre;
    private String email;
    private String password;
    private String rol;
}
