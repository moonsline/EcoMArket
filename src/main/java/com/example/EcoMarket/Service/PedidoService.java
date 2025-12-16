package com.example.EcoMarket.Service;

import com.example.EcoMarket.Model.Model_Pedido;
import com.example.EcoMarket.Model.Model_Producto;
import com.example.EcoMarket.Repository.PedidoRepository;
import com.example.EcoMarket.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepo;

    @Autowired
    private ProductoRepository productoRepo;

    // Obtener todos los pedidos
    public List<Model_Pedido> obtenerTodos() {
        return pedidoRepo.findAll();
    }

    // Obtener pedido por ID
    public Model_Pedido obtenerPorId(int id) {
        return pedidoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }

    // Crear nuevo pedido
    public Model_Pedido agregar(Model_Pedido p) {

        // Asegurar que la lista de productos no sea null
        if (p.getProductos() == null) {
            p.setProductos(new ArrayList<>());
        }

        return pedidoRepo.save(p);
    }

    // Actualizar pedido
    public Model_Pedido actualizar(int id, Model_Pedido p) {

        Model_Pedido actual = obtenerPorId(id);

        actual.setFecha(p.getFecha());
        actual.setEstado(p.getEstado());
        actual.setTotal(p.getTotal());

        return pedidoRepo.save(actual);
    }

    // Eliminar pedido
    public String eliminar(int id) {
        // Obtenemos el pedido; si no existe, lanza excepción
        Model_Pedido pedido = obtenerPorId(id);

        // Limpiamos los productos asociados antes de eliminar
        if (pedido.getProductos() != null && !pedido.getProductos().isEmpty()) {
            pedido.getProductos().clear();
            pedidoRepo.save(pedido);  // Guardamos los cambios en la BD
        }

        // Ahora eliminamos el pedido
        pedidoRepo.deleteById(id);
        return "Pedido eliminado";
    }


    // Agregar producto a pedido (ManyToMany)
    public Model_Pedido agregarProducto(int idPedido, int idProducto) {

        Model_Pedido pedido = obtenerPorId(idPedido);
        Model_Producto producto = productoRepo.findById(idProducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Evitar null en lista
        if (pedido.getProductos() == null) {
            pedido.setProductos(new ArrayList<>());
        }

        pedido.getProductos().add(producto);
        return pedidoRepo.save(pedido);
    }

    // Quitar producto de pedido
    public Model_Pedido quitarProducto(int idPedido, int idProducto) {

        Model_Pedido pedido = obtenerPorId(idPedido);

        if (pedido.getProductos() != null) {
            pedido.getProductos().removeIf(p -> p.getId() == idProducto);
        }

        return pedidoRepo.save(pedido);
    }
}
