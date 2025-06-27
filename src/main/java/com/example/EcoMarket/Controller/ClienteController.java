package com.example.EcoMarket.Controller;

import com.example.EcoMarket.Model.Model_Cliente;
import com.example.EcoMarket.Service.ClienteService;
import com.example.EcoMarket.Assemblers.ClienteModelAssembler;
import com.example.EcoMarket.hateoas.ClienteModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteModelAssembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<ClienteModel>> getAllClientes() {
        List<EntityModel<ClienteModel>> clientes = clienteService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(clientes,
                linkTo(methodOn(ClienteController.class).getAllClientes()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<ClienteModel> getClienteById(@PathVariable int id) {
        return assembler.toModel(clienteService.obtenerPorId(id));
    }

    @PostMapping
    public EntityModel<ClienteModel> crearCliente(@RequestBody Model_Cliente cliente) {
        return assembler.toModel(clienteService.agregarCliente(cliente));
    }

    @PutMapping("/{id}")
    public EntityModel<ClienteModel> actualizarCliente(@PathVariable int id, @RequestBody Model_Cliente cliente) {
        return assembler.toModel(clienteService.actualizarCliente(id, cliente));
    }

    @DeleteMapping("/{id}")
    public String eliminarCliente(@PathVariable int id) {
        return clienteService.eliminarCliente(id);
    }
}
