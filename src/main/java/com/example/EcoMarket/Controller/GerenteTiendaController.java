package com.example.EcoMarket.Controller;

import com.example.EcoMarket.Assemblers.GerenteTiendaModelAssembler;
import com.example.EcoMarket.Model.Model_GerenteTienda;
import com.example.EcoMarket.Service.GerenteTiendaService;

import com.example.EcoMarket.hateoas.GerenteTiendaModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/gerentes")
@Tag(name = "Controlador GerenteTienda", description = "Servicio de gestion de Ventas ")
public class GerenteTiendaController {

     @Autowired
     private GerenteTiendaService gerenteTiendaService;

     @Autowired
     private GerenteTiendaModelAssembler assembler;

    @Operation(summary = "Obtener todos los GerenteTienda", description = "Devuelve una lista de todos los gerentes de tienda registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de gerentes obtenida correctamente"),
            @ApiResponse(responseCode = "404", description = "Logística no encontrado")

    })
    @GetMapping
    public CollectionModel<EntityModel<GerenteTiendaModel>> getGerenteTienda() {
        List<EntityModel<GerenteTiendaModel>> gerenteTienda = gerenteTiendaService.listarGerenteTienda().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(gerenteTienda,
                linkTo(methodOn(GerenteTiendaController.class).getGerenteTienda()).withSelfRel());
    }



    @Operation(summary = "Agregar GerenteTienda", description = "Agrega GerenteTienda a su lista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "GerenteTienda creado correctamente"),
            @ApiResponse(responseCode = "404", description = "GerenteTienda no encontrado")
    })
    @PostMapping
    public EntityModel<GerenteTiendaModel> postGerenteTienda(@RequestBody Model_GerenteTienda gerenteTienda)
    {return assembler.toModel(gerenteTiendaService.agregarGerenteTienda(gerenteTienda));}

    @Operation(summary = "Obtener GerenteTienda por su id", description = "Obtiene la lista de GerenteTienda por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "GerenteTienda encontrado"),
            @ApiResponse(responseCode = "404", description = "GerenteTienda no encontrado")
    })
    @GetMapping("/{idGerente}")
    public EntityModel<GerenteTiendaModel> getGerenteTiendaById(@PathVariable int idGerente) {
        return assembler.toModel(gerenteTiendaService.obtenerGerenteTienda(idGerente));
    }

    @Operation(summary = "Elimina GerenteTienda", description = "Elimina GerenteTienda de la lista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "GerenteTienda eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "GerenteTienda no encontrado")
    })
    @DeleteMapping("/{idGerente}")
    public String deleteGerenteTiendaById(@PathVariable int idGerente) {
        return gerenteTiendaService.eliminarGerenteTienda(idGerente);
    }
    @Operation(summary = "Actualizar GerenteTienda por su id", description = "Actualiza GerenteTienda buscando su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "GerenteTienda actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "GerenteTienda no encontrado")
    })
    @PutMapping("/{idGerente}")
    public EntityModel<GerenteTiendaModel> upddateGerenteTiendaById(@PathVariable int idGerente, @RequestBody Model_GerenteTienda gerenteTienda) {
        return assembler.toModel(gerenteTiendaService.actualizarGerenteTienda(idGerente, gerenteTienda));
    }

}
