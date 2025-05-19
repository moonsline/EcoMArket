package com.example.EcoMarket.Controller;


import com.example.EcoMarket.Model.Model_Usuario;
import com.example.EcoMarket.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Model_Usuario> obtenerUsuarios(){
        return usuarioService.getUsuarios();
    }

    @PostMapping
    public Model_Usuario guardarUsuario(@RequestBody Model_Usuario usuario){
        return usuarioService.saveUsuario(usuario);
    }

    @GetMapping("/{id}")
    public Model_Usuario buscarPorId(@PathVariable int id){
        return usuarioService.getUsuarioId(id);
    }

    @PutMapping("/{id}")
    public Model_Usuario modificarUsuario(@PathVariable int id, @RequestBody Model_Usuario usuario){
        return usuarioService.updateUsuario(usuario);
    }

    @DeleteMapping("/{id}")
    public String eliminarUsuario(@PathVariable int id){
        return usuarioService.deleteUsuario(id);
    }



}
