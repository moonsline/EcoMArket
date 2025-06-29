package com.example.EcoMarket.Assemblers;

import com.example.EcoMarket.Controller.UsuarioController;
import com.example.EcoMarket.Model.Model_Usuario;
import com.example.EcoMarket.hateoas.UsuarioModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Model_Usuario, EntityModel<UsuarioModel>> {

    @Override
    public EntityModel<UsuarioModel> toModel(Model_Usuario usuario) {
        UsuarioModel usuarioModel = new UsuarioModel(usuario);
        return EntityModel.of(usuarioModel,
                linkTo(methodOn(UsuarioController.class).getUsuarioById(usuario.getId())).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).getAllUsuarios()).withRel("usuarios"));
    }
}