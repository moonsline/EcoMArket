package com.example.EcoMarket.Service;

import com.example.EcoMarket.Model.Model_Usuario;
import com.example.EcoMarket.Repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String agregarUsuario(Model_Usuario usuario) {
        usuarioRepository.save(usuario);
        return "Usuario agregado con exito!";
    }

    public String listarUsuarios() {
        String output = "";
        for (Model_Usuario usuario : usuarioRepository.findAll()) {
            output += "ID usuario: "+usuario.getNombre() + "\n";
            output += "Nombre usuario: "+usuario.getNombre() + "\n";
            output += "Email usuario: "+usuario.getEmail() + "\n";
            output += "Password usuario: "+usuario.getPassword() + "\n";
            output += "Rol usuario: "+usuario.getRol()+ "\n";
        }
        if(output.isEmpty()){
            return "No se encontro el usuario";
        }else {
            return output;
        }

    }
    public String obtenerUsuario(int id) {
        String output = "";
        if (usuarioRepository.existsById(id)) {
            Model_Usuario usuario = usuarioRepository.findById(id).get();
            output += "ID usuario: "+usuario.getIdUsuario() + "\n";
            output += "Nombre usuario: "+usuario.getNombre() + "\n";
            output += "Email usuario: "+usuario.getEmail() + "\n";
            output += "Password usuario: "+usuario.getPassword() + "\n";
            output += "Rol usuario: "+usuario.getRol() + "\n";
            return output;
        }else {
            return "No se encontro el usuario";
        }
    }

    public String eliminarUsuario(int id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return "Usuario eliminado con exito!";
        }else{
            return "No se encontro el usuario";
        }
    }

    public String actualizarUsuario(int id, Model_Usuario usuario) {
        if (usuarioRepository.existsById(id)) {
            Model_Usuario buscado = usuarioRepository.findById(id).get();
            buscado.setNombre(usuario.getNombre());
            buscado.setEmail(usuario.getEmail());
            buscado.setPassword(usuario.getPassword());
            buscado.setRol(usuario.getRol());
            usuarioRepository.save(buscado);
            return "Usuario actualizado con exito!";
        }else {
            return "No se encontro el usuario";
        }
    }
}
