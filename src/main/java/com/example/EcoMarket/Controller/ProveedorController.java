package com.example.EcoMarket.Controller;


import com.example.EcoMarket.Model.Model_Proveedor;
import com.example.EcoMarket.Service.ProveedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/proveedores")
@Tag(name="Controlador Proveedor", description="Servicio de gestion de proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    @Operation(summary = "Obtener proveedores", description="Obtiene la lista de proveedores")
    public String getProveedores() {
        return proveedorService.listarProveedor();
    }

    @PostMapping
    @Operation(summary = "Agregar proveedores", description="Agrega proveedores a la lista")
    public String postProveedores(@RequestBody Model_Proveedor proveedor) {
        return proveedorService.agregarProveedor(proveedor);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener proveedores con su id", description="Obtiene proveedores buscando su id")
    public String getProveedoreById(@PathVariable int id) {
        return proveedorService.obtenerProveedor(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina proveedores", description="Elimina proveedores de la lista")
    public String deleteProveedoreById(@PathVariable int id) {
        return proveedorService.eliminarProveedor(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Elimina proveedor con su id", description="Elimina proveedor buscando su id")
    public String upddateProveedoreById(@PathVariable int id, @RequestBody Model_Proveedor proveedor) {
        return proveedorService.actualizarProveedor(id, proveedor);
    }
}
