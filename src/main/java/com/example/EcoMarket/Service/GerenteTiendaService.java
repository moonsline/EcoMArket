package com.example.EcoMarket.Service;

import com.example.EcoMarket.Model.Model_GerenteTienda;
import com.example.EcoMarket.Repository.GerenteTiendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class GerenteTiendaService {
    @Autowired
    GerenteTiendaRepository gerenteTiendaRepository;

    public String agregarGerenteTienda(Model_GerenteTienda gerenteTienda){
        gerenteTiendaRepository.save(gerenteTienda);
        return "Gerente agregado";
    }

    public String listarGerenteTienda(){
        String output ="";
        for(Model_GerenteTienda gerenteTienda :gerenteTiendaRepository.findAll()){
            output+="ID gerente"+gerenteTienda.getIdGerente() +"\n";
            output+="Nombre Gerente"+gerenteTienda.getNombre() +"\n";
            output+="Email Gerente"+gerenteTienda.getEmail() +"\n";
            output+="Password Gerente"+gerenteTienda.getPassword() +"\n";
            output+="Rol Gerente"+gerenteTienda.getRol() +"\n";

        }if(output.isEmpty()){
            return "No se encontro el gerente";
        }else{
            return output;
        }
    }

    public String obtenerGerenteTienda(int idGerente){
        String output ="";
        if (gerenteTiendaRepository.existsById(idGerente)){
            Model_GerenteTienda gerenteTienda = gerenteTiendaRepository.findById(idGerente).get();
            output+="ID gerente"+gerenteTienda.getIdGerente() +"\n";
            output+="Nombre Gerente"+gerenteTienda.getNombre() +"\n";
            output+="Email Gerente"+gerenteTienda.getEmail() +"\n";
            output+="Password Gerente"+gerenteTienda.getPassword() +"\n";
            output+="Rol Gerente"+gerenteTienda.getRol() +"\n";
            return output;
        }else{
            return"No se encontro el gerente";
        }
    }

    public String eliminarGerenteTienda(int idGerente){
        if (gerenteTiendaRepository.existsById(idGerente)){
            gerenteTiendaRepository.deleteById(idGerente);
            return"Gerente eliminado";
        }else{
            return"No se encontro el gerente";
        }
    }

    public String actualizarGerenteTienda(int idGerente, Model_GerenteTienda gerenteTienda){
        if (gerenteTiendaRepository.existsById(idGerente)){
            Model_GerenteTienda buscado = gerenteTiendaRepository.findById(idGerente).get();
            buscado.setNombre(gerenteTienda.getNombre());
            buscado.setEmail(gerenteTienda.getEmail());
            buscado.setPassword(gerenteTienda.getPassword());
            buscado.setRol(gerenteTienda.getPassword());
            gerenteTiendaRepository.save(buscado);
            return"Gerente actualizado";
        }else{
            return"No se encontro el gerente";
        }
    }



}
