package com.example.EcoMarket.Repository;

import com.example.EcoMarket.Model.Model_Producto;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Repository

public class ProductoRepository {

    private List<Model_Producto> productos = new ArrayList<>();

    public List<Model_Producto> obtenerProducto(){
        return productos;
    }

    public Model_Producto buscarId(int id){
        for (Model_Producto producto: productos) {
            if (producto.getId()==id){
                return producto;
            }
        }
        return null;
    }

    public Model_Producto agregarProducto(Model_Producto producto){
        productos.add(producto);
        return producto;
    }

    public Model_Producto actualizarProducto(Model_Producto producto) {
        int idProducto = 0;
        int idPosicion = 0;
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId() == producto.getId()) {
                idProducto = productos.get(i).getId();
                idPosicion = i;

            }
        }

        Model_Producto productoActualizado = new Model_Producto();
        productoActualizado.setId(idProducto);
        productoActualizado.setNombre(producto.getNombre());
        productoActualizado.setPrecio(producto.getPrecio());
        productoActualizado.setStock(producto.getStock());

        productos.set(idPosicion, productoActualizado);
        return productoActualizado;
    }

    public String eliminarProducto(int id){
        Model_Producto producto = buscarId(id);
        if (producto != null) {
            productos.remove(producto);
            return "Producto eliminado con éxito";
        }else{
                return "Producto no encontrado";
            }
    }
}
