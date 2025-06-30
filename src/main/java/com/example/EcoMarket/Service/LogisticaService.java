package com.example.EcoMarket.Service;

import com.example.EcoMarket.Model.Model_Cliente;
import com.example.EcoMarket.Model.Model_Logistica;
import com.example.EcoMarket.Repository.LogisticaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogisticaService {
    @Autowired
    private LogisticaRepository logisticaRepository;

    public Model_Logistica  agregarLogisitca(Model_Logistica logistica){
        return logisticaRepository.save(logistica);
    }
    public List<Model_Logistica> listarLogistica(){ return logisticaRepository.findAll();}


    public Model_Logistica obtenerLogistica(int idLogistica){
        return logisticaRepository.findById(idLogistica)
                .orElseThrow(() -> new RuntimeException("Logistica no encontrada"));
    }

    public String eliminarLogistica(int idLogistica){
        if (logisticaRepository.existsById((idLogistica))){
            logisticaRepository.deleteById(idLogistica);
            return"Usuario logistica eliminado";
        }else{
            return"No se encontro el ususario logistica";
        }
    }

    public  Model_Logistica actualizarLogistica(int idLogistica, Model_Logistica logistica){
        Model_Logistica existente = obtenerLogistica(idLogistica);
        existente.setNombre(logistica.getNombre());
        existente.setEmail(logistica.getEmail());
        existente.setPassword(logistica.getPassword());
        existente.setRol(logistica.getRol());
        existente.setIdLogistica(idLogistica);
        return logisticaRepository.save(existente);


    }



}
