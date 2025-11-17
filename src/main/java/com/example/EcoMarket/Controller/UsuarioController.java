package com.example.EcoMarket.Controller;

import com.example.EcoMarket.Model.Model_Usuario;
import com.example.EcoMarket.Service.UsuarioService;
import com.example.EcoMarket.Assemblers.UsuarioModelAssembler;
import com.example.EcoMarket.hateoas.UsuarioModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Controlador Usuario", description = "Servicio de gestión de usuarios del sistema")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener todos los usuarios", description = "Retorna una lista de todos los usuarios del sistema")
    @ApiResponse(responseCode = "200", description = "Consulta exitosa")
    @ApiResponse(responseCode = "404", description = "Consulta no encontrada")

    public CollectionModel<EntityModel<UsuarioModel>> getAllUsuarios() {
        List<EntityModel<UsuarioModel>> lista = usuarioService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(lista,
                linkTo(methodOn(UsuarioController.class).getAllUsuarios()).withSelfRel());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuario por ID", description = "Retorna un usuario según su ID")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrada")

    public EntityModel<UsuarioModel> getUsuarioById(@PathVariable int id) {
        return assembler.toModel(usuarioService.obtenerPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crear nuevo usuario", description = "Agrega un nuevo usuario al sistema")
    @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente")
    public ResponseEntity<?> crearUsuario(@RequestBody Model_Usuario usuario) {
        try {
            // normalizar email a minúsculas para evitar duplicados por case
            if (usuario.getEmail() != null) {
                usuario.setEmail(usuario.getEmail().toLowerCase());
            }
            Model_Usuario creado = usuarioService.agregarUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(assembler.toModel(creado));
        } catch (IllegalStateException e) {
            // email duplicado u otra integridad
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear usuario");
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar usuario por ID", description = "Actualiza los datos de un usuario existente")
    @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrada")

    public EntityModel<UsuarioModel> actualizarUsuario(@PathVariable int id, @RequestBody Model_Usuario usuario) {
        return assembler.toModel(usuarioService.actualizarUsuario(id, usuario));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar usuario por ID", description = "Elimina un usuario del sistema según su ID")
    @ApiResponse(responseCode = "200", description = "Usuario eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrada")

    public String eliminarUsuario(@PathVariable int id) {
        return usuarioService.eliminarUsuario(id);
    }

}