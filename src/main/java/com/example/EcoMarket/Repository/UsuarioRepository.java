package com.example.EcoMarket.Repository;

import com.example.EcoMarket.Model.Model_Usuario;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepository {


    //Arreglo que guardará todos los usuarios
    private List<Model_Usuario> usuarios = new ArrayList<>();


    //Metodo que retorna todos los libros
    public List<Model_Usuario> obtenerUsuarios() {
        return usuarios;
    }

    //Buscar un usuario por su id
    public Model_Usuario buscarPorId(int idUsuario) {
        for (Model_Usuario usuario : usuarios) {
            if (usuario.getIdUsuario() == idUsuario) {
                return usuario;
            }
        }
        return null;
    }

    //Buscar un usuario por su email
    public Model_Usuario buscarPorEmail(String email) {
        for (Model_Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                return usuario;
            }
        }
        return null;
    }

    //Metodo para agregar usuario al registro
    public Model_Usuario agregarUsuario(Model_Usuario usuario) {
        usuarios.add(usuario);
        return usuario;
    }

    //Metodo para actualizar datos de usuario
    public Model_Usuario actualizarUsuario(Model_Usuario usuario) {
        int idUsuario = 0;
        int idPosicion =0;

        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getIdUsuario() == usuario.getIdUsuario()) {
                idUsuario = usuarios.get(i).getIdUsuario();
                idPosicion = i;
            }
        }

        Model_Usuario usuario1 = new Model_Usuario();
        usuario1.setIdUsuario(idUsuario);
        usuario1.setNombre(usuario.getNombre());
        usuario1.setEmail(usuario.getEmail());
        usuario1.setPassword(usuario.getPassword());
        usuario1.setRol(usuario.getRol());

        usuarios.set(idPosicion, usuario1);
        return usuario1;
    }

    //Metodo que elimina usuario mediante su id
    public String eliminarUsuario(int idUsuario) {
        Model_Usuario usuario = buscarPorId(idUsuario);

        if (usuario != null) {
            usuarios.remove(usuario);
            return "Usuario removido com sucesso!";
        }else{
            return "No encontrado";
        }
    }
}
