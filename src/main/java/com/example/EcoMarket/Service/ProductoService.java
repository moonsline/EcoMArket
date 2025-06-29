package com.example.EcoMarket.Service;

import com.example.EcoMarket.Model.Model_Producto;
import com.example.EcoMarket.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository repo;

    public List<Model_Producto> obtenerTodos() { return repo.findAll(); }

    public Model_Producto obtenerPorId(int id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    public Model_Producto agregar(Model_Producto p) { return repo.save(p); }

    public Model_Producto actualizar(int id, Model_Producto p) {
        Model_Producto actual = obtenerPorId(id);
        actual.setNombre(p.getNombre());
        actual.setPrecio(p.getPrecio());
        actual.setStock(p.getStock());
        return repo.save(actual);
    }

    public String eliminar(int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "Producto eliminado";
        } else return "No encontrado";
    }
}

