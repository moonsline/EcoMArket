package com.example.EcoMarket.Controller;

import com.example.EcoMarket.Model.Model_Logistica;
import com.example.EcoMarket.Service.LogisticaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logistica")
@Tag(name = "Controlador Logistica", description = "Servicio de gestion de logistica")
public class LogisticaController {

    @Autowired
    private LogisticaService logisticaService;


    @GetMapping
    @Operation(summary = "Obtener Logistica", description = "Obtiene la lista de logistica existente en el sistema")
    @ApiResponse(responseCode = "200", description = "Consulta exitosa")
    public String getLogisitca() { return logisticaService.listarLogistica();}

     @PostMapping
     @Operation(summary = "Agregar logistica", description="Agrega logistica a su lista")
     public String postLogistica(@RequestBody Model_Logistica logistica) {return logisticaService.agregarLogisitca(logistica);}

    @GetMapping("/{idLogistica}")
    @Operation(summary = "Obtener logistica por su id", description="Obtiene la lista de logistica por su id")
    public String getLogisticaById(@PathVariable int idLogistica) {
        return logisticaService.obtenerLogistica(idLogistica);
    }

    @DeleteMapping("/{idLogistica}")
    @Operation(summary = "Elimina logistica", description="Elimina logistica de la lista")
    public String deleteLogisticaById(@PathVariable int idLogistica) {
        return logisticaService.eliminarLogistica(idLogistica);
    }

    @PutMapping("/{idLogistica}")
    @Operation(summary = "Elimina logistica con su id", description="Elimina logistica buscando su id")
    public String upddateLogisticaById(@PathVariable int idLogistica, @RequestBody Model_Logistica logistica) {
        return logisticaService.actualizarLogistica(idLogistica, logistica);
    }


}
