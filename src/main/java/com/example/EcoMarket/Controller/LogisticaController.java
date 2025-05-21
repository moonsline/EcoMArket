package com.example.EcoMarket.Controller;

import com.example.EcoMarket.Model.Model_Logistica;
import com.example.EcoMarket.Service.LogisticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logistica")
public class LogisticaController {

    @Autowired
    private LogisticaService logisticaService;


    @GetMapping
    public String getLogisitca() { return logisticaService.listarLogistica();}

     @PostMapping
    public String postLogistica(@RequestBody Model_Logistica logistica) {return logisticaService.agregarLogisitca(logistica);}

    @GetMapping("/{idLogistica}")
    public String getLogisticaById(@PathVariable int idLogistica) {
        return logisticaService.obtenerLogistica(idLogistica);
    }

    @DeleteMapping("/{idLogistica}")
    public String deleteLogisticaById(@PathVariable int idLogistica) {
        return logisticaService.eliminarLogistica(idLogistica);
    }

    @PutMapping("/{idLogistica}")
    public String upddateLogisticaById(@PathVariable int idLogistica, @RequestBody Model_Logistica logistica) {
        return logisticaService.actualizarLogistica(idLogistica, logistica);
    }


}
