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

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Controlador Usuario", description = "Servicio de gestión de usuarios del sistema")
public class UsuarioController {

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
    public ResponseEntity<UsuarioRespuestaDTO> crearUsuario(@RequestBody UsuarioRegistroDTO usuarioDto) {
        // Recibo solo los datos necesarios para crear el usuario, así evito que el frontend envíe datos sensibles o innecesarios.
        try {
            Model_Usuario creado = usuarioService.agregarUsuarioDesdeDTO(usuarioDto);
            // Transformo la entidad a DTO de respuesta para no exponer la contraseña ni otros datos sensibles.
            UsuarioRespuestaDTO respuesta = usuarioService.convertirARespuestaDTO(creado);
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar usuario por ID", description = "Actualiza los datos de un usuario existente")
    @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrada")
    public ResponseEntity<UsuarioRespuestaDTO> actualizarUsuario(@PathVariable int id, @RequestBody UsuarioRegistroDTO usuarioDto) {
        // Actualizo el usuario usando los datos del DTO de registro, así mantengo la seguridad y simplicidad.
        Model_Usuario actualizado = usuarioService.actualizarUsuarioDesdeDTO(id, usuarioDto);
        UsuarioRespuestaDTO respuesta = usuarioService.convertirARespuestaDTO(actualizado);
        return ResponseEntity.ok(respuesta);
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