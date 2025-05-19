package com.example.EcoMarket.Service;

import com.example.EcoMarket.Model.Model_Pedido;
import com.example.EcoMarket.Repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    public String agregarPedido(Model_Pedido pedido){
        pedidoRepository.save(pedido);
        return "Pedido agregado con exito!";
    }

    public String listarPedidos(){
        StringBuilder output = new StringBuilder();
        for(Model_Pedido pedido : pedidoRepository.findAll()){
            output.append("ID pedido: ").append(pedido.getIdPedido()).append("\n");
            output.append("Fecha: ").append(pedido.getFecha()).append("\n");
            output.append("Estado: ").append(pedido.getEstado()).append("\n");
            output.append("Total: ").append(pedido.getTotal()).append("\n");
            output.append("Productos: ").append(pedido.getProductos());
        }
        return output.isEmpty() ? "No se encontraron pedidos" : output.toString();
    }

    public String obtenerPedido(int id){
        if(pedidoRepository.existsById(id)){
            Model_Pedido pedido = pedidoRepository.findById(id).get();
            StringBuilder output = new StringBuilder();
            output.append("ID pedido: ").append(pedido.getIdPedido()).append("\n");
            output.append("Fecha: ").append(pedido.getFecha()).append("\n");
            output.append("Estado: ").append(pedido.getEstado()).append("\n");
            output.append("Total: ").append(pedido.getTotal()).append("\n");
            output.append("Productos: ").append(pedido.getProductos());
            return output.toString();
        }else{
            return "Pedido no encontrado";
        }
    }

    public String eliminarPedido(int id){
        if(pedidoRepository.existsById(id)){
            pedidoRepository.deleteById(id);
            return "Pedido eliminado con exito!";
        }else {
            return "Pedido no encontrado";
        }
    }

    public String actualizarPedido(int id, Model_Pedido pedido){
        if(pedidoRepository.existsById(id)){
            Model_Pedido buscado = pedidoRepository.findById(id).get();
            buscado.setFecha(pedido.getFecha());
            buscado.setEstado(pedido.getEstado());
            buscado.setTotal(pedido.getTotal());
            buscado.setProductos(pedido.getProductos());
            pedidoRepository.save(buscado);
            return "Pedido actualizado con exito!";
        }else{
            return "Pedido no encontrado";
        }
    }
}
