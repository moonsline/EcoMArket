package com.example.EcoMarket.Service;

import com.example.EcoMarket.Model.Model_Producto;
import com.example.EcoMarket.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Model_Producto> getProductos(){
        return productoRepository.obtenerProducto();
    }

    public Model_Producto saveProducto(Model_Producto producto) {
        return productoRepository.agregarProducto(producto);
    }

    public Model_Producto getProductoId(int id){
        return productoRepository.buscarId(id);
    }

    public Model_Producto updateProducto(Model_Producto producto){
        return productoRepository.actualizarProducto(producto);
    }

    public String deleteProducto (int id){
        return productoRepository.eliminarProducto(id);
    }

}
