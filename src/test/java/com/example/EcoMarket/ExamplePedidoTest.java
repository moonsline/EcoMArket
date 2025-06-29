package com.example.EcoMarket;

import com.example.EcoMarket.Model.Model_Administrador;
import com.example.EcoMarket.Model.Model_Pedido;
import com.example.EcoMarket.Repository.PedidoRepository;
import com.example.EcoMarket.Service.PedidoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
public class ExamplePedidoTest {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private PedidoService pedidoServiceMock;

    @Test
    @DisplayName("FindAll Test")
    void testPedidoServiceMock(){
        List<Model_Pedido> pedidos = pedidoRepository.findAll();
        assertNotNull(pedidos);
        assertEquals(1, pedidos.size());
    }

    @Test
    @DisplayName("Rectificar estado del pedido")
    void testFindPedido(){
        Model_Pedido prueba = pedidoRepository.findById(1).get();
        assertNotNull(prueba);
        assertEquals("activo", prueba.getEstado());
    }
    @Test
    @DisplayName("Test controller /pedidos (get)")
    void testControllerPedidos() {
        try {
            mockMvc.perform(get("/pedidos"))
                    .andExpect(status().isOk());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            fail();
        }
    }

    @Test
    @DisplayName("Actualizar nombre admin")
    void testUpdatePedido() {
        // Buscar el pedido por su ID
        Model_Pedido pedido = pedidoRepository.findById(1).get();

        // Verificar que el pedido existe
        assertNotNull(pedido);

        // Actualizar el estado del pedido
        pedido.setEstado("terminado");
        pedidoRepository.save(pedido);

        // Recuperar el producto actualizado
        Model_Pedido pedidoActualizado = pedidoRepository.findById(1).get();

        // Verificar que el nombre se haya actualizado correctamente
        assertEquals("terminado", pedidoActualizado.getEstado());
    }
}
