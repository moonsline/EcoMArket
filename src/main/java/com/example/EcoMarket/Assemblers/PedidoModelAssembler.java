package com.example.EcoMarket.Assemblers;

import com.example.EcoMarket.Controller.PedidoController;
import com.example.EcoMarket.Model.Model_Pedido;
import com.example.EcoMarket.hateoas.PedidoModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PedidoModelAssembler implements RepresentationModelAssembler<Model_Pedido, EntityModel<PedidoModel>> {
    @Override
    public EntityModel<PedidoModel> toModel(Model_Pedido pedido) {
        PedidoModel model = new PedidoModel(pedido);
        return EntityModel.of(model,
                linkTo(methodOn(PedidoController.class).getPedidoById(pedido.getIdPedido())).withSelfRel(),
                linkTo(methodOn(PedidoController.class).getAllPedidos()).withRel("pedidos"));
    }
}

