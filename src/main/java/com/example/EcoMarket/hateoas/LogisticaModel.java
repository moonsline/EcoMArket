package com.example.EcoMarket.hateoas;

import com.example.EcoMarket.Model.Model_Logistica;
import org.springframework.hateoas.RepresentationModel;

public class LogisticaModel extends RepresentationModel<LogisticaModel>{

    private int idLogistica;
    private String nombre;
    private String email;
    private String password;
    private String rol;

    public LogisticaModel(Model_Logistica logistica){
        this.idLogistica = logistica.getIdLogistica();
        this.nombre = logistica.getNombre();
        this.email = logistica.getEmail();
        this.password = logistica.getPassword();
        this.rol = logistica.getRol();
    }

    public int getIdLogistica() {return idLogistica;}
    public String getNombre() {return nombre;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public String getRol() {return rol;}
}
