package com.example.EcoMarket.Service;

import com.example.EcoMarket.Model.Model_Logistica;
import com.example.EcoMarket.Repository.LogisticaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogisticaService {
    @Autowired
    LogisticaRepository logisticaRepository;
    public String agregarLogisitca(Model_Logistica logistica){
        logisticaRepository.save(logistica);
        return "Usuario Logistica agregado";
    }
    public String listarLogistica(){
        String output ="";
        for(Model_Logistica logistica : logisticaRepository.findAll()){
            output+="ID Logisitca: "+logistica.getIdLogistica() +"\n";
            output+="Nombre logistica: "+logistica.getNombre() +"\n";
            output+="Email logistica: "+logistica.getEmail() +"\n";
            output+="Password logistica: "+logistica.getPassword() +"\n";
            output+="Rol Logisitca: "+logistica.getRol() +"\n";

        }if(output.isEmpty()){
            return "No se encontro el usuario logisitca";
        }else{
            return output;
        }
    }


    public String obtenerLogistica(int idLogistica){
        String output ="";
        if (logisticaRepository.existsById(idLogistica)){
            Model_Logistica logistica = logisticaRepository.findById(idLogistica).get();
            output+="ID Logisitca: "+logistica.getIdLogistica() +"\n";
            output+="Nombre logistica: "+logistica.getNombre() +"\n";
            output+="Email logistica: "+logistica.getEmail() +"\n";
            output+="Password logistica: "+logistica.getPassword() +"\n";
            output+="Rol Logisitca: "+logistica.getRol() +"\n";
            return output;
        }else{
            return"NO se encontro el usuario logisitca";
        }
    }

    public String eliminarLogistica(int idLogistica){
        if (logisticaRepository.existsById((idLogistica))){
            logisticaRepository.deleteById(idLogistica);
            return"Usuario logistica eliminado";
        }else{
            return"No se encontro el ususario logistica";
        }
    }

    public String actualizarLogistica(int idLogistica, Model_Logistica logistica){
        if (logisticaRepository.existsById(idLogistica)){
            Model_Logistica buscado = logisticaRepository.findById(idLogistica).get();
            buscado.setNombre(logistica.getNombre());
            buscado.setEmail(logistica.getEmail());
            buscado.setPassword(logistica.getPassword());
            buscado.setRol(logistica.getRol());
            logisticaRepository.save(buscado);
            return"Usuario logisitca actualizado";
        }else{
            return"No se encontro el usuaro logistica";
        }
    }



}
