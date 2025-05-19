package com.example.EcoMarket.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Model_GerenteTienda  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idGerente;
    private String nombre;
    private String email;
    private String password;
    private String rol;

}
