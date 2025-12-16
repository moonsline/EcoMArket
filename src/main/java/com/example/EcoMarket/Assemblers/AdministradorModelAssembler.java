package com.example.EcoMarket.Assemblers;

import com.example.EcoMarket.Controller.AdministradorController;
import com.example.EcoMarket.Model.Model_Administrador;
import com.example.EcoMarket.hateoas.AdministradorModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class AdministradorModelAssembler implements RepresentationModelAssembler<Model_Administrador, EntityModel<AdministradorModel>> {

    @Override
    public EntityModel<AdministradorModel> toModel(Model_Administrador admin) {
        AdministradorModel model = new AdministradorModel(admin);

        return EntityModel.of(model,
                linkTo(methodOn(AdministradorController.class).getAdministradorById(admin.getIdAdmin())).withSelfRel(),
                linkTo(methodOn(AdministradorController.class).getAllAdministradores()).withRel("administradores"));
    }
}

