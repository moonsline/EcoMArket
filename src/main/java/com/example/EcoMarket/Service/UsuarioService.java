package com.example.EcoMarket.Service;

import com.example.EcoMarket.Model.Model_Usuario;
import com.example.EcoMarket.Repository.UsuarioRepository;
import com.example.EcoMarket.dto.UsuarioRegistroDTO;
import com.example.EcoMarket.dto.UsuarioRespuestaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    private PasswordEncoder passwordEncoder;


    // Elimino el método agregarUsuario(UsuarioModel dto) porque no se usa y genera error de compilación.

    // Método para crear usuario desde DTO de registro
    public Model_Usuario agregarUsuarioDesdeDTO(UsuarioRegistroDTO dto) {
        // Creo la entidad usando solo los datos necesarios, así evito problemas de seguridad.
        Model_Usuario usuario = new Model_Usuario();
        usuario.setId(null);
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));

        usuario.setRut(dto.getRut());
        usuario.setRol("USER"); // Por defecto, el rol es USER. Si el registro es desde backoffice, puedes cambiar esto.
        usuario.setActivo(1); // El usuario se crea activo por defecto.
        // Validaciones y lógica igual que antes...
        if (usuario.getEmail() != null) {
            usuario.setEmail(usuario.getEmail().toLowerCase());
        }
        if (usuarioRepo.findByEmailIgnoreCase(usuario.getEmail()).isPresent()) {
            throw new IllegalStateException("El email ya está registrado");
        }
        if (usuario.getPassword() == null || usuario.getPassword().isBlank()) {
            throw new IllegalArgumentException("La contraseña es obligatoria");
        }
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
            existente.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }
        existente.setRol(usuario.getRol());
        return usuarioRepo.save(existente);
    }

    // Método para actualizar usuario desde DTO de registro
    public Model_Usuario actualizarUsuarioDesdeDTO(int id, UsuarioRegistroDTO dto) {
        Model_Usuario existente = obtenerPorId(id);
        existente.setNombre(dto.getNombre());
        existente.setEmail(dto.getEmail());
        existente.setRut(dto.getRut());
        // Solo actualizo la contraseña si viene en el DTO y no está vacía
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            existente.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        // El rol y activo pueden mantenerse igual o ajustarse según la lógica de negocio
        return usuarioRepo.save(existente);
    }

    public String eliminarUsuario(int id) {
        if (usuarioRepo.existsById(id)) {
            usuarioRepo.deleteById(id);
            return "Usuario eliminado correctamente";
        }
        return "Usuario no encontrado";
    }

    // Método para convertir la entidad a DTO de respuesta
    public UsuarioRespuestaDTO convertirARespuestaDTO(Model_Usuario usuario) {
        // Solo incluyo los datos públicos, nunca la contraseña.
        UsuarioRespuestaDTO dto = new UsuarioRespuestaDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        dto.setRut(usuario.getRut());
        dto.setRol(usuario.getRol());
        dto.setActivo(usuario.getActivo());
        return dto;
    }

    // Para pruebas si necesitas otra creación de instancias
    public void setUsuarioRepo(UsuarioRepository repo) {
        this.usuarioRepo = repo;
    }
}