package com.example.EcoMarket.Modelo;
import java.util.List;
public class Modelo_Cliente extends Modelo_Usuario {

    private String direccion;
    private List<String> historialPedidos;

    public Modelo_Cliente() {
    }

    public Modelo_Cliente(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<String> getHistorialPedidos() {
        return historialPedidos;
    }

    public void setHistorialPedidos(List<String> historialPedidos) {
        this.historialPedidos = historialPedidos;
    }

    public void agregarPedido(String pedido) {
        historialPedidos.add(pedido);
    }

    public void historialPedidos() {
        if (historialPedidos.isEmpty()) {
            System.out.println("El historial de pedidos está vacío.");
        } else {
            System.out.println("Historial de Pedidos de " + getNombre() + ":");
            for (String pedido : historialPedidos) {
                System.out.println("- " + pedido);
            }
        }
    }
}