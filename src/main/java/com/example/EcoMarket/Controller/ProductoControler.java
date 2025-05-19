package com.example.EcoMarket.Controller;


import com.example.EcoMarket.Model.Model_Producto;
import com.example.EcoMarket.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoControler {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Model_Producto> obtenerProducto(){
        return productoService.getProductos();
    }

    @PostMapping
    public Model_Producto guardarProducto(@RequestBody Model_Producto producto){
        return productoService.saveProducto(producto);
    }

    @GetMapping("/{id}")
    public Model_Producto buscarId(@PathVariable int id){
        return productoService.getProductoId(id);
    }

    @PutMapping("/{id}")
    public Model_Producto modificarProducto(@PathVariable int id, @RequestBody Model_Producto producto){
        return productoService.updateProducto(producto);
    }

    @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable int id){
        return productoService.deleteProducto(id);
    }

}
