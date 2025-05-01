package com.example.EcoMarket.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Genera getters, setters, toString, equals, hashCode y un constructor con los campos requeridos.
@AllArgsConstructor //Genera un constructor con todos los campos
@NoArgsConstructor //Genera un constructor con todos los campos


public class Model_Usuario {
    private int idUsuario;
    private String nombre;
    private String email;
    private String password;
    private String rol;



}
