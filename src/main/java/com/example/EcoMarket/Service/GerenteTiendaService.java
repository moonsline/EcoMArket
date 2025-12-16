package com.example.EcoMarket.Service;

import com.example.EcoMarket.Model.Model_GerenteTienda;
import com.example.EcoMarket.Repository.GerenteTiendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class GerenteTiendaService {
    @Autowired
    GerenteTiendaRepository gerenteTiendaRepository;

    public Model_GerenteTienda agregarGerenteTienda(Model_GerenteTienda gerenteTienda){
        return gerenteTiendaRepository.save(gerenteTienda);
    }

    public List<Model_GerenteTienda> listarGerenteTienda(){
        return gerenteTiendaRepository.findAll();
    }

    public Model_GerenteTienda obtenerGerenteTienda(int idGerente){
        return gerenteTiendaRepository.findById(idGerente)
                .orElseThrow(() -> new RuntimeException("Gerente no encontrado"));
    }

    public String eliminarGerenteTienda(int idGerente){
        if (gerenteTiendaRepository.existsById(idGerente)){
            gerenteTiendaRepository.deleteById(idGerente);
            return"Gerente eliminado";
        }else{
            return"No se encontro el gerente";
        }
    }

    public Model_GerenteTienda actualizarGerenteTienda(int idGerente, Model_GerenteTienda gerenteTienda){
        Model_GerenteTienda existente =  obtenerGerenteTienda(idGerente);
        existente.setNombre(gerenteTienda.getNombre());
        existente.setEmail(gerenteTienda.getEmail());
        existente.setPassword(gerenteTienda.getPassword());
        existente.setRol(gerenteTienda.getRol());
        existente.setIdGerente(idGerente);
        return gerenteTiendaRepository.save(existente);

    }



}
