package com.example.EcoMarket.Model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Model_Cliente extends Model_Usuario {


    private String direccion;
    private List<String> historialPedidos;


}