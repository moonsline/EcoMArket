package com.example.EcoMarket.Controller;


import com.example.EcoMarket.Model.Model_Producto;
import com.example.EcoMarket.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD


@RestController
@RequestMapping("/productos")
=======
@RestController
@RequestMapping("/api/v1/productos")
>>>>>>> 8f37e31321f6bc47641547f57db79cd41bf425ca
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public String getPoducto(){
        return productoService.listaProducto();
    }
    @PostMapping
    public String postProductos(@RequestBody Model_Producto producto){
        return productoService.agregarProducto(producto);
    }

    @GetMapping("/{id}")
    public String getProductoById(@PathVariable int id){
        return productoService.obtenerProducto(id);
    }

    @DeleteMapping("/{id}")
    public String deleteProductosById(@PathVariable int id){
        return productoService.eliminarProducto(id);
    }

    @PutMapping("/{id}")
    public String updateProductoById(@PathVariable int id, @RequestBody Model_Producto producto){
        return productoService.actualizarProducto(id, producto);
    }

}
