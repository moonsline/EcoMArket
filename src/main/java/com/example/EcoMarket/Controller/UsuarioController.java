package com.example.EcoMarket.Controller;


import com.example.EcoMarket.Model.Model_Usuario;
import com.example.EcoMarket.Service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@Tag(name="Controlador User", description= "Servicio de gestion de usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @Operation(summary = "Obtener usuarios", description="Obtiene la lista de usuarios")
    public String getUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @PostMapping
    @Operation(summary = "Agregar usuarios", description="Agrega usuarios a la lista")
    public String postUsuarios(@RequestBody Model_Usuario usuario) {
        return usuarioService.agregarUsuario(usuario);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuario con su id", description="Obtiene usuario buscando su id")
    public String getUsuarioById(@PathVariable int id) {
        return usuarioService.obtenerUsuario(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina usuarios", description="Elimina usuarios de la lista")
    public String deleteUsuarioById(@PathVariable int id) {
        return usuarioService.eliminarUsuario(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Elimina usuario con su id", description="Elimina usuario buscando su id")
    public String upddateUsuarioById(@PathVariable int id, @RequestBody Model_Usuario usuario) {
        return usuarioService.actualizarUsuario(id, usuario);
    }



}
