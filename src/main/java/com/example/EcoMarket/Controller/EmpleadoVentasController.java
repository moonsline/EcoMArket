package com.example.EcoMarket.Controller;

import com.example.EcoMarket.Model.Model_EmpleadoVentas;
import com.example.EcoMarket.Service.EmpleadoVentasService;
import com.example.EcoMarket.Assemblers.EmpleadoVentasModelAssembler;
import com.example.EcoMarket.hateoas.EmpleadoVentasModel;

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
@RequestMapping("/empleados-ventas")
@Tag(name = "Controlador EmpleadoVentas", description = "Servicio de gestión de empleados del área de ventas")
public class EmpleadoVentasController {

    @Autowired
    private EmpleadoVentasService service;

    @Autowired
    private EmpleadoVentasModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener todos los empleados de ventas", description = "Lista todos los empleados que desempeñan funciones de ventas")
    @ApiResponse(responseCode = "200", description = "Consulta exitosa")
    @ApiResponse(responseCode = "404", description = "Consulta no encontrado")

    public CollectionModel<EntityModel<EmpleadoVentasModel>> getAllEmpleadosVentas() {
        List<EntityModel<EmpleadoVentasModel>> lista = service.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(lista,
                linkTo(methodOn(EmpleadoVentasController.class).getAllEmpleadosVentas()).withSelfRel());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener empleado de ventas por ID", description = "Devuelve los datos de un empleado de ventas según su ID")
    @ApiResponse(responseCode = "200", description = "Empleado encontrado")
    @ApiResponse(responseCode = "404", description = "Empleado no encontrado")

    public EntityModel<EmpleadoVentasModel> getEmpleadoVentasById(@PathVariable int id) {
        return assembler.toModel(service.obtenerPorId(id));
    }

    @PostMapping
    @Operation(summary = "Agregar nuevo empleado de ventas", description = "Registra un nuevo empleado para ventas")
    @ApiResponse(responseCode = "200", description = "Empleado agregado correctamente")
    @ApiResponse(responseCode = "404", description = "Empleado no encontrado")

    public EntityModel<EmpleadoVentasModel> crear(@RequestBody Model_EmpleadoVentas nuevo) {
        return assembler.toModel(service.agregar(nuevo));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar empleado de ventas por ID", description = "Modifica los datos de un empleado existente")
    @ApiResponse(responseCode = "200", description = "Empleado actualizado correctamente")
    @ApiResponse(responseCode = "404", description = "Empleado no encontrado")

    public EntityModel<EmpleadoVentasModel> actualizar(@PathVariable int id, @RequestBody Model_EmpleadoVentas nuevo) {
        return assembler.toModel(service.actualizar(id, nuevo));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar empleado de ventas por ID", description = "Elimina un empleado del sistema")
    @ApiResponse(responseCode = "200", description = "Empleado eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "Empleado no encontrado")

    public String eliminar(@PathVariable int id) {
        return service.eliminar(id);
    }
}
