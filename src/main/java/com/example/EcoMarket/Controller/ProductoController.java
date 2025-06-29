package com.example.EcoMarket.Controller;


import com.example.EcoMarket.Assemblers.ProductoModelAssembler;
import com.example.EcoMarket.Model.Model_Producto;
import com.example.EcoMarket.Service.ProductoService;
import com.example.EcoMarket.hateoas.ProductoModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;


@RestController
@RequestMapping("/productos")
@Tag(name = "Controlador Producto", description = "Gestión de productos")
public class ProductoController {

    @Autowired
    private ProductoService service;
    @Autowired
    private ProductoModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener productos")
    public CollectionModel<EntityModel<ProductoModel>> getAllProductos() {
        return CollectionModel.of(service.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public EntityModel<ProductoModel> getProductoById(@PathVariable int id) {
        return assembler.toModel(service.obtenerPorId(id));
    }

    @PostMapping
    public EntityModel<ProductoModel> crear(@RequestBody Model_Producto p) {
        return assembler.toModel(service.agregar(p));
    }

    @PutMapping("/{id}")
    public EntityModel<ProductoModel> actualizar(@PathVariable int id, @RequestBody Model_Producto p) {
        return assembler.toModel(service.actualizar(id, p));
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id) {
        return service.eliminar(id);
    }
}
