package com.example.EcoMarket.Controller;

import com.example.EcoMarket.Model.Model_Cliente;
import com.example.EcoMarket.Service.ClienteService;
import com.example.EcoMarket.Assemblers.ClienteModelAssembler;
import com.example.EcoMarket.hateoas.ClienteModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Controlador Cliente", description = "Servicio de gestión de clientes del sistema EcoMarket")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener todos los clientes", description = "Retorna una lista de todos los clientes registrados")
    @ApiResponse(responseCode = "200", description = "Consulta exitosa")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")

    public CollectionModel<EntityModel<ClienteModel>> getAllClientes() {
        List<EntityModel<ClienteModel>> clientes = clienteService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(clientes,
                linkTo(methodOn(ClienteController.class).getAllClientes()).withSelfRel());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener cliente por ID", description = "Devuelve los datos de un cliente específico")
    @ApiResponse(responseCode = "200", description = "Cliente encontrado")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")

    public EntityModel<ClienteModel> getClienteById(@PathVariable int id) {
        return assembler.toModel(clienteService.obtenerPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crear nuevo cliente", description = "Agrega un nuevo cliente al sistema")
    @ApiResponse(responseCode = "200", description = "Cliente creado exitosamente")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")

    public EntityModel<ClienteModel> crearCliente(@RequestBody Model_Cliente cliente) {
        return assembler.toModel(clienteService.agregarCliente(cliente));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar cliente por ID", description = "Modifica los datos de un cliente existente")
    @ApiResponse(responseCode = "200", description = "Cliente actualizado correctamente")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")

    public EntityModel<ClienteModel> actualizarCliente(@PathVariable int id, @RequestBody Model_Cliente cliente) {
        return assembler.toModel(clienteService.actualizarCliente(id, cliente));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar cliente por ID", description = "Elimina un cliente del sistema")
    @ApiResponse(responseCode = "200", description = "Cliente eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")

    public String eliminarCliente(@PathVariable int id) {
        return clienteService.eliminarCliente(id);
    }
}
