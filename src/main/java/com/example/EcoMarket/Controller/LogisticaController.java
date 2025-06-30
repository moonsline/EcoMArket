package com.example.EcoMarket.Controller;

import com.example.EcoMarket.Model.Model_Logistica;
import com.example.EcoMarket.Service.LogisticaService;
import com.example.EcoMarket.Assemblers.LogisticaModelAssembler;
import com.example.EcoMarket.hateoas.LogisticaModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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


    @GetMapping


    public CollectionModel<EntityModel<LogisticaModel>> getLogistica() {
        List<EntityModel<LogisticaModel>> logistica = logisticaService.listarLogistica().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(logistica,
                linkTo(methodOn(LogisticaController.class).getLogistica()).withSelfRel());
    }


     @PostMapping
     @Operation(summary = "Agregar logistica", description="Agrega logistica a su lista")
     @ApiResponse(responseCode = "200", description = "Logistica creado exitosamente")
     public EntityModel<LogisticaModel> postLogistica(@RequestBody Model_Logistica logistica) {return assembler.toModel(logisticaService.agregarLogisitca(logistica));}

    @GetMapping("/{idLogistica}")
    @Operation(summary = "Obtener logistica por su id", description="Obtiene la lista de logistica por su id")
    @ApiResponse(responseCode = "200", description = "Logistica encontrado")
    public EntityModel<LogisticaModel> getLogisticaById(@PathVariable int idLogistica) {
        return assembler.toModel(logisticaService.obtenerLogistica(idLogistica));
    }



    @DeleteMapping("/{idLogistica}")
    @Operation(summary = "Elimina logistica", description="Elimina logistica de la lista")
    public String deleteLogisticaById(@PathVariable int idLogistica) {
        return logisticaService.eliminarLogistica(idLogistica);
    }

    @PutMapping("/{idLogistica}")
    @Operation(summary = "Actulizar logistica con su id", description="Modifica los datos de  logistica buscando su id")
    @ApiResponse(responseCode = "200", description = "Logistica actualizado correctamente")
    public EntityModel<LogisticaModel> upddateLogisticaById(@PathVariable int idLogistica, @RequestBody Model_Logistica logistica) {
        return assembler.toModel(logisticaService.actualizarLogistica(idLogistica, logistica));
    }

}
