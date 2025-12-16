package com.example.EcoMarket.Service;

import com.example.EcoMarket.Model.Model_Proveedor;
import com.example.EcoMarket.Repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    public Model_Proveedor agregarProveedor(Model_Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public List<Model_Proveedor> obtenerTodos() {
        return proveedorRepository.findAll();
    }

    public Model_Proveedor obtenerPorId(int id) {
        return proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + id));
    }

    public String eliminarProveedor(int id) {
        if (proveedorRepository.existsById(id)) {
            proveedorRepository.deleteById(id);
            return "Proveedor eliminado correctamente";
        } else {
            return "Proveedor no encontrado";
        }
    }

    public Model_Proveedor actualizarProveedor(int id, Model_Proveedor proveedor) {
        Model_Proveedor existente = proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + id));

        existente.setNombre(proveedor.getNombre());
        existente.setContacto(proveedor.getContacto());

        return proveedorRepository.save(existente);
    }
}
