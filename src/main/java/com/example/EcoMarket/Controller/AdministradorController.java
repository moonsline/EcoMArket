package com.example.EcoMarket.Controller;


import com.example.EcoMarket.Model.Model_Administrador;
import com.example.EcoMarket.Service.AdministradorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/administradores")
@Tag(name="Administrador Controller", description = "Servicio de Gestión de cliente")
public class AdministradorController {
    @Autowired
    private AdministradorService administradorService;

    @GetMapping
    @Operation(summary = "Administrador", description = "Obtiene el administrador y su información")
    @ApiResponse(responseCode = "200", description = "¡Consulta Exitosa!")
    public String getAdministrador() { return administradorService.listaAdminstrador();}

    @PostMapping
    public String postAdministrador(@RequestBody Model_Administrador administrador) {return administradorService.agregarAdministrador(administrador);}

    @GetMapping("/{idAdmin}")
    public String getAdministradorById(@PathVariable int idAdmin) {
        return administradorService.obtenerAdminstrador(idAdmin);
    }

    @DeleteMapping("/{idAdmin}")
    public String deleteAdministradorById(@PathVariable int idAdmin) {
        return administradorService.eliminarAdminstrador(idAdmin);
    }

    @PutMapping("/{idAdmin}")
    public String upddateAdministradorById(@PathVariable int idAdmin, @RequestBody Model_Administrador administrador) {
        return administradorService.actualizarAdministrador(idAdmin, administrador);
    }


}
