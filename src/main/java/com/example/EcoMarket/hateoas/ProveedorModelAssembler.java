package com.example.EcoMarket.hateoas;

import com.example.EcoMarket.Controller.ProveedorController;
import com.example.EcoMarket.Model.Model_Proveedor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProveedorModelAssembler implements RepresentationModelAssembler<Model_Proveedor, EntityModel<ProveedorModel>> {

    @Override
    public EntityModel<ProveedorModel> toModel(Model_Proveedor proveedor) {
        ProveedorModel model = new ProveedorModel(proveedor);

        return EntityModel.of(model,
                linkTo(methodOn(ProveedorController.class).getProveedoreById(proveedor.getId())).withSelfRel(),
                linkTo(methodOn(ProveedorController.class).getProveedores()).withRel("proveedores"));
    }
}

