package com.example.EcoMarket.Service;

import com.example.EcoMarket.Model.Model_EmpleadoVentas;
import com.example.EcoMarket.Repository.EmpleadoVentasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoVentasService {
    @Autowired
    EmpleadoVentasRepository empleadoVentasRepository;

    public String agregarEmpleadoVentas(Model_EmpleadoVentas empleadoVentas){
        empleadoVentasRepository.save(empleadoVentas);
        return "Empleado agregado";
    }


    public String listarEmpleadoVentas(){
        String output ="";
        for (Model_EmpleadoVentas empleadoVentas : empleadoVentasRepository.findAll()){
            output += "ID Empleado" + empleadoVentas.getIdEmpleado()+ "\n";
            output += "Nombre Empleado" + empleadoVentas.getNombre()+ "\n";
            output += "Email Empleado" + empleadoVentas.getEmail()+ "\n";
            output += "Password empleado"+ empleadoVentas.getPassword()+ "\n";
            output += "Rol Empleado"+ empleadoVentas.getRol()+ "\n";
        }
        if (output.isEmpty()){
            return "No se encontro el empleado";
        }else{
            return output;
        }
    }

    public String obtenerEmpleadoVentas(int idEmpleado){
        String output = "";
        if (empleadoVentasRepository.existsById(idEmpleado)){
            Model_EmpleadoVentas empleadoVentas = empleadoVentasRepository.findById(idEmpleado).get();
            output += "ID Empleado" + empleadoVentas.getIdEmpleado()+ "\n";
            output += "Nombre Empleado" + empleadoVentas.getNombre()+ "\n";
            output += "Email Empleado" + empleadoVentas.getEmail()+ "\n";
            output += "Password empleado"+ empleadoVentas.getPassword()+ "\n";
            output += "Rol Empleado"+ empleadoVentas.getRol()+ "\n";
            return output;
        }else{
            return "No se encontro el empleado";
        }
    }

    public String eliminarEmpleadoVentas(int idEmpleado){
        if (empleadoVentasRepository.existsById(idEmpleado)){
            empleadoVentasRepository.deleteById(idEmpleado);
            return "Empleado eliminado";
        }else{
            return "No se encontro el empleado";
        }
    }

    public String actualizarEmpleadoVentas(int idEmpleado, Model_EmpleadoVentas empleadoVentas){
        if (empleadoVentasRepository.existsById(idEmpleado)){
            Model_EmpleadoVentas buscado = empleadoVentasRepository.findById(idEmpleado).get();
            buscado.setNombre(empleadoVentas.getNombre());
            buscado.setEmail(empleadoVentas.getEmail());
            buscado.setPassword(empleadoVentas.getPassword());
            buscado.setRol(empleadoVentas.getRol());
            empleadoVentasRepository.save(buscado);
            return "Empleado actualzado";
        }else{
            return "No se encontro el empleado";
        }
    }



}
