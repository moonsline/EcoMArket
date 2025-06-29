package com.example.EcoMarket.Assemblers;

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
        return EntityModel.of(model,
                linkTo(methodOn(ProductoController.class).getProductoById(producto.getId())).withSelfRel(),
                linkTo(methodOn(ProductoController.class).getAllProductos()).withRel("productos"));
    }
}
