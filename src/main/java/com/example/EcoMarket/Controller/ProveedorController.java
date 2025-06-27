package com.example.EcoMarket.Controller;

import com.example.EcoMarket.Model.Model_Proveedor;
import com.example.EcoMarket.Service.ProveedorService;
import com.example.EcoMarket.hateoas.ProveedorModel;
import com.example.EcoMarket.Assemblers.ProveedorModelAssembler;

import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Obtener proveedores", description="Obtiene la lista de proveedores")
    public CollectionModel<EntityModel<ProveedorModel>> getProveedores() {
        List<Model_Proveedor> proveedores = proveedorService.obtenerTodos();

        List<EntityModel<ProveedorModel>> proveedorModels = proveedores.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(proveedorModels,
                linkTo(methodOn(ProveedorController.class).getProveedores()).withSelfRel());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener proveedor por ID", description="Obtiene proveedor buscando su ID")
    public EntityModel<ProveedorModel> getProveedoreById(@PathVariable int id) {
        Model_Proveedor proveedor = proveedorService.obtenerPorId(id);
        return assembler.toModel(proveedor);
    }

    @PostMapping
    @Operation(summary = "Agregar proveedor", description="Agrega un proveedor a la lista")
    public EntityModel<ProveedorModel> postProveedores(@RequestBody Model_Proveedor proveedor) {
        Model_Proveedor nuevo = proveedorService.agregarProveedor(proveedor);
        return assembler.toModel(nuevo);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar proveedor", description="Elimina un proveedor por su ID")
    public String deleteProveedoreById(@PathVariable int id) {
        return proveedorService.eliminarProveedor(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar proveedor", description="Actualiza proveedor por ID")
    public EntityModel<ProveedorModel> updateProveedor(@PathVariable int id, @RequestBody Model_Proveedor proveedor) {
        Model_Proveedor actualizado = proveedorService.actualizarProveedor(id, proveedor);
        return assembler.toModel(actualizado);
    }
}
