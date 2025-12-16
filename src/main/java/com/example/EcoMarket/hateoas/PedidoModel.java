package com.example.EcoMarket.hateoas;

import com.example.EcoMarket.Model.Model_Pedido;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Modelo HATEOAS para representar un pedido.
 * Esta clase se usa para devolver una versión del pedido que incluye enlaces (links)
 * y no expone directamente la entidad JPA.
 */
public class PedidoModel extends RepresentationModel<PedidoModel> {

    private int idPedido;
    private LocalDateTime fecha;
    private String estado;
    private BigDecimal total;
    private List<ProductoModel> productos;

    public PedidoModel(Model_Pedido pedido) {

        // ID del pedido
        this.idPedido = pedido.getIdPedido();

        // Fecha del pedido (ya es LocalDateTime en el modelo JPA)
        this.fecha = pedido.getFecha();

        // Estado del pedido (ej: PENDIENTE, ENVIADO...)
        this.estado = pedido.getEstado();

        // Total del pedido (ya es BigDecimal en el modelo JPA)
        this.total = pedido.getTotal();

        // Conversión segura de la lista de productos → ProductoModel
        if (pedido.getProductos() != null) {
            this.productos = pedido.getProductos().stream()
                    .map(ProductoModel::new) // convierte entidad → HATEOAS model
                    .collect(Collectors.toList());
        } else {
            this.productos = List.of(); // lista vacía para evitar null
        }
    }

    public int getIdPedido() {
        return idPedido;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public List<ProductoModel> getProductos() {
        return productos;
    }
}
