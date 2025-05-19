package com.example.EcoMarket.Controller;


import com.example.EcoMarket.Model.Model_Proveedor;
import com.example.EcoMarket.Service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public String getProveedores() {
        return proveedorService.listarProveedor();
    }

    @PostMapping
    public String postProveedores(@RequestBody Model_Proveedor proveedor) {
        return proveedorService.agregarProveedor(proveedor);
    }

    @GetMapping("/{id}")
    public String getProveedoreById(@PathVariable int id) {
        return proveedorService.obtenerProveedor(id);
    }

    @DeleteMapping("/{id}")
    public String deleteProveedoreById(@PathVariable int id) {
        return proveedorService.eliminarProveedor(id);
    }

    @PutMapping("/{id}")
    public String updateProveedorById(@PathVariable int id, @RequestBody Model_Proveedor proveedor) {
        return proveedorService.actualizarProveedor(id, proveedor);
    }
}
