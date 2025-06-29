package com.example.EcoMarket;

import com.example.EcoMarket.Model.Model_Pedido;
import com.example.EcoMarket.Repository.PedidoRepository;
import com.example.EcoMarket.Service.PedidoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ExamplePedidoTest {

    @MockBean
    private PedidoRepository pedidoRepository;

    @MockBean
    private PedidoService pedidoService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("FindAll Test")
    void testPedidoServiceMock(){
        Model_Pedido pedido = new Model_Pedido();
        when(pedidoRepository.findAll()).thenReturn(Arrays.asList(pedido));
        List<Model_Pedido> pedidos = pedidoRepository.findAll();
        assertNotNull(pedidos);
        assertEquals(1, pedidos.size());
    }

    @Test
    @DisplayName("Rectificar estado del pedido")
    void testFindPedido(){
        Model_Pedido prueba = new Model_Pedido();
        prueba.setEstado("activo");
        when(pedidoRepository.findById(1)).thenReturn(Optional.of(prueba));
        Model_Pedido result = pedidoRepository.findById(1).get();
        assertNotNull(result);
        assertEquals("activo", result.getEstado());
    }

    @Test
    @DisplayName("Test controller /pedidos (get)")
    void testControllerPedidos() {
        try {
            Model_Pedido pedido = new Model_Pedido();
            pedido.setProductos(new ArrayList<>()); // Inicializa la lista vacía
            when(pedidoService.obtenerTodos()).thenReturn(Arrays.asList(pedido));
            mockMvc.perform(get("/pedidos"))
                    .andExpect(status().isOk());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            fail();
        }
    }

    @Test
    @DisplayName("Actualizar estado del pedido")
    void testUpdatePedido() {
        Model_Pedido pedido = new Model_Pedido();
        pedido.setEstado("activo");
        when(pedidoRepository.findById(1)).thenReturn(Optional.of(pedido));
        Model_Pedido result = pedidoRepository.findById(1).get();
        assertNotNull(result);
        result.setEstado("terminado");
        when(pedidoRepository.save(result)).thenReturn(result);
        when(pedidoRepository.findById(1)).thenReturn(Optional.of(result));
        Model_Pedido pedidoActualizado = pedidoRepository.findById(1).get();
        assertEquals("terminado", pedidoActualizado.getEstado());
    }
}