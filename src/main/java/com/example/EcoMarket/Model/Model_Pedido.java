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

    public Model_Pedido(int idPedido, LocalDateTime fecha, String estado, BigDecimal total, List<Model_Producto> productos) {
        this.idPedido = idPedido;
        this.fecha = fecha;
        this.estado = estado;
        this.total = total;
        this.productos = productos;
    }

    public Model_Pedido() {
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<Model_Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Model_Producto> productos) {
        this.productos = productos;
    }
}
