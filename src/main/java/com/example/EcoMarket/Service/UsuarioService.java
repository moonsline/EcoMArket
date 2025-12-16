package com.example.EcoMarket.Service;

import com.example.EcoMarket.Model.Model_Usuario;
import com.example.EcoMarket.Repository.UsuarioRepository;
import com.example.EcoMarket.dto.UsuarioRegistroDTO;
import com.example.EcoMarket.dto.UsuarioRespuestaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

// Servicio de usuarios.
// Explicación sencilla: aquí están las "acciones" que podemos hacer
// con los usuarios (crear, buscar, actualizar, eliminar, login).
@Service
public class UsuarioService {

    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private UsuarioRepository usuarioRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;


    // Elimino el método agregarUsuario(UsuarioModel dto) porque no se usa y genera error de compilación.

    // Método para crear usuario desde DTO de registro
    public Model_Usuario agregarUsuarioDesdeDTO(UsuarioRegistroDTO dto) {
        log.debug("Iniciando creación de usuario con datos: {}", dto);
        Model_Usuario usuario = new Model_Usuario();
        usuario.setId(null);
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        usuario.setRut(dto.getRut());
        // Asignar rol: si viene en el DTO y es válido, usarlo; si no, por defecto USER
        if (dto.getRol() != null && !dto.getRol().isBlank()) {
            log.debug("Asignando rol proporcionado: {}", dto.getRol());
            usuario.setRol(dto.getRol());
        } else {
            log.debug("No se proporcionó rol, asignando rol por defecto: USER");
            usuario.setRol("USER");
        }
        usuario.setActivo(1); // El usuario se crea activo por defecto.
        // Validaciones y lógica igual que antes...
        if (usuario.getEmail() != null) {
            usuario.setEmail(usuario.getEmail().toLowerCase());
        }
        if (usuarioRepo.findByEmailIgnoreCase(usuario.getEmail()).isPresent()) {
            log.error("El email ya está registrado: {}", usuario.getEmail());
            throw new IllegalStateException("El email ya está registrado");
        }
        if (usuario.getPassword() == null || usuario.getPassword().isBlank()) {
            log.error("La contraseña es obligatoria");
            throw new IllegalArgumentException("La contraseña es obligatoria");
        }
        Model_Usuario creado = usuarioRepo.save(usuario);
        log.debug("Usuario creado exitosamente: {}", creado);
        return creado;
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
        log.debug("Actualizando usuario con ID: {}", id);
        log.debug("Datos recibidos para la actualización: {}", dto);

        Model_Usuario existente = obtenerPorId(id);
        log.debug("Usuario existente antes de la actualización: {}", existente);

        // Validar y asignar el campo email
        if (dto.getEmail() == null || dto.getEmail().isBlank()) {
            log.error("El campo email no puede ser nulo o vacío");
            throw new IllegalArgumentException("El campo email es obligatorio");
        }
        existente.setEmail(dto.getEmail().toLowerCase());

        existente.setNombre(dto.getNombre());
        existente.setRut(dto.getRut());

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            log.debug("Actualizando contraseña del usuario con ID: {}", id);
            existente.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        if (dto.getRol() != null && !dto.getRol().isBlank()) {
            log.debug("Actualizando rol del usuario con ID: {} a {}", id, dto.getRol());
            existente.setRol(dto.getRol());
        }

        Model_Usuario actualizado = usuarioRepo.save(existente);
        log.debug("Usuario actualizado correctamente: {}", actualizado);

        return actualizado;
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