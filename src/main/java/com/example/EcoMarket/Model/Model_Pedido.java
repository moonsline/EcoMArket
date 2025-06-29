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

// Tanto en pedido como en producto tendremos que hacer una migración para que sea compatible con hateoas
// averiguando me di cuenta qué si solo dejamos la lista de productos como un atributo en pedido, podría caer en loops infinitos en que los id
// de productos a pedidos y como tienen una relación de muchos a muchos, podría ser problemáticos.
// parece que la solución más viable es crear enlaces de HATEOAS para que a partir de un pedido se dirija a los productos que le pertenecen.
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
