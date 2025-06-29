package com.example.EcoMarket.Service;

import com.example.EcoMarket.Model.Model_Usuario;
import com.example.EcoMarket.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    public Model_Usuario agregarUsuario(Model_Usuario usuario) {
        return usuarioRepo.save(usuario);
    }

    public List<Model_Usuario> obtenerTodos() {
        return usuarioRepo.findAll();
    }

    public Model_Usuario obtenerPorId(int id) {
        return usuarioRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public Model_Usuario actualizarUsuario(int id, Model_Usuario usuario) {
        Model_Usuario existente = obtenerPorId(id);
        existente.setNombre(usuario.getNombre());
        existente.setCorreo(usuario.getCorreo());
        existente.setContrasena(usuario.getContrasena());
        existente.setRol(usuario.getRol());
        return usuarioRepo.save(existente);
    }

    public String eliminarUsuario(int id) {
        if (usuarioRepo.existsById(id)) {
            usuarioRepo.deleteById(id);
            return "Usuario eliminado correctamente";
        } else {
            return "Usuario no encontrado";
        }
    }

    // Para pruebas si necesitas otra creación de instancias
    public void setUsuarioRepo(UsuarioRepository repo) {
        this.usuarioRepo = repo;
    }
}