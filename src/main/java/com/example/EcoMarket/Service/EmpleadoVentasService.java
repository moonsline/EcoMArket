package com.example.EcoMarket.Service;

import com.example.EcoMarket.Model.Model_EmpleadoVentas;
import com.example.EcoMarket.Repository.EmpleadoVentasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoVentasService {

    @Autowired
    private EmpleadoVentasRepository repo;

    public Model_EmpleadoVentas agregar(Model_EmpleadoVentas e) {
        return repo.save(e);
    }

    public List<Model_EmpleadoVentas> obtenerTodos() {
        return repo.findAll();
    }

    public Model_EmpleadoVentas obtenerPorId(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
    }

    public Model_EmpleadoVentas actualizar(int id, Model_EmpleadoVentas nuevo) {
        Model_EmpleadoVentas actual = obtenerPorId(id);
        actual.setNombre(nuevo.getNombre());
        actual.setEmail(nuevo.getEmail());
        actual.setPassword(nuevo.getPassword());
        actual.setRol(nuevo.getRol());
        return repo.save(actual);
    }

    public String eliminar(int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "Empleado eliminado correctamente";
        } else {
            return "Empleado no encontrado";
        }
    }

    public void setRepo(EmpleadoVentasRepository r) {
        this.repo = r;
    }
}

