package com.example.EcoMarket.hateoas;

import com.example.EcoMarket.Model.Model_EmpleadoVentas;
import org.springframework.hateoas.RepresentationModel;

public class EmpleadoVentasModel extends RepresentationModel<EmpleadoVentasModel> {

    private int idEmpleado;
    private String nombre;
    private String email;
    private String rol;

    public EmpleadoVentasModel(Model_EmpleadoVentas ev) {
        this.idEmpleado = ev.getIdEmpleado();
        this.nombre = ev.getNombre();
        this.email = ev.getEmail();
        this.rol = ev.getRol();
    }

    public int getIdEmpleado() { return idEmpleado; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getRol() { return rol; }
}
