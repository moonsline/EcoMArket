package com.example.EcoMarket.hateoas;

import com.example.EcoMarket.Model.Model_Producto;
import org.springframework.hateoas.RepresentationModel;

public class ProductoModel extends RepresentationModel<ProductoModel> {
    private int id;
    private String nombre;
    private float precio;
    private int stock;

    public ProductoModel(Model_Producto p) {
        this.id = p.getId();
        this.nombre = p.getNombre();
        this.precio = p.getPrecio();
        this.stock = p.getStock();
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }
}

