package com.example.EcoMarket.Controller;

import com.example.EcoMarket.Model.Model_EmpleadoVentas;
import com.example.EcoMarket.Service.EmpleadoVentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empelado")
public class EmpleadoVentasController {

    @Autowired
    private EmpleadoVentasService empleadoVentasService;


     @GetMapping
    public String getEmpleadoVentas() { return empleadoVentasService.listarEmpleadoVentas();}

     @PostMapping
    public String postEmpleadoVentas(@RequestBody Model_EmpleadoVentas empleadoVentas) {return empleadoVentasService.agregarEmpleadoVentas(empleadoVentas);}

    @GetMapping("/{idEmpleado}")
    public String getEmpleadoVentasById(@PathVariable int idEmpleado) {
        return empleadoVentasService.obtenerEmpleadoVentas(idEmpleado);
    }

    @DeleteMapping("/{idEmpleado}")
    public String deleteEmpleadoVentasById(@PathVariable int idEmpleado) {
        return empleadoVentasService.eliminarEmpleadoVentas(idEmpleado);
    }

    @PutMapping("/{idEmpleado}")
    public String upddateEmpleadoVentasById(@PathVariable int idEmpleado, @RequestBody Model_EmpleadoVentas empleadoVentas) {
        return empleadoVentasService.actualizarEmpleadoVentas(idEmpleado, empleadoVentas);
    }


}
