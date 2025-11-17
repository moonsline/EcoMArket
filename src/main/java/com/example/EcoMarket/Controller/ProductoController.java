package com.example.EcoMarket.Controller;


import com.example.EcoMarket.Assemblers.ProductoModelAssembler;
import com.example.EcoMarket.Model.Model_Producto;
import com.example.EcoMarket.Service.ProductoService;
import com.example.EcoMarket.hateoas.ProductoModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Obtener todos los productos", description = "Devuelve una lista de todos los productos registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de productos obtenida correctamente"),
            @ApiResponse(responseCode = "404", description = "Lista de productos no encontrado")

    })
    @GetMapping
    public CollectionModel<EntityModel<ProductoModel>> getAllProductos() {
        return CollectionModel.of(service.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList()));
    }

    @Operation(summary = "Obtener producto por ID", description = "Devuelve un producto específico según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/{id}")
    public EntityModel<ProductoModel> getProductoById(@PathVariable int id) {
        return assembler.toModel(service.obtenerPorId(id));
    }

    @Operation(summary = "Crear un nuevo producto", description = "Crea un producto con los datos proporcionados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")

    })
    @PostMapping
    public EntityModel<ProductoModel> crear(@RequestBody Model_Producto p) {
        return assembler.toModel(service.agregar(p));
    }

    @Operation(summary = "Actualizar un producto", description = "Actualiza los datos de un producto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PutMapping("/{id}")
    public EntityModel<ProductoModel> actualizar(@PathVariable int id, @RequestBody Model_Producto p) {
        return assembler.toModel(service.actualizar(id, p));
    }

    @Operation(summary = "Eliminar un producto", description = "Elimina un producto según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id) {
        return service.eliminar(id);
    }
}
