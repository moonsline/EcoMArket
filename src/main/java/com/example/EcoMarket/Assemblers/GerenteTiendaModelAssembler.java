package com.example.EcoMarket.Assemblers;

import com.example.EcoMarket.Controller.GerenteTiendaController;
import com.example.EcoMarket.Model.Model_GerenteTienda;
import com.example.EcoMarket.hateoas.GerenteTiendaModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class GerenteTiendaModelAssembler implements RepresentationModelAssembler <Model_GerenteTienda, EntityModel<GerenteTiendaModel>>{

    @Override
    public EntityModel<GerenteTiendaModel> toModel(Model_GerenteTienda gerente) {
      //  GerenteTiendaModel model = new GerenteTiendaModel(gerente);
        GerenteTiendaModel model = new GerenteTiendaModel();
        return EntityModel.of(model,
                linkTo(methodOn(GerenteTiendaController.class).getGerenteTiendaById(gerente.getIdGerente())).withSelfRel(),
                linkTo(methodOn(GerenteTiendaController.class).getGerenteTienda()).withRel("GerenteTienda"));

    }

}
