package com.example.EcoMarket.Controller;

import com.example.EcoMarket.Assemblers.PedidoModelAssembler;
import com.example.EcoMarket.Model.Model_Pedido;
import com.example.EcoMarket.Service.PedidoService;
import com.example.EcoMarket.hateoas.PedidoModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Controlador encargado de manejar las operaciones CRUD de los pedidos.
 * Utiliza HATEOAS para devolver enlaces navegables dentro de las respuestas.
 */
@RestController
@RequestMapping("/pedidos")
@Tag(name = "Controlador Pedido", description = "Gestión de pedidos y asignación de productos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @Autowired
    private PedidoModelAssembler assembler;

    // -------------------------------------------------------------------------
    // GET: Obtener todos los pedidos
    // -------------------------------------------------------------------------
    @Operation(summary = "Obtener todos los pedidos", description = "Devuelve la lista completa de pedidos")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'LOGISTICA')")
    public CollectionModel<EntityModel<PedidoModel>> getAllPedidos() {

        var pedidos = service.obtenerTodos()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        // Agrego un link al mismo endpoint como buena práctica HATEOAS
        return CollectionModel.of(pedidos)
                .add(linkTo(methodOn(PedidoController.class).getAllPedidos()).withSelfRel());
    }

    // -------------------------------------------------------------------------
    // GET: Obtener pedido por ID
    // -------------------------------------------------------------------------
    @Operation(summary = "Obtener pedido por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pedido encontrado"),
            @ApiResponse(responseCode = "404", description = "No existe el pedido")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'LOGISTICA')")
    public EntityModel<PedidoModel> getPedidoById(@PathVariable int id) {
        return assembler.toModel(service.obtenerPorId(id));
    }

    // -------------------------------------------------------------------------
    // POST: Crear un nuevo pedido
    // -------------------------------------------------------------------------
    @Operation(summary = "Crear un pedido nuevo")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pedido creado correctamente")
    })
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'LOGISTICA')")
    public ResponseEntity<EntityModel<PedidoModel>> crear(@RequestBody Model_Pedido p) {

        var pedidoGuardado = service.agregar(p);
        var entityModel = assembler.toModel(pedidoGuardado);

        // Devuelvo código 201 Created + ubicación del recurso creado
        return ResponseEntity
                .created(entityModel.getRequiredLink("self").toUri())
                .body(entityModel);
    }

    // -------------------------------------------------------------------------
    // PUT: Actualizar un pedido existente
    // -------------------------------------------------------------------------
    @Operation(summary = "Actualizar un pedido existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pedido actualizado"),
            @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'LOGISTICA')")
    public ResponseEntity<EntityModel<PedidoModel>> actualizar(@PathVariable int id,
                                                               @RequestBody Model_Pedido p) {

        var pedidoActualizado = service.actualizar(id, p);
        return ResponseEntity.ok(assembler.toModel(pedidoActualizado));
    }

    // -------------------------------------------------------------------------
    // DELETE: Eliminar un pedido
    // -------------------------------------------------------------------------
    @Operation(summary = "Eliminar pedido por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Pedido eliminado"),
            @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'LOGISTICA')")
    public ResponseEntity<?> eliminar(@PathVariable int id) {

        service.eliminar(id);
        // En REST es correcto devolver 204 No Content al eliminar
        return ResponseEntity.noContent().build();
    }

    // -------------------------------------------------------------------------
    // POST: Agregar producto a un pedido
    // -------------------------------------------------------------------------
    @Operation(summary = "Agregar producto a un pedido existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto agregado"),
            @ApiResponse(responseCode = "404", description = "Pedido o producto no encontrado")
    })
    @PostMapping("/{idPedido}/productos/{idProducto}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'LOGISTICA')")
    public ResponseEntity<EntityModel<PedidoModel>> agregarProducto(@PathVariable int idPedido,
                                                                    @PathVariable int idProducto) {

        var actualizado = service.agregarProducto(idPedido, idProducto);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    // -------------------------------------------------------------------------
    // DELETE: Quitar producto de pedido
    // -------------------------------------------------------------------------
    @Operation(summary = "Quitar producto de un pedido")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto quitado"),
            @ApiResponse(responseCode = "404", description = "Pedido o producto no encontrado")
    })
    @DeleteMapping("/{idPedido}/productos/{idProducto}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'LOGISTICA')")
    public ResponseEntity<EntityModel<PedidoModel>> quitarProducto(@PathVariable int idPedido,
                                                                   @PathVariable int idProducto) {

        var actualizado = service.quitarProducto(idPedido, idProducto);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }
}
