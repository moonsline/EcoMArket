package com.example.EcoMarket.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data



@Entity
@Table(name = "MODEL_PRODUCTO")
public class Model_Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    private int precio;

    private int stock;
    @Column(name = "IMG")
    private String img;

    @Column(name = "EXPIRATION_DATE")
    private LocalDate expirationDate;

    @JsonIgnore
    @ManyToMany(mappedBy = "productos")
    private List<Model_Pedido> pedidos;

    public Model_Producto(int id, String nombre, int precio, int stock, String img, LocalDate expirationDate, List<Model_Pedido> pedidos) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.img = img;
        this.expirationDate = expirationDate;
        this.pedidos = pedidos;
    }

    public Model_Producto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public List<Model_Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Model_Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}

