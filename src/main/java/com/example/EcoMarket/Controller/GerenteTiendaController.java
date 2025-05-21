package com.example.EcoMarket.Controller;

import com.example.EcoMarket.Model.Model_GerenteTienda;
import com.example.EcoMarket.Service.GerenteTiendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gerente")
public class GerenteTiendaController {

    @Autowired
    private GerenteTiendaService gerenteTiendaService;

     @GetMapping
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
