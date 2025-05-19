package com.example.EcoMarket.Controller;


import com.example.EcoMarket.Model.Model_Pedido;
import com.example.EcoMarket.Repository.PedidoRepository;
import com.example.EcoMarket.Service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public String getPedidos(){
        return pedidoService.listarPedidos();
    }

    @PostMapping
    public String postPedido(@RequestBody Model_Pedido pedido){
        return pedidoService.agregarPedido(pedido);
    }

    @GetMapping("/{id}")
    public String getPedidoById(@PathVariable int id){
        return pedidoService.obtenerPedido(id);
    }

    @DeleteMapping("/{id}")
    public String deletePedido(@PathVariable int id){
        return pedidoService.eliminarPedido(id);
    }

    @PutMapping("/{id}")
    public String updatePedidoById(@PathVariable int id, @RequestBody Model_Pedido pedido){
        return pedidoService.actualizarPedido(id, pedido);
    }
}
