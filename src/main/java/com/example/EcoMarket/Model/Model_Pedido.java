package com.example.EcoMarket.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Model_Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPedido;
    private Date fecha;
    private String estado;
    private float total;

    @ManyToMany
    @JoinTable(
            name = "pedido_producto", //Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name="pedido_id"),
            inverseJoinColumns = @JoinColumn(name="producto_id")
    )
    private List<Model_Producto> productos;




}
