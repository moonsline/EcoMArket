package com.example.EcoMarket.Controller;


import com.example.EcoMarket.Assemblers.PedidoModelAssembler;
import com.example.EcoMarket.Model.Model_Pedido;
import com.example.EcoMarket.Repository.PedidoRepository;
import com.example.EcoMarket.Service.PedidoService;
import com.example.EcoMarket.hateoas.PedidoModel;
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
@RequestMapping("/pedidos")
@Tag(name = "Controlador Pedido", description = "Gestión de pedidos y asignación de productos")
public class PedidoController {

    @Autowired
    private PedidoService service;
    @Autowired
    private PedidoModelAssembler assembler;

    @Operation(summary = "Obtener todos los pedidos", description = "Devuelve una lista de todos los pedidos registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pedidos obtenida correctamente")
    })
    @GetMapping
    public CollectionModel<EntityModel<PedidoModel>> getAllPedidos() {
        return CollectionModel.of(service.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList()));
    }

    @Operation(summary = "Obtener pedido por ID", description = "Devuelve un pedido específico según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido encontrado"),
            @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    @GetMapping("/{id}")
    public EntityModel<PedidoModel> getPedidoById(@PathVariable int id) {
        return assembler.toModel(service.obtenerPorId(id));
    }

    @Operation(summary = "Crear un nuevo pedido", description = "Crea un pedido con los datos proporcionados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido creado correctamente"),
            @ApiResponse(responseCode = "404", description = "Pedido no encontrado")

    })
    @PostMapping
    public EntityModel<PedidoModel> crear(@RequestBody Model_Pedido p) {
        return assembler.toModel(service.agregar(p));
    }
    @Operation(summary = "Actualizar un pedido", description = "Actualiza los datos de un pedido existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    @PutMapping("/{id}")
    public EntityModel<PedidoModel> actualizar(@PathVariable int id, @RequestBody Model_Pedido p) {
        return assembler.toModel(service.actualizar(id, p));
    }
    @Operation(summary = "Eliminar un pedido", description = "Elimina un pedido según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id) {
        return service.eliminar(id);
    }
    @Operation(summary = "Agregar producto a pedido", description = "Asocia un producto existente a un pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto agregado al pedido correctamente"),
            @ApiResponse(responseCode = "404", description = "Pedido o producto no encontrado")
    })
    @PostMapping("/{idPedido}/productos/{idProducto}")
    public EntityModel<PedidoModel> agregarProducto(@PathVariable int idPedido, @PathVariable int idProducto) {
        return assembler.toModel(service.agregarProducto(idPedido, idProducto));
    }
    @Operation(summary = "Quitar producto de pedido", description = "Elimina la asociación de un producto a un pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto quitado del pedido correctamente"),
            @ApiResponse(responseCode = "404", description = "Pedido o producto no encontrado")
    })
    @DeleteMapping("/{idPedido}/productos/{idProducto}")
    public EntityModel<PedidoModel> quitarProducto(@PathVariable int idPedido, @PathVariable int idProducto) {
        return assembler.toModel(service.quitarProducto(idPedido, idProducto));
    }
}
