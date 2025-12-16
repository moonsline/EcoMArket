package com.example.EcoMarket.Service;

import com.example.EcoMarket.Model.Model_Administrador;
import com.example.EcoMarket.Repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository adminRepo;

    public Model_Administrador agregarAdministrador(Model_Administrador admin) {
        return adminRepo.save(admin);
    }
    public List<Model_Administrador> obtenerTodos() {
        return adminRepo.findAll();
    }
    public Model_Administrador obtenerPorId(int id) {
        return adminRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));
    }
    public Model_Administrador actualizarAdministrador(int id, Model_Administrador admin) {
        Model_Administrador existente = obtenerPorId(id);
        existente.setNombre(admin.getNombre());
        existente.setEmail(admin.getEmail());
        existente.setPassword(admin.getPassword());
        existente.setRol(admin.getRol());
        return adminRepo.save(existente);
    }

    public String eliminarAdministrador(int id) {
        if (adminRepo.existsById(id)) {
            adminRepo.deleteById(id);
            return "Administrador eliminado correctamente";
        } else {
            return "Administrador no encontrado";
        }
    }

    // para pruebas si necesitamos otra creacion de isntancias
    public void setAdminRepo(AdministradorRepository repo) {
        this.adminRepo = repo;
    }
}














