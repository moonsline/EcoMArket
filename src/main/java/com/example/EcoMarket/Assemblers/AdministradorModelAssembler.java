package com.example.EcoMarket.Assemblers;

import com.example.EcoMarket.Controller.AdministradorControllerV2;
import com.example.EcoMarket.Model.Model_Administrador;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AdministradorModelAssembler implements RepresentationModelAssembler<Model_Administrador, EntityModel<Model_Administrador>> {
    @Override
    public EntityModel<Model_Administrador> toModel(Model_Administrador admin) {
        return EntityModel.of(admin,

linkTo(methodOn(AdministradorControllerV2.class).getAdministradorById(admin.getIdAdmin())).withSelfRel(),

linkTo(methodOn(AdministradorControllerV2.class).getAdministrador()).withRel("Administrador"),

linkTo(methodOn(AdministradorControllerV2.class).upddateAdministradorById(admin.getIdAdmin(),admin)).withRel("Put"),

linkTo(methodOn(AdministradorControllerV2.class).postAdministrador(admin)).withRel("Post"),

linkTo(methodOn(AdministradorControllerV2.class).deleteAdministradorById(admin.getIdAdmin())).withSelfRel()
                );


    }
}
