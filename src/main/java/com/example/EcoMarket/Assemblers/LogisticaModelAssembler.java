package com.example.EcoMarket.Assemblers;

import com.example.EcoMarket.Controller.LogisticaController;
import com.example.EcoMarket.Model.Model_Logistica;
import com.example.EcoMarket.hateoas.LogisticaModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class LogisticaModelAssembler implements RepresentationModelAssembler<Model_Logistica, EntityModel<LogisticaModel>>{

    @Override
    public EntityModel<LogisticaModel> toModel(Model_Logistica logistica) {
        LogisticaModel model = new LogisticaModel(logistica);

        return EntityModel.of(model,
                linkTo(methodOn(LogisticaController.class).getLogisticaById(logistica.getIdLogistica())).withSelfRel(),
                linkTo(methodOn(LogisticaController.class).getLogistica()).withRel("logistica"));
    }
}
