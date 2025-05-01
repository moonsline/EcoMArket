package com.example.EcoMarket.Service;

import com.example.EcoMarket.Model.Model_Usuario;
import com.example.EcoMarket.Repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Model_Usuario> getUsuarios(){
        return usuarioRepository.obtenerUsuarios();
    }
    public Model_Usuario saveUsuario(Model_Usuario usuario){
        return usuarioRepository.agregarUsuario(usuario);
    }
    public Model_Usuario getUsuarioId(int id){
        return usuarioRepository.buscarPorId(id);
    }
    public Model_Usuario getUsuarioByEmail(String email){
        return usuarioRepository.buscarPorEmail(email);
    }
    public Model_Usuario updateUsuario(Model_Usuario usuario){
        return usuarioRepository.actualizarUsuario(usuario);
    }
    public String deleteUsuario(int id){
        usuarioRepository.eliminarUsuario(id);
        return "Usuario eliminado";
    }
}
