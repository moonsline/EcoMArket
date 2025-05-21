package com.example.EcoMarket.Service;


import com.example.EcoMarket.Model.Model_Administrador;
import com.example.EcoMarket.Repository.AdministradorRepository;
import com.example.EcoMarket.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministradorService {
    @Autowired
    AdministradorRepository administradorRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    public String agregarAdministrador(Model_Administrador administrador){
        administradorRepository.save(administrador);
        return "Adminstrador agregado";
    }

    public String listaAdminstrador(){
        String output = "";
        for (Model_Administrador administrador: administradorRepository.findAll()){
            output += "ID Administrador:" + administrador.getIdAdmin()+"\n";
            output += "Nombre Adminstrador: " + administrador.getNombre()+"\n";
            output += "Email Adminstrador :" + administrador.getEmail()+"\n";
            output += "Password Administrador:" + administrador.getPassword()+"\n";
            output += "Rol Adminstrador" + administrador.getRol()+"\n";
        }
        if (output.isEmpty()){
            return "No se encontro el adminstrador";
        }else{
            return output;
        }
    }

    public String obtenerAdminstrador(int idAdmin){
        String output ="";
        if (administradorRepository.existsById(idAdmin)){
            Model_Administrador administrador = administradorRepository.findById(idAdmin).get();
            output += "ID Administrador:" + administrador.getIdAdmin()+ "\n";
            output += "Nombre Adminstrador: " + administrador.getNombre()+"\n";
            output += "Email Adminstrador :" + administrador.getEmail()+"\n";
            output += "Password Administrador:" + administrador.getPassword()+"\n";
            output += "Rol Adminstrador" + administrador.getRol()+"\n";
            return output;
        }else{
            return "No se encontro el administrador";
        }
    }

    public String eliminarAdminstrador(int idAdmin){
        if (administradorRepository.existsById(idAdmin)){
            administradorRepository.deleteById(idAdmin);
            return "Administrador eliminado";
        }else{
            return "No se encontro el administrador";
        }
    }

    public String actualizarAdministrador(int idAdmin, Model_Administrador admin){
        if (administradorRepository.existsById(idAdmin)){
            Model_Administrador buscado = administradorRepository.findById(idAdmin).get();
            buscado.setNombre(admin.getNombre());
            buscado.setEmail(admin.getEmail());
            buscado.setPassword(admin.getPassword());
            buscado.setRol(admin.getRol());
            administradorRepository.save(buscado);
            return "Administrador actualizado";
        }else{
            return "No se encontro el adminstrador";
        }
    }













}
