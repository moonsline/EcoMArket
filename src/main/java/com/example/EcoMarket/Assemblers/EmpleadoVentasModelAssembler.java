package com.example.EcoMarket.Assemblers;

import com.example.EcoMarket.Controller.EmpleadoVentasController;
import com.example.EcoMarket.Model.Model_EmpleadoVentas;
import com.example.EcoMarket.hateoas.EmpleadoVentasModel;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class EmpleadoVentasModelAssembler implements RepresentationModelAssembler<Model_EmpleadoVentas, EntityModel<EmpleadoVentasModel>> {

    @Override
    public EntityModel<EmpleadoVentasModel> toModel(Model_EmpleadoVentas ev) {
        EmpleadoVentasModel model = new EmpleadoVentasModel(ev);
        return EntityModel.of(model,
                linkTo(methodOn(EmpleadoVentasController.class).getEmpleadoVentasById(ev.getIdEmpleadoVentas())).withSelfRel(),
                linkTo(methodOn(EmpleadoVentasController.class).getAllEmpleadosVentas()).withRel("empleados-ventas"));
    }
}
