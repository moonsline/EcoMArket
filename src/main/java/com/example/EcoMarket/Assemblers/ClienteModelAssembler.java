package com.example.EcoMarket.Assemblers;

import com.example.EcoMarket.Controller.ClienteController;
import com.example.EcoMarket.Model.Model_Cliente;
import com.example.EcoMarket.hateoas.ClienteModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ClienteModelAssembler implements RepresentationModelAssembler<Model_Cliente, EntityModel<ClienteModel>> {

    @Override
    public EntityModel<ClienteModel> toModel(Model_Cliente cliente) {
        ClienteModel model = new ClienteModel(cliente);

        return EntityModel.of(model,
                linkTo(methodOn(ClienteController.class).getClienteById(cliente.getIdCliente())).withSelfRel(),
                linkTo(methodOn(ClienteController.class).getAllClientes()).withRel("clientes"));
    }
}
