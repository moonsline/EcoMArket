package com.example.EcoMarket.hateoas;

import org.springframework.hateoas.RepresentationModel;

public class GerenteTiendaModel extends RepresentationModel<GerenteTiendaModel> {

    private int idGerente;
    private String nombre;
    private String email;
    private String password;
    private String rol;

    public int getIdGerente() {return idGerente;}
    public String getNombre() {return nombre;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public String getRol() {return rol;}

}
