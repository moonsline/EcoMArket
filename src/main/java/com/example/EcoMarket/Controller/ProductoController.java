package com.example.EcoMarket.Controller;


import com.example.EcoMarket.Assemblers.ProductoModelAssembler;
import com.example.EcoMarket.Model.Model_Producto;
import com.example.EcoMarket.Service.ProductoService;
import com.example.EcoMarket.Service.FileStorageService;
import com.example.EcoMarket.hateoas.ProductoModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/productos")
@Tag(name = "Controlador Producto", description = "Gestión de productos")
public class ProductoController {

    @Autowired
    private ProductoService service;
    @Autowired
    private ProductoModelAssembler assembler;
    @Autowired
    private FileStorageService fileStorage;

    @Operation(summary = "Obtener todos los productos", description = "Devuelve una lista de todos los productos registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de productos obtenida correctamente"),
            @ApiResponse(responseCode = "404", description = "Lista de productos no encontrado")

    })
    @GetMapping
    public CollectionModel<EntityModel<ProductoModel>> getAllProductos() {
        return CollectionModel.of(service.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList()));
    }

    @Operation(summary = "Obtener producto por ID", description = "Devuelve un producto específico según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/{id}")
    public EntityModel<ProductoModel> getProductoById(@PathVariable int id) {
        return assembler.toModel(service.obtenerPorId(id));
    }
    @GetMapping("/uploads/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            Path filePath = Paths.get("uploads").resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }

            // Detectar tipo MIME automáticamente
            String contentType = Files.probeContentType(filePath);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(
                            contentType != null ? contentType : "application/octet-stream"
                    ))
                    .body(resource);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }



    @Operation(summary = "Crear un nuevo producto", description = "Crea un producto con los datos proporcionados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PostMapping(consumes = {"multipart/form-data"})
    public EntityModel<ProductoModel> crear(
        @RequestPart("producto") Model_Producto p,
        @RequestPart(value = "img", required = false) MultipartFile img
    ) {
        return assembler.toModel(service.agregar(p, img));
    }

    @Operation(summary = "Actualizar un producto", description = "Actualiza los datos de un producto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PutMapping("/{id}")
    public EntityModel<ProductoModel> actualizar(@PathVariable int id, @RequestBody Model_Producto p) {
        return assembler.toModel(service.actualizar(id, p));
    }

    @Operation(summary = "Eliminar un producto", description = "Elimina un producto según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id) {
        return service.eliminar(id);
    }
}
