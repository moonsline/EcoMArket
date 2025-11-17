package com.example.EcoMarket.Service;

import com.example.EcoMarket.Model.Model_Usuario;
import com.example.EcoMarket.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Servicio de usuarios.
// Explicación sencilla: aquí están las "acciones" que podemos hacer
// con los usuarios (crear, buscar, actualizar, eliminar, login).
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Transactional
    public Model_Usuario agregarUsuario(Model_Usuario usuario) {
        // Ignorar cualquier id entrante: la BD lo generará
        usuario.setId(null);
        // Normalizar email
        if (usuario.getEmail() != null) {
            usuario.setEmail(usuario.getEmail().toLowerCase());
        }
        // Validar duplicado por email (case-insensitive)
        if (usuarioRepo.findByEmailIgnoreCase(usuario.getEmail()).isPresent()) {
            throw new IllegalStateException("El email ya está registrado");
        }
        // Validación mínima de password
        if (usuario.getPassword() == null || usuario.getPassword().isBlank()) {
            throw new IllegalArgumentException("La contraseña es obligatoria");
        }
        // Opción A: guardamos password en texto plano (solo uso académico)
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
        existente.setEmail(usuario.getEmail());
        // Si viene nueva contraseña, la actualizamos
        if (usuario.getPassword() != null && !usuario.getPassword().isBlank()) {
            existente.setPassword(usuario.getPassword());
        }
        existente.setRol(usuario.getRol());
        return usuarioRepo.save(existente);
    }

    public Model_Usuario loginPlano(String email, String password) {
        Model_Usuario usuario = usuarioRepo.findByEmailIgnoreCase(email.toLowerCase())
                .orElseThrow(() -> new RuntimeException("No existe usuario"));
        // Comparar password en texto plano
        if (!usuario.getPassword().equals(password)) {
            throw new RuntimeException("Credenciales inválidas");
        }
        return usuario;
    }

    public String eliminarUsuario(int id) {
        if (usuarioRepo.existsById(id)) {
            usuarioRepo.deleteById(id);
            return "Usuario eliminado correctamente";
        }
        return "Usuario no encontrado";
    }

    // Para pruebas si necesitas otra creación de instancias
    public void setUsuarioRepo(UsuarioRepository repo) {
        this.usuarioRepo = repo;
    }
}