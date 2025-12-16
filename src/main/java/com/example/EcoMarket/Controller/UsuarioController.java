package com.example.EcoMarket.Controller;

import com.example.EcoMarket.Model.Model_Usuario;
import com.example.EcoMarket.Service.UsuarioService;
import com.example.EcoMarket.dto.UsuarioRegistroDTO;
import com.example.EcoMarket.dto.UsuarioRespuestaDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Controlador Usuario", description = "Servicio de gestión de usuarios del sistema")
public class UsuarioController {

    private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioService usuarioService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    @Operation(summary = "Obtener todos los usuarios", description = "Retorna una lista de todos los usuarios del sistema")
    @ApiResponse(responseCode = "200", description = "Consulta exitosa")
    @ApiResponse(responseCode = "404", description = "Consulta no encontrada")
    public ResponseEntity<List<UsuarioRespuestaDTO>> getAllUsuarios() {
        // Devuelvo una lista de DTOs simples, sin HATEOAS, para facilitar el consumo desde frontend y app móvil.
        List<UsuarioRespuestaDTO> lista = usuarioService.obtenerTodos().stream()
            .map(usuarioService::convertirARespuestaDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuario por ID", description = "Retorna un usuario según su ID")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrada")
    public ResponseEntity<UsuarioRespuestaDTO> getUsuarioById(@PathVariable int id) {
        Model_Usuario usuario = usuarioService.obtenerPorId(id);
        UsuarioRespuestaDTO respuesta = usuarioService.convertirARespuestaDTO(usuario);
        return ResponseEntity.ok(respuesta);
    }

    @PostMapping
    @Operation(summary = "Crear nuevo usuario", description = "Agrega un nuevo usuario al sistema")
    @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente")
    public ResponseEntity<?> crearUsuario(@RequestBody UsuarioRegistroDTO usuarioDto) {
        log.debug("Payload recibido para crear usuario: {}", usuarioDto);
        try {
            if (usuarioDto.getPassword() == null || usuarioDto.getPassword().isBlank()) {
                log.warn("Intento de creación de usuario sin contraseña");
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "La contraseña es obligatoria"));
            }
            Model_Usuario creado = usuarioService.agregarUsuarioDesdeDTO(usuarioDto);
            UsuarioRespuestaDTO respuesta = usuarioService.convertirARespuestaDTO(creado);
            log.debug("Usuario creado exitosamente: {}", respuesta);
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        } catch (IllegalStateException e) {
            log.error("Error de estado al crear usuario: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error", e.getMessage()));
        } catch (IllegalArgumentException e) {
            log.error("Error de argumento al crear usuario: {}", e.getMessage());
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            log.error("Error interno al crear usuario: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error interno del servidor"));
        }
    }


    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and @usuarioService.obtenerPorId(#id).getEmail() == authentication.name)")
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar usuario por ID", description = "Actualiza los datos de un usuario existente")
    @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    public ResponseEntity<Object> actualizarUsuario(@PathVariable int id, @RequestBody UsuarioRegistroDTO usuarioDto) {
        log.debug("Payload recibido para actualizar usuario con ID {}: {}", id, usuarioDto);
        try {
            Model_Usuario actualizado = usuarioService.actualizarUsuarioDesdeDTO(id, usuarioDto);
            UsuarioRespuestaDTO respuesta = usuarioService.convertirARespuestaDTO(actualizado);
            log.debug("Usuario actualizado correctamente: {}", respuesta);
            return ResponseEntity.ok(respuesta);
        } catch (IllegalStateException e) {
            log.error("Error de estado al actualizar usuario con ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error", e.getMessage()));
        } catch (IllegalArgumentException e) {
            log.error("Error de argumento al actualizar usuario con ID {}: {}", id, e.getMessage());
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            log.error("Error interno al actualizar usuario con ID {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error interno del servidor"));
        }
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar usuario por ID", description = "Elimina un usuario del sistema según su ID")
    @ApiResponse(responseCode = "200", description = "Usuario eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrada")
    public ResponseEntity<String> eliminarUsuario(@PathVariable int id) {
        String mensaje = usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok(mensaje);
    }

}