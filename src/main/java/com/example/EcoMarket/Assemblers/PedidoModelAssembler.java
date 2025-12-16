package com.example.EcoMarket.Assemblers;

import com.example.EcoMarket.Controller.PedidoController;
import com.example.EcoMarket.Controller.ProductoController;
import com.example.EcoMarket.Model.Model_Pedido;
import com.example.EcoMarket.hateoas.PedidoModel;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Assembler encargado de convertir la entidad JPA Model_Pedido
 * en un modelo HATEOAS (PedidoModel) que incluye enlaces (links).
 */
@Component
public class PedidoModelAssembler implements RepresentationModelAssembler<Model_Pedido, EntityModel<PedidoModel>> {

    @Override
    public EntityModel<PedidoModel> toModel(Model_Pedido pedido) {

        // Convierto la entidad JPA en un modelo HATEOAS
        PedidoModel model = new PedidoModel(pedido);

        // Creo el EntityModel y agrego los links principales
        EntityModel<PedidoModel> entity = EntityModel.of(
                model,
                // Link a este mismo pedido
                linkTo(methodOn(PedidoController.class)
                        .getPedidoById(pedido.getIdPedido()))
                        .withSelfRel(),

                // Link a la lista de pedidos
                linkTo(methodOn(PedidoController.class)
                        .getAllPedidos())
                        .withRel("pedidos")
        );

        // Agrego links a cada producto del pedido
        // Sin expandirlos para evitar ciclos recursivos en el JSON
        if (pedido.getProductos() != null) {
            pedido.getProductos().forEach(prod ->
                    entity.add(
                            linkTo(methodOn(ProductoController.class)
                                    .getProductoById(prod.getId()))
                                    .withRel("producto_" + prod.getId())
                    )
            );
        }

        return entity;
    }
}
