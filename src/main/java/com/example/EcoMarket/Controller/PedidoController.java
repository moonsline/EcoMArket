package com.example.EcoMarket.Controller;


import com.example.EcoMarket.Assemblers.PedidoModelAssembler;
import com.example.EcoMarket.Model.Model_Pedido;
import com.example.EcoMarket.Repository.PedidoRepository;
import com.example.EcoMarket.Service.PedidoService;
import com.example.EcoMarket.hateoas.PedidoModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidos")
@Tag(name = "Controlador Pedido", description = "Gestión de pedidos y asignación de productos")
public class PedidoController {

    @Autowired
    private PedidoService service;
    @Autowired
    private PedidoModelAssembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<PedidoModel>> getAllPedidos() {
        return CollectionModel.of(service.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public EntityModel<PedidoModel> getPedidoById(@PathVariable int id) {
        return assembler.toModel(service.obtenerPorId(id));
    }

    @PostMapping
    public EntityModel<PedidoModel> crear(@RequestBody Model_Pedido p) {
        return assembler.toModel(service.agregar(p));
    }

    @PutMapping("/{id}")
    public EntityModel<PedidoModel> actualizar(@PathVariable int id, @RequestBody Model_Pedido p) {
        return assembler.toModel(service.actualizar(id, p));
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id) {
        return service.eliminar(id);
    }

    @PostMapping("/{idPedido}/productos/{idProducto}")
    public EntityModel<PedidoModel> agregarProducto(@PathVariable int idPedido, @PathVariable int idProducto) {
        return assembler.toModel(service.agregarProducto(idPedido, idProducto));
    }

    @DeleteMapping("/{idPedido}/productos/{idProducto}")
    public EntityModel<PedidoModel> quitarProducto(@PathVariable int idPedido, @PathVariable int idProducto) {
        return assembler.toModel(service.quitarProducto(idPedido, idProducto));
    }
}
