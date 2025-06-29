package com.example.EcoMarket.hateoas;

import com.example.EcoMarket.Model.Model_Pedido;
import com.example.EcoMarket.Model.Model_Producto;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoModel extends RepresentationModel<PedidoModel> {

    private int idPedido;
    private Date fecha;
    private String estado;
    private float total;
    private List<Integer> productosIds;

    public PedidoModel(Model_Pedido pedido) {
        this.idPedido = pedido.getIdPedido();
        this.fecha = pedido.getFecha();
        this.estado = pedido.getEstado();
        this.total = pedido.getTotal();
        this.productosIds = pedido.getProductos().stream()
                .map(Model_Producto::getId)
                .collect(Collectors.toList());
    }

    public int getIdPedido() {
        return idPedido;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public float getTotal() {
        return total;
    }

    public List<Integer> getProductosIds() {
        return productosIds;
    }
}
