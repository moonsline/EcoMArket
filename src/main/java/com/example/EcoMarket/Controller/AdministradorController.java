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
@Tag(name="Administrador Controller", description = "Servicio de Gestión de Administrador")
public class AdministradorController {
    @Autowired
    private AdministradorService administradorService;

    @GetMapping
    @Operation(summary = "Administrador", description = "Obtiene la lista de administrador existente")
    @ApiResponse(responseCode = "200", description = "¡Consulta Exitosa!")
    public String getAdministrador() { return administradorService.listaAdminstrador();}

    @PostMapping
    @Operation(summary = "Agregar Administración", description="Agrega Administración a su lista")
    public String postAdministrador(@RequestBody Model_Administrador administrador) {return administradorService.agregarAdministrador(administrador);}

    @GetMapping("/{idAdmin}")
    @Operation(summary = "Obtener Administrador por su id", description="Obtiene la lista de Administrador por su id")
    public String getAdministradorById(@PathVariable int idAdmin) {
        return administradorService.obtenerAdminstrador(idAdmin);
    }

    @DeleteMapping("/{idAdmin}")
    @Operation(summary = "Elimina Administrador", description="Elimina Administrador de la lista")
    public String deleteAdministradorById(@PathVariable int idAdmin) {
        return administradorService.eliminarAdminstrador(idAdmin);
    }

    @PutMapping("/{idAdmin}")
    @Operation(summary = "Elimina Administrador con su id", description="Elimina Administrador buscando su id")
    public String upddateAdministradorById(@PathVariable int idAdmin, @RequestBody Model_Administrador administrador) {
        return administradorService.actualizarAdministrador(idAdmin, administrador);
    }


}
