package com.example.EcoMarket.Controller;

import com.example.EcoMarket.Model.Model_GerenteTienda;
import com.example.EcoMarket.Service.GerenteTiendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gerentes")
@Tag(name = "Controlador Genrente", description = "Servicio de gestion de Ventas ")
public class GerenteTiendaController {

    @Autowired
    private GerenteTiendaService gerenteTiendaService;

     @GetMapping
     @Operation(summary = "Obtener Gerente", description = "Obtiene la lista de usuarios existentes en el sisteme")
     @ApiResponse(responseCode = "200", description = "¡Consulta exitosa!")
    public String getGerenteTienda() { return gerenteTiendaService.listarGerenteTienda();}

     @PostMapping
    public String postGerenteTienda(@RequestBody Model_GerenteTienda gerenteTienda) {return gerenteTiendaService.agregarGerenteTienda(gerenteTienda);}

    @GetMapping("/{idGerente}")
    public String getGerenteTiendaById(@PathVariable int idGerente) {
        return gerenteTiendaService.obtenerGerenteTienda(idGerente);
    }

    @DeleteMapping("/{idGerente}")
    public String deleteGerenteTiendaById(@PathVariable int idGerente) {
        return gerenteTiendaService.eliminarGerenteTienda(idGerente);
    }

    @PutMapping("/{idGerente}")
    public String upddateGerenteTiendaById(@PathVariable int idGerente, @RequestBody Model_GerenteTienda gerenteTienda) {
        return gerenteTiendaService.actualizarGerenteTienda(idGerente, gerenteTienda);
    }

}
