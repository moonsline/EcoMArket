package com.example.EcoMarket.Controller;

import com.example.EcoMarket.Model.Model_EmpleadoVentas;
import com.example.EcoMarket.Service.EmpleadoVentasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empleados")
@Tag(name = "Contolador Empleado", description = "Servicio de gestión de empleados")
public class EmpleadoVentasController {

    @Autowired
    private EmpleadoVentasService empleadoVentasService;


     @GetMapping
     @Operation(summary = "Obtener EmpleadoVentas", description = "Obtiene la lista de los EmpleadoVentas existentes")
     @ApiResponse(responseCode = "200", description = "¡Consulta Exitosa!")
    public String getEmpleadoVentas() { return empleadoVentasService.listarEmpleadoVentas();}

     @PostMapping
     @Operation(summary = "Obtener EmpleadoVentas", description = "Obtiene la lista de los EmpleadoVentas existentes")
    public String postEmpleadoVentas(@RequestBody Model_EmpleadoVentas empleadoVentas) {return empleadoVentasService.agregarEmpleadoVentas(empleadoVentas);}

    @GetMapping("/{idEmpleado}")
    @Operation(summary = "Obtener EmpleadoVentas por su id", description="Obtiene la EmpleadoVentas de ventas por su id")
    public String getEmpleadoVentasById(@PathVariable int idEmpleado) {
        return empleadoVentasService.obtenerEmpleadoVentas(idEmpleado);
    }

    @DeleteMapping("/{idEmpleado}")
    @Operation(summary = "Elimina EmpleadoVentas", description="Elimina EmpleadoVentas de la lista")
    public String deleteEmpleadoVentasById(@PathVariable int idEmpleado) {
        return empleadoVentasService.eliminarEmpleadoVentas(idEmpleado);
    }

    @PutMapping("/{idEmpleado}")
    @Operation(summary = "Elimina EmpleadoVentas con su id", description="Elimina EmpleadoVentas buscando su id")
    public String upddateEmpleadoVentasById(@PathVariable int idEmpleado, @RequestBody Model_EmpleadoVentas empleadoVentas) {
        return empleadoVentasService.actualizarEmpleadoVentas(idEmpleado, empleadoVentas);
    }


}
