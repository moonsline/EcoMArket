package com.example.EcoMarket.Controller;


import com.example.EcoMarket.Model.Model_Pedido;
import com.example.EcoMarket.Repository.PedidoRepository;
import com.example.EcoMarket.Service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
@Tag(name="Controlador Pedido", description= "Servicio de gestion de pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    @Operation(summary = "Obtener pedidos", description="Obtiene la lista de pedidos")
    public String getPedidos(){
        return pedidoService.listarPedidos();
    }

    @PostMapping
    @Operation(summary = "Agregar pedidos", description="Agrega pedidos a la lista")
    public String postPedido(@RequestBody Model_Pedido pedido){
        return pedidoService.agregarPedido(pedido);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener pedido con su id", description="Obtiene pedido buscando su id")
    public String getPedidoById(@PathVariable int id){
        return pedidoService.obtenerPedido(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina pedidos", description="Elimina pedidos de la lista")
    public String deletePedido(@PathVariable int id){
        return pedidoService.eliminarPedido(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Elimina pedido con su id", description="Elimina pedido buscando su id")
    public String updatePedidoById(@PathVariable int id, @RequestBody Model_Pedido pedido){
        return pedidoService.actualizarPedido(id, pedido);
    }
}
