package com.example.EcoMarket.Service;


import com.example.EcoMarket.Model.Model_Proveedor;
import com.example.EcoMarket.Repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProveedorService {

    @Autowired
    ProveedorRepository proveedorRepository;

    public String agregarProveedor(Model_Proveedor proveedor) {
        proveedorRepository.save(proveedor);
        return "Proveedor agregado";
    }

    public String listarProveedor() {
        String output = "";
        for (Model_Proveedor proveedor : proveedorRepository.findAll()) {
            output += "ID proveedor: " +proveedor.getId()+ "\n";
            output += "Nombre proveedor: " +proveedor.getNombre()+ "\n";
            output += "Contacto proveedor: " +proveedor.getContacto()+ "\n";
        }
        if(output.isEmpty()) {
            return "No se encontro el proveedor";
        }else {
        return output;
        }
    }

    public String obtenerProveedor(int id) {
        String output = "";
        if(proveedorRepository.existsById(id)) {
            Model_Proveedor proveedor = proveedorRepository.findById(id).get();
            output += "ID proveedor: " +proveedor.getId()+ "\n";
            output += "Nombre proveedor: " +proveedor.getNombre()+ "\n";
            output += "Contacto proveedor: " +proveedor.getContacto()+ "\n";
            return output;
        }else {
            return "No se encontro el proveedor";
        }
    }

    public String eliminarProveedor(int id) {
        if(proveedorRepository.existsById(id)) {
            proveedorRepository.deleteById(id);
            return "Proveedor eliminado";
        }else{
            return "No se encontro el proveedor";
        }
    }

    public String actualizarProveedor(int id, Model_Proveedor proveedor) {
        if(proveedorRepository.existsById(id)) {
            Model_Proveedor buscado = proveedorRepository.findById(id).get();
            buscado.setNombre(proveedor.getNombre());
            buscado.setContacto(proveedor.getContacto());
            proveedorRepository.save(buscado);
            return "Proveedor actualizado";
        }else{
            return "No se encontro el proveedor";
        }
    }
}
