package com.example.EcoMarket.Controller;


import com.example.EcoMarket.Model.Model_Cliente;
import com.example.EcoMarket.Service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Cliente controlador", description = "Servicio de gestión de cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

     @GetMapping
     @Operation(summary = "Optener Clientes", description = "Obtiene una lista de clientes")
     @ApiResponse(responseCode = "200", description = "¡Consulta exitosa!")
    public String getCliente() { return clienteService.listarCliente();}

     @PostMapping
    public String postCliente(@RequestBody Model_Cliente cliente) {return clienteService.agregarCliente(cliente);}

    @GetMapping("/{idCliente}")
    public String getClienteById(@PathVariable int idCliente) {
        return clienteService.obtenerCliente(idCliente);
    }

    @DeleteMapping("/{idCliente}")
    public String deleteClienteById(@PathVariable int idCliente) {
        return clienteService.eliminarCliente(idCliente);
    }

    @PutMapping("/{idCliente}")
    public String upddateClienteById(@PathVariable int idCliente, @RequestBody Model_Cliente cliente) {
        return clienteService.actualizarCliente(idCliente, cliente);
    }



}
