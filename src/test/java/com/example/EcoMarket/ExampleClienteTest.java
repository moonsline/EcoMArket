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

import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@AutoConfigureMockMvc
public class ExampleClienteTest {

    @MockBean
    ClienteRepository clienteRepository;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ClienteService clienteServiceMock;

    @Test
    @DisplayName("FindAll Clientes Test")
    void testClienteServiceMock() {
        List<Model_Cliente> clientes = List.of(
                new Model_Cliente(1, "Diego Cruces", "diego@mail.com", "pass", "cliente","direccion 1234"),
                new Model_Cliente(2, "Ana Perez", "ana@mail.com", "pass", "cliente", "direccion 5678")
        );
        when(clienteRepository.findAll()).thenReturn(clientes);

        List<Model_Cliente> resultado = clienteRepository.findAll();
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
    }

    @Test
    @DisplayName("Rectificar nombre cliente")
    void testFindCliente() {
        Model_Cliente cliente = new Model_Cliente(1, "Diego Cruces", "diego@mail.com", "pass", "cliente","direccion 1234");
        when(clienteRepository.findById(1)).thenReturn(java.util.Optional.of(cliente));

        Model_Cliente resultado = clienteRepository.findById(1).get();
        assertNotNull(resultado);
        assertEquals("Diego Cruces", resultado.getNombre());
    }

    @Test
    @DisplayName("Test controller - solo status")
    void testController() {
        try {
            mockMvc.perform(get("/clientes"))
                    .andExpect(status().isOk());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            fail();
        }
    }

    @Test
    @DisplayName("Actualizar nombre cliente")
    void testUpdateClienteName() {
        Model_Cliente cliente = new Model_Cliente(1, "Diego Cruces", "diego@mail.com", "pass", "cliente","direccion 1234");
        when(clienteRepository.findById(1)).thenReturn(java.util.Optional.of(cliente));
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        cliente.setNombre("Degoderp");
        clienteRepository.save(cliente);

        when(clienteRepository.findById(1)).thenReturn(java.util.Optional.of(cliente));
        Model_Cliente clienteActualizado = clienteRepository.findById(1).get();
        assertEquals("Degoderp", clienteActualizado.getNombre());
    }
}
