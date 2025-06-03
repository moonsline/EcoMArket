package com.example.EcoMarket.Controller;


import com.example.EcoMarket.Model.Model_Producto;
import com.example.EcoMarket.Service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/productos")
@Tag(name="Controlador Producto", description= "Servicio de gestion de producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    @Operation(summary = "Obtener productos", description="Obtiene la lista de productos")
    public String getPoducto(){
        return productoService.listaProducto();
    }

    @PostMapping
    @Operation(summary = "Agregar productos", description="Agrega productos a la lista")
    public String postProductos(@RequestBody Model_Producto producto){
        return productoService.agregarProducto(producto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener productos con su id", description="Obtiene producto buscando su id")
    public String getProductoById(@PathVariable int id){
        return productoService.obtenerProducto(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina productos", description="Elimina productos de la lista")
    public String deleteProductosById(@PathVariable int id){
        return productoService.eliminarProducto(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Elimina producto con su id", description="Elimina producto buscando su id")
    public String updateProductoById(@PathVariable int id, @RequestBody Model_Producto producto){
        return productoService.actualizarProducto(id, producto);
    }

}
