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
@Tag(name = "Controlador GerenteTienda", description = "Servicio de gestion de Ventas ")
public class GerenteTiendaController {

    @Autowired
    private GerenteTiendaService gerenteTiendaService;

     @GetMapping
     @Operation(summary = "Obtener GerenteTienda", description = "Obtiene la lista de GerenteTienda existentes en el sisteme")
     @ApiResponse(responseCode = "200", description = "¡Consulta exitosa!")
    public String getGerenteTienda() { return gerenteTiendaService.listarGerenteTienda();}

     @PostMapping
     @Operation(summary = "Agregar GerenteTienda", description="Agrega GerenteTienda a su lista")
     public String postGerenteTienda(@RequestBody Model_GerenteTienda gerenteTienda) {return gerenteTiendaService.agregarGerenteTienda(gerenteTienda);}

    @GetMapping("/{idGerente}")
    @Operation(summary = "Obtener GerenteTienda por su id", description="Obtiene la lista de GerenteTienda por su id")
    public String getGerenteTiendaById(@PathVariable int idGerente) {
        return gerenteTiendaService.obtenerGerenteTienda(idGerente);
    }

    @DeleteMapping("/{idGerente}")
    @Operation(summary = "Elimina GerenteTienda", description="Elimina GerenteTienda de la lista")
    public String deleteGerenteTiendaById(@PathVariable int idGerente) {
        return gerenteTiendaService.eliminarGerenteTienda(idGerente);
    }

    @PutMapping("/{idGerente}")
    @Operation(summary = "Elimina GerenteTienda con su id", description="Elimina GerenteTienda buscando su id")
    public String upddateGerenteTiendaById(@PathVariable int idGerente, @RequestBody Model_GerenteTienda gerenteTienda) {
        return gerenteTiendaService.actualizarGerenteTienda(idGerente, gerenteTienda);
    }

}
