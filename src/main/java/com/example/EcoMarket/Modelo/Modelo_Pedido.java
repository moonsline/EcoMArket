package com.example.EcoMarket.Modelo;
import java.util.Date;
import java.util.List;

public class Modelo_Pedido {
    private int idPedido;
    private Date fecha;
    private String estado;
    private float total;
    private List<String> productos;

    public Modelo_Pedido() {
    }

    public Modelo_Pedido(int idPedido, Date fecha, String estado, float total, List<String> productos) {
        this.idPedido = idPedido;
        this.fecha = fecha;
        this.estado = estado;
        this.total = total;
        this.productos = productos;

    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public List<String> getProductos() {
        return productos;
    }

    public void setProductos(List<String> productos) {
        this.productos = productos;
    }
    public void agregarProductos(String Producto) {
        productos.add(Producto);
    }

}
