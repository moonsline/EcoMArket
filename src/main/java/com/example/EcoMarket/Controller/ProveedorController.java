package com.example.EcoMarket.Controller;

import com.example.EcoMarket.Model.Model_Proveedor;
import com.example.EcoMarket.Service.ProveedorService;
import com.example.EcoMarket.hateoas.ProveedorModel;
import com.example.EcoMarket.Assemblers.ProveedorModelAssembler;

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
@RequestMapping("/proveedores")
@Tag(name="Controlador Proveedor", description="Servicio de gestion de proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private ProveedorModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener todos los proveedores", description = "Devuelve una lista con todos los proveedores registrados")
    @ApiResponse(responseCode = "200", description = "Consulta exitosa")
    public CollectionModel<EntityModel<ProveedorModel>> getProveedores() {
        List<Model_Proveedor> proveedores = proveedorService.obtenerTodos();

        List<EntityModel<ProveedorModel>> proveedorModels = proveedores.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(proveedorModels,
                linkTo(methodOn(ProveedorController.class).getProveedores()).withSelfRel());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener proveedor por ID", description = "Devuelve un proveedor específico según su ID")
    @ApiResponse(responseCode = "200", description = "Proveedor encontrado")
    public EntityModel<ProveedorModel> getProveedoreById(@PathVariable int id) {
        Model_Proveedor proveedor = proveedorService.obtenerPorId(id);
        return assembler.toModel(proveedor);
    }

    @PostMapping
    @Operation(summary = "Agregar nuevo proveedor", description = "Agrega un nuevo proveedor al sistema")
    @ApiResponse(responseCode = "200", description = "Proveedor agregado correctamente")
    public EntityModel<ProveedorModel> postProveedores(@RequestBody Model_Proveedor proveedor) {
        Model_Proveedor nuevo = proveedorService.agregarProveedor(proveedor);
        return assembler.toModel(nuevo);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar proveedor por ID", description = "Elimina un proveedor del sistema según su ID")
    @ApiResponse(responseCode = "200", description = "Proveedor eliminado correctamente")
    public String deleteProveedoreById(@PathVariable int id) {
        return proveedorService.eliminarProveedor(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar proveedor por ID", description = "Actualiza los datos de un proveedor ya existente")
    @ApiResponse(responseCode = "200", description = "Proveedor actualizado correctamente")
    public EntityModel<ProveedorModel> updateProveedor(@PathVariable int id, @RequestBody Model_Proveedor proveedor) {
        Model_Proveedor actualizado = proveedorService.actualizarProveedor(id, proveedor);
        return assembler.toModel(actualizado);
    }
}
