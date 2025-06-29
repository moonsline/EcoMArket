package com.example.EcoMarket;

import com.example.EcoMarket.Model.Model_Logistica;
import com.example.EcoMarket.Repository.LogisticaRepository;
import com.example.EcoMarket.Service.LogisticaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ExampleLogisticaTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LogisticaRepository logisticaRepository;

    @MockBean
    private LogisticaService logisticaService;

    @Test
    @DisplayName("FindAll test")
    void testLogisticaServiceMock(){
        Model_Logistica logistica = new Model_Logistica();
        when(logisticaRepository.findAll()).thenReturn(Arrays.asList(logistica));
        List<Model_Logistica> logisticaList = logisticaRepository.findAll();
        assertNotNull(logisticaList);
        assertEquals(1, logisticaList.size());
    }

    @Test
    @DisplayName("Rectificar nombre logistica")
    void testFindLogistica(){
        Model_Logistica prueba = new Model_Logistica();
        prueba.setNombre("Pedro Pablo Perez");
        when(logisticaRepository.findById(1)).thenReturn(Optional.of(prueba));
        Model_Logistica result = logisticaRepository.findById(1).get();
        assertNotNull(result);
        assertEquals("Pedro Pablo Perez", result.getNombre());
    }

    @Test
    @DisplayName("Test controller")
    void testController(){
        when(logisticaService.listarLogistica()).thenReturn("Lista completa");
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
        Model_Logistica prueba = new Model_Logistica();
        prueba.setNombre("Pedro Pablo Perez");
        when(logisticaRepository.findById(1)).thenReturn(Optional.of(prueba));
        Model_Logistica result = logisticaRepository.findById(1).get();
        assertNotNull(result);
        result.setNombre("Gonzalo Gonzales Ganzo");
        when(logisticaRepository.save(result)).thenReturn(result);
        when(logisticaRepository.findById(1)).thenReturn(Optional.of(result));
        Model_Logistica logisticaActualizado = logisticaRepository.findById(1).get();
        assertEquals("Gonzalo Gonzales Ganzo", logisticaActualizado.getNombre());
    }
}
