package com.example.EcoMarket.Controller;


import com.example.EcoMarket.Model.Model_Proveedor;
import com.example.EcoMarket.Model.Model_Usuario;
import com.example.EcoMarket.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String getUsuarios(){return usuarioService.listarUsuarios();}

    @PostMapping
    public String postUsuario(@RequestBody Model_Usuario usuario) {
        return usuarioService.agregarUsuario(usuario);
    }

    @GetMapping("/{id}")
    public String getUsuariosById(@PathVariable int id) {
        return usuarioService.obtenerUsuario(id);
    }

    @DeleteMapping("/{id}")
    public String deleteUsuarioById(@PathVariable int id) {
        return usuarioService.eliminarUsuario(id);
    }

    @PutMapping("/{id}")
    public String updateUsuarioById(@PathVariable int id, @RequestBody Model_Usuario usuario) {
        return usuarioService.actualizarUsuario(id, usuario);
    }


}
