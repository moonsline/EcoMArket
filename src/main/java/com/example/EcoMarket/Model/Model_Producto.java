package com.example.EcoMarket.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Model_Producto {
    private int id;
    private String nombre;
    private float precio;
    private int stock;


}
