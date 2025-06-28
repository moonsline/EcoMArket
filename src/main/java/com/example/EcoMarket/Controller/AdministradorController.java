package com.example.EcoMarket.Controller;

import com.example.EcoMarket.Model.Model_Administrador;
import com.example.EcoMarket.Service.AdministradorService;
import com.example.EcoMarket.Assemblers.AdministradorModelAssembler;
import com.example.EcoMarket.hateoas.AdministradorModel;

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
@RequestMapping("/administradores")
@Tag(name = "Controlador Administrador", description = "Servicio de gestión de administradores del sistema")
public class AdministradorController {

    @Autowired
    private AdministradorService adminService;

    @Autowired
    private AdministradorModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener todos los administradores", description = "Retorna una lista de todos los administradores del sistema")
    @ApiResponse(responseCode = "200", description = "Consulta exitosa")
    public CollectionModel<EntityModel<AdministradorModel>> getAllAdministradores() {
        List<EntityModel<AdministradorModel>> lista = adminService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(lista,
                linkTo(methodOn(AdministradorController.class).getAllAdministradores()).withSelfRel());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener administrador por ID", description = "Retorna un administrador según su ID")
    @ApiResponse(responseCode = "200", description = "Administrador encontrado")
    public EntityModel<AdministradorModel> getAdministradorById(@PathVariable int id) {
        return assembler.toModel(adminService.obtenerPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crear nuevo administrador", description = "Agrega un nuevo administrador al sistema")
    @ApiResponse(responseCode = "200", description = "Administrador creado exitosamente")
    public EntityModel<AdministradorModel> crearAdministrador(@RequestBody Model_Administrador admin) {
        return assembler.toModel(adminService.agregarAdministrador(admin));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar administrador por ID", description = "Actualiza los datos de un administrador existente")
    @ApiResponse(responseCode = "200", description = "Administrador actualizado correctamente")
    public EntityModel<AdministradorModel> actualizarAdministrador(@PathVariable int id, @RequestBody Model_Administrador admin) {
        return assembler.toModel(adminService.actualizarAdministrador(id, admin));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar administrador por ID", description = "Elimina un administrador del sistema según su ID")
    @ApiResponse(responseCode = "200", description = "Administrador eliminado correctamente")
    public String eliminarAdministrador(@PathVariable int id) {
        return adminService.eliminarAdministrador(id);
    }
}

