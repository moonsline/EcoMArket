package com.example.EcoMarket.hateoas;

import com.example.EcoMarket.Model.Model_Producto;
import org.springframework.hateoas.RepresentationModel;

public class ProductoModel extends RepresentationModel<ProductoModel> {
    private int id;
    private String nombre;
    private int precio;
    private int stock;
    private String img;
    private java.time.LocalDate expirationDate;
    private String imgNutricional;

    public ProductoModel(Model_Producto p) {
        this.id = p.getId();
        this.nombre = p.getNombre();
        this.precio = p.getPrecio();
        this.stock = p.getStock();
        this.img = p.getImg();
        this.expirationDate = p.getExpirationDate();
        this.imgNutricional = p.getImgNutricional();
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public String getImg() {
        return img;
    }

    public java.time.LocalDate getExpirationDate() {
        return expirationDate;
    }

    public  String getImgNutricional() {return imgNutricional;}
}
