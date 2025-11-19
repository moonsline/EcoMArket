package com.example.EcoMarket.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

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
}

