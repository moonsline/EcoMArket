package com.example.EcoMarket.hateoas;

import com.example.EcoMarket.Model.Model_Proveedor;
import org.springframework.hateoas.RepresentationModel;

public class ProveedorModel extends RepresentationModel<ProveedorModel> {

    private int id;
    private String nombre;
    private String contacto;

    public ProveedorModel(Model_Proveedor proveedor) {
        this.id = proveedor.getId();
        this.nombre = proveedor.getNombre();
        this.contacto = proveedor.getContacto();
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContacto() {
        return contacto;
    }
}

