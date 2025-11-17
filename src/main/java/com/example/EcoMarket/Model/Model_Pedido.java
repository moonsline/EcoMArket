package com.example.EcoMarket.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entidad Pedido.
 * Representa un pedido realizado por un cliente.
 * Uso @Entity para que JPA mapee esta clase con la tabla correspondiente en la BD.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MODEL_PEDIDO")
public class Model_Pedido {

    /**
     * ID autogenerado.
     * Uso IDENTITY porque nuestra base Oracle ya tiene el trigger + secuencia en el DDL.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PEDIDO")
    private int idPedido;

    /**
     * Fecha en que se generó el pedido.
     * Uso LocalDateTime para guardar fecha y hora.
     */
    @Column(name = "FECHA")
    private LocalDateTime fecha;

    /**
     * Estado del pedido (ej: "PENDIENTE", "ENVIADO", "ENTREGADO").
     */
    @Column(name = "ESTADO")
    private String estado;

    /**
     * Total del pedido.
     * Uso BigDecimal para evitar problemas de precisión en montos.
     */
    @Column(name = "TOTAL")
    private BigDecimal total;

    /**
     * Relación ManyToMany con productos.
     * Un pedido puede tener varios productos y un producto puede estar en varios pedidos.
     * La anotación @JsonIgnore evita recursividad al convertir a JSON.
     */
    @ManyToMany
    @JoinTable(
            name = "PEDIDO_PRODUCTO",
            joinColumns = @JoinColumn(name = "PEDIDO_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCTO_ID")
    )

    private List<Model_Producto> productos;
}
