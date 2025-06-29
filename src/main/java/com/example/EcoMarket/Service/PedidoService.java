package com.example.EcoMarket.Service;

import com.example.EcoMarket.Model.Model_Pedido;
import com.example.EcoMarket.Model.Model_Producto;
import com.example.EcoMarket.Repository.PedidoRepository;
import com.example.EcoMarket.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepo;
    @Autowired
    private ProductoRepository productoRepo;

    public List<Model_Pedido> obtenerTodos() { return pedidoRepo.findAll(); }

    public Model_Pedido obtenerPorId(int id) {
        return pedidoRepo.findById(id).orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    public Model_Pedido agregar(Model_Pedido p) { return pedidoRepo.save(p); }

    public Model_Pedido actualizar(int id, Model_Pedido p) {
        Model_Pedido actual = obtenerPorId(id);
        actual.setFecha(p.getFecha());
        actual.setEstado(p.getEstado());
        actual.setTotal(p.getTotal());
        return pedidoRepo.save(actual);
    }

    public String eliminar(int id) {
        if (pedidoRepo.existsById(id)) {
            pedidoRepo.deleteById(id);
            return "Pedido eliminado";
        } else return "No encontrado";
    }

    public Model_Pedido agregarProducto(int idPedido, int idProducto) {
        Model_Pedido pedido = obtenerPorId(idPedido);
        Model_Producto producto = productoRepo.findById(idProducto).orElseThrow();
        pedido.getProductos().add(producto);
        return pedidoRepo.save(pedido);
    }

    public Model_Pedido quitarProducto(int idPedido, int idProducto) {
        Model_Pedido pedido = obtenerPorId(idPedido);
        pedido.getProductos().removeIf(p -> p.getId() == idProducto);
        return pedidoRepo.save(pedido);
    }
}

