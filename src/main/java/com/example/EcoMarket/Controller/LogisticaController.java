package com.example.EcoMarket.Controller;

import com.example.EcoMarket.Model.Model_Logistica;
import com.example.EcoMarket.Service.LogisticaService;
import com.example.EcoMarket.Assemblers.LogisticaModelAssembler;
import com.example.EcoMarket.hateoas.LogisticaModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping("/logistica")
@Tag(name = "Controlador Logistica", description = "Servicio de gestion de logistica")
public class LogisticaController {

    @Autowired
    private LogisticaService logisticaService;

    @Autowired
    private LogisticaModelAssembler assembler;

    @Operation(summary = "Obtener todas las logísticas", description = "Devuelve una lista de todas las logísticas registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de logísticas obtenida correctamente")
    })
    @GetMapping
    public CollectionModel<EntityModel<LogisticaModel>> getLogistica() {
        List<EntityModel<LogisticaModel>> logistica = logisticaService.listarLogistica().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(logistica,
                linkTo(methodOn(LogisticaController.class).getLogistica()).withSelfRel());
    }


    @Operation(summary = "Agregar logística", description = "Agrega una nueva logística a la lista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Logística creada exitosamente")
    })
    @PostMapping
    public EntityModel<LogisticaModel> postLogistica(
            @Parameter(description = "Datos de la logística a crear", required = true)
            @RequestBody Model_Logistica logistica) {
        return assembler.toModel(logisticaService.agregarLogisitca(logistica));
    }

    @Operation(summary = "Obtener logística por ID", description = "Obtiene una logística específica por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logística encontrada"),
            @ApiResponse(responseCode = "404", description = "Logística no encontrada")
    })
    @GetMapping("/{idLogistica}")
    public EntityModel<LogisticaModel> getLogisticaById(
            @Parameter(description = "ID de la logística a buscar", required = true)
            @PathVariable int idLogistica) {
        return assembler.toModel(logisticaService.obtenerLogistica(idLogistica));
    }

    @Operation(summary = "Eliminar logística", description = "Elimina una logística de la lista por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logística eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Logística no encontrada")
    })
    @DeleteMapping("/{idLogistica}")
    public String deleteLogisticaById(
            @Parameter(description = "ID de la logística a eliminar", required = true) @PathVariable int idLogistica) {
        return logisticaService.eliminarLogistica(idLogistica);
    }

    @Operation(summary = "Actualizar logística por ID", description = "Modifica los datos de una logística buscando por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logística actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "Logística no encontrada")
    })
    @PutMapping("/{idLogistica}")
    public EntityModel<LogisticaModel> upddateLogisticaById(
            @Parameter(description = "ID de la logística a actualizar", required = true) @PathVariable int idLogistica,
            @Parameter(description = "Datos actualizados de la logística", required = true) @RequestBody Model_Logistica logistica) {
        return assembler.toModel(logisticaService.actualizarLogistica(idLogistica, logistica));
    }

}
