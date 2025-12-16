package com.example.EcoMarket.Assemblers;

import com.example.EcoMarket.Controller.PedidoController;
import com.example.EcoMarket.Controller.ProductoController;
import com.example.EcoMarket.Model.Model_Producto;
import com.example.EcoMarket.hateoas.ProductoModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductoModelAssembler implements RepresentationModelAssembler<Model_Producto, EntityModel<ProductoModel>> {

    @Override
    public EntityModel<ProductoModel> toModel(Model_Producto producto) {

        ProductoModel model = new ProductoModel(producto);

        EntityModel<ProductoModel> entity = EntityModel.of(model,
                linkTo(methodOn(ProductoController.class).getProductoById(producto.getId())).withSelfRel(),
                linkTo(methodOn(ProductoController.class).getAllProductos()).withRel("productos")
        );

        // Agregar links a pedidos que contienen este producto (no expandirlos)
        if (producto.getPedidos() != null) {
            producto.getPedidos().forEach(ped ->
                    entity.add(
                            linkTo(methodOn(PedidoController.class).getPedidoById(ped.getIdPedido()))
                                    .withRel("pedido_" + ped.getIdPedido())
                    )
            );
        }

        return entity;
    }
}
