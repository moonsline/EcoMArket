package com.example.EcoMarket;


import com.example.EcoMarket.Model.Model_GerenteTienda;
import com.example.EcoMarket.Model.Model_Logistica;
import com.example.EcoMarket.Repository.LogisticaRepository;
import com.example.EcoMarket.Service.LogisticaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ExampleLogisticaTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private LogisticaRepository logisticaRepository;
    @Autowired
    private LogisticaService logisticaServiceMock;

    @Test
    @DisplayName("FindAll test")
    void testLogisticaServiceMock(){
        List<Model_Logistica> logistica = logisticaRepository.findAll();
        assertNotNull(logistica);
        assertEquals(1, logistica.size());
    }

    @Test
    @DisplayName("Rectificar nombre logistica")
    void testFindLogistica(){
        Model_Logistica prueba = logisticaRepository.findById(1).get();
        assertNotNull(prueba);
        assertEquals("Pedro Pablo Perez", prueba.getNombre());
    }

    @Test
    @DisplayName("Test controller")
    void testController(){
        when(logisticaServiceMock.listarLogistica()).thenReturn("Lista completa");

        try{
            mockMvc.perform(get("/logistica"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Lista completa"));
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            fail();
        }

    }

    @Test
    @DisplayName("Actualizar nombre logistica")
    void testUpdateLogistica(){
        Model_Logistica prueba = logisticaRepository.findById(1).get();
        assertNotNull(prueba);
        prueba.setNombre("Gonzalo Gonzales Ganzo");
        logisticaRepository.save(prueba);
        Model_Logistica logisticaActualizado = logisticaRepository.findById(1).get();
        assertEquals("Gonzalo Gonzales Ganzo", logisticaActualizado.getNombre());
    }
}
