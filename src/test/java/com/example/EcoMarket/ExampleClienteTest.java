package com.example.EcoMarket;

import com.example.EcoMarket.Model.Model_Cliente;
import com.example.EcoMarket.Repository.ClienteRepository;
import com.example.EcoMarket.Service.ClienteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ExampleClienteTest {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ClienteService clienteServiceMock;

    @Test
    @DisplayName("FindAll Clientes Test")
    void testClienteServiceMock() {
        List<Model_Cliente> clientes = clienteRepository.findAll();
        assertNotNull(clientes);
        assertEquals(2, clientes.size());
    }

    @Test
    @DisplayName("Rectificar nombre cliente")
    void testFindCliente() {
        Model_Cliente cliente = clienteRepository.findById(1).get();
        assertNotNull(cliente);
        assertEquals("Diego Cruces", cliente.getNombre());
    }

    @Test
    @DisplayName("Test controller de clientes")
    void testController() {
        when(clienteServiceMock.listarCliente()).thenReturn("Lista de clientes completa");

        try {
            mockMvc.perform(get("/clientes"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Lista de clientes completa"));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            fail();
        }
    }
    @Test
    @DisplayName("Actualizar nombre cliente")
    void testUpdateClienteName() {
        Model_Cliente cliente = clienteRepository.findById(1).get();
        assertNotNull(cliente);

        cliente.setNombre("Degoderp");
        clienteRepository.save(cliente);

        Model_Cliente clienteActualizado = clienteRepository.findById(1).get();
        assertEquals("Degoderp", clienteActualizado.getNombre());
    }

}
